package prac3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml";
		String key = "3162521e5cdc63f914f0403d8b7bbec3";

		Scanner sc = new Scanner(System.in);
		System.out.println("날짜(yyyymmdd) >>> ");
		String targetDt = sc.next();

		try {
			key = URLEncoder.encode(key,"UTF-8");
			targetDt = URLEncoder.encode(targetDt, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("인코딩 실패", e);
		}
		String apiURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key="+ key + "&targetDt=" + targetDt;
		// 주의 : 키 다음에 공백, 특히 등호 = 뒤에, & 잘 붙이기.

		String reponse = getResponse(apiURL); // 메소드화 시킴 getResponse 이 메소드는 주소 받아서 신청하는 아이
		//System.out.println(reponse);
		createFile(reponse);

	}
	
	
	public static String getResponse(String apiURL) {
		
		HttpURLConnection con = getConnection(apiURL);
		try {
			if(con.getResponseCode()==HttpURLConnection.HTTP_OK) {
				return readBody(con.getInputStream());		//readBody 은 String으로 구현되어야함
				
			}else {
				return readBody(con.getErrorStream());
			}
			
			
			
			
		}catch (IOException e) {
			throw new RuntimeException("API 요청 오류", e);
		}
		
	}

	
	
	public static HttpURLConnection getConnection(String apiURL) {
		
		
		
		try {
			
			URL url = new URL(apiURL);
			return (HttpURLConnection)url.openConnection();
		}catch (MalformedURLException e) {
			throw new RuntimeException("API 주소 오류",e);
		}catch(IOException e) {
			throw new RuntimeException("API 연결 오류",e);
		}
		
		
		
		
	}
	
	public static String readBody(InputStream in) {
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				
			}
			return sb.toString();
			
		}catch(IOException e) {
		throw new RuntimeException("API 응답 오류",e);
	}
	
	}	
	
	
	public static void createFile(String response) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\storage\\boxoffice.xml"))) {
			bw.write(response);	
			bw.flush();				//해도 그만 안해도 그만인데 안전빵으로 해줌. 스트림에 남아있는 모든 걸 보내줌. 빨대사이에 낀거를 후 불어줌(?)
		}catch(IOException e) {
			throw new RuntimeException("파일 생성 오류", e);
		}
	}

}

