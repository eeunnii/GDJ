package service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class NaverCaptchaServiceimpl implements NaverChaprtSerive {
	
	//field
	private final String CLIENT_ID = "_48qz3Z7EcTkgD6lEtVx";
	private final String CLIENT_SECRET = "_fp9SsfDX0";
	
	
	@Override
	public String getChaptchakey() {
		// code=0 : "키 발급", code=1 "사용자 입력값 검증"
		// https://developers.naver.com/docs/utils/captcha/reference/#%ED%8C%8C%EB%9D%BC%EB%AF%B8%ED%84%B0
		String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=0";
		
		// 반환할 key값(네이버API가 제공하는 캡차
		String key = null;
		
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();

			// 요청 메소드(Http 메소드) 
			con.setRequestMethod("GET"); // 대문자로 작성할 것!!
			
			// 요청헤더  : 클라이언트 아이디,, 클라이언트 씨크릿
			// https://developers.naver.com/docs/utils/captcha/reference/#%EC%B0%B8%EA%B3%A0-%EC%82%AC%ED%95%AD
			con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
			con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);
			
			// 입력 스트림 선택 및 생성(네이버 API 서버의 정보를 읽기 위함)
			BufferedReader reader =null;
			
			if(con.getResponseCode()==200) {   // 200 : HttpURLconnection에 저장되어있음 깃허브
				reader = new BufferedReader(new java.io.InputStreamReader(con.getInputStream()));	
			}else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			// 네이버 api 서버가 보낸 데이터 저장
			StringBuilder sb = new StringBuilder();
			String line;
			while((line=reader.readLine()) != null) {
				sb.append(line);   // {"key":"8eNtWnpjSnDd2046"} 이게 들어있는거임. 
			}
			// 네이버 API서버가 보낸 데이터 확인 및 반환
			JSONObject obj = new JSONObject(sb.toString());
			key = obj.getString("key");
			
			// 자원 반남
			reader.close();
			con.disconnect();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}

		return key;
	}

	@Override
	public Map<String, String> getChaptchaImage(HttpServletRequest requset, String key) {
		Map<String,String> map= new HashMap<String, String>();
		
		String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key="+key;
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();

			// 요청 메소드(Http 메소드) 
			con.setRequestMethod("GET"); // 대문자로 작성할 것!!
			
			// 요청헤더  : 클라이언트 아이디,, 클라이언트 씨크릿
			// https://developers.naver.com/docs/utils/captcha/reference/#%EC%B0%B8%EA%B3%A0-%EC%82%AC%ED%95%AD
			con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
			con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);
			
			// 입력 스트림 선택 및 생성(네이버 API 서버의 정보를 읽기 위함) -- 이미지는 바이트기반의 스트림으로 받아와야한다.
		
			
			// 응답이 성공하면 이미지(JPG)가 응답
			if(con.getResponseCode()==200) {   // 200 : HttpURLconnection에 저장되어있음 깃허브
				// 이미지 다운로드 경로 
				String dirname="ncaptcha";
				String realPath = requset.getServletContext().getRealPath("ncaptcha");
				File dir = new File(realPath);
				if(dir.exists()==false) {
					dir.mkdirs(); 
				}
				// 이미지 이름 (중복되는 이름잇음 안되니까 타임스탬프사용)
				String filename = System.currentTimeMillis() + ".jpg";
				
				// 캡처 이미지 객체 생성
				File file = new File(dir, filename);
				
				// 네이버API로 부터 정보를 읽어서 (in) 서버경로에 저장(out)
				BufferedInputStream in = new BufferedInputStream(con.getInputStream());	
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
				
				// 저장(다운로드)
				byte[] b = new byte[1024];
				int readByte = 0;
				while((readByte=in.read(b)) != -1) { // -1은 끝났다는 뜻.
					out.write(b, 0, readByte);
				}
				
				//login.jsp로 전달할 데이터(캡차이미지 경로+파일명+캡차키)
				map.put("dirname", dirname);
				map.put("filename", filename);
				map.put("key", key);
				
				//자원반납
				out.close();
				in.close();
				
				
			}
			
			//응답이 실패하면 text 형식으로 응답이 올거임 - 근데 실패한 응답이니까 크게 신경안써도o
			else {
				BufferedReader reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				StringBuilder sb = new StringBuilder();
				String line;
				while((line=reader.readLine()) != null) {
					sb.append(line);   // {"key":"8eNtWnpjSnDd2046"} 이게 들어있는거임. 
				}
				System.out.println("응답 실패 사유");
				System.out.println(sb.toString());
				reader.close();
			}
			
			// 자원 반남
			con.disconnect();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	@Override
	public void refreshCaptcha(HttpServletRequest requset, HttpServletResponse response) {
		// 응답 데이터 형식 : JSON
		response.setContentType("application/json");
		
		// 응답 데이터 
		// 캡차키+캡차이미지 새로 요청해서 제이슨으로 만들어줌
		/*
			{
				"dirname" : "nacaptcha",
				"filename" : "1111111111.jap"
				"key" : "ajdfaioedslkfajweiakdf"
			}
		*/
		String key = getChaptchakey();
		Map<String, String> map = getChaptchaImage(requset, key);
		JSONObject obj = new JSONObject(map); //제이슨은 map으로도 만들 수 잇음
		
		// 응답 
		try {
			PrintWriter out = response.getWriter();
			out.println(obj.toString());
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean validateUserInput(HttpServletRequest request) {
			
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		
		// 반환할 값
		boolean result = false;

		
		// 요청URL
		String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=1&key="+key+"&value="+value;
		
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();

			// 요청 메소드(Http 메소드) 
			con.setRequestMethod("GET"); // 대문자로 작성할 것!!
			
			// 요청헤더  : 클라이언트 아이디,, 클라이언트 씨크릿
			// https://developers.naver.com/docs/utils/captcha/reference/#%EC%B0%B8%EA%B3%A0-%EC%82%AC%ED%95%AD
			con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
			con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);
			
			// 입력 스트림 선택 및 생성(네이버 API 서버의 정보를 읽기 위함)
			BufferedReader reader =null;
			
			if(con.getResponseCode()==200) {   // 200 : HttpURLconnection에 저장되어있음 깃허브
				reader = new BufferedReader(new java.io.InputStreamReader(con.getInputStream()));	
			}else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			// 네이버 api 서버가 보낸 데이터 저장
			StringBuilder sb = new StringBuilder();
			String line;
			while((line=reader.readLine()) != null) {
				sb.append(line);   // {"key":"8eNtWnpjSnDd2046"} 이게 들어있는거임. 
			}
			// 네이버 API서버가 보낸 데이터 확인 및 반환
			JSONObject obj = new JSONObject(sb.toString());
			result = obj.getBoolean("result");
			
			// 자원 반남
			reader.close();
			con.disconnect();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return result;
	}

}
