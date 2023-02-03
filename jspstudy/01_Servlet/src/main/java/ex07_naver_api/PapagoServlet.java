package ex07_naver_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PapagoServlet")
public class PapagoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PapagoServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 클라이언트 아이디, 시크릿
		String clientId = "ZuA2Hxw8DnfFAdWjRSk4";
		String clientSecret = "oaR8PF5cnk";
		
		// 요청 파라미터 (원본 언어, 목적언어, 번역할 테슽 
		String source = request.getParameter("source");
		String target = request.getParameter("target");
		String text = request.getParameter("text");
		
		// 번역할 텍스트 UTF-8 인코딩
		try {
			text = URLEncoder.encode(text,"UTF-8");
		}catch(UnsupportedEncodingException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("번역할 텍스트 인코딩 실패");
			out.close();
		}
		
		// -- 네이버에 보낼 데이터 준비 완료
		
		// API 접속
		String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
		}catch(MalformedURLException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API URL이 잘 못 되었습니다.");
			out.close();
		}catch(IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("api 연결 접속이 실패했습니다");
			out.close();
		}
		
		// API 요청
		try {
			//요청 헤더
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			
			// 요청 메소드
			con.setRequestMethod("POST");
			con.setDoOutput(true); // post 방식에서 데이터 보낼거 있을 때 같이 써주는 코드, 우리한테 보낼게 있다는 뜻, post와 get은 파라미터는 어떻게 가느냐의 차이
			//url?파라미터, get방식 // post는 주소에 파라미터 안붙음. 본문에 붙여서 보내야함. 우리가 네이버한테 보내야하는 것을 출력스트림을 써서 보내야함 
			// 보내는 코드를 자바로 짜야함. 
			
			//요청 파라미터 보내기 
			String params = "source="+source+"&target="+target+"&text="+text;
			OutputStream outputStream= con.getOutputStream();  // getOutputStream는 바이트기반스트림임. 바이트는 스트링을 못 보냄. String을 바이트배열로 바꿔서 보낼거임
			outputStream.write(params.getBytes());
			outputStream.close();
		}catch(IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("api 요청이 실패했습니다");
			out.close();
		}
			// API 응답 스트림 생성(정상 스트림, 에러스트림)
			// 받을 때는 문자 기반으로 받아야되서 buffered 사용
			BufferedReader reader = null;
			try {
				int responseCode = con.getResponseCode();
				if(responseCode == HttpURLConnection.HTTP_OK) {
					reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				}else {
					reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}
			}catch(IOException e) {
				response.setContentType("text/plain; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("API 응답 스트림 생성이 실패했습니다");
				out.close();
			}
			
			// API 응답 (StringBuilder에 저장)
			StringBuilder sb = new StringBuilder();
			String line = null;
			try {
				while((line = reader.readLine())!=null ) {
					sb.append(line);
				}
			}catch(IOException e) {
				response.setContentType("text/plain; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("API 응답 스트림 생성이 실패했습니다");
				out.close();
			}
			
			// client로 API 응답 결과(StringBuilder) 보내기
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
			out.close();
			
			
			//sb.toString이 resData로 들어감
			
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
