package ex02_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


//지금 배우고 있는거 : ★공공테이터 수집을 위한 객체지향 프로그램 구현


//요청( 어떤 데이터를 (처리)달라고 요청하는 것 )
// 클라이언트 -> 서버 
//요청할 주소 : apiURL
//요청 파라미터(서버로 보내줄 데이터)
// URL 문서 보면 필수/선택으로 보내야하는게 분리되어 있음
// apiURL? 파라미터=값&파라미터=값   ...     --> <파라미터=값&>  이 보내줘야하는 데이터

public class Main {
	
	//알아야될 용어 
	// 요청
	// 1. Request
	// 2. 클라이언트 ->
	// 
	
	// 파라미터
	// 1. Parameter
	// 2. 보내는 데이터(변수 개념)
	
	// 응답
	// 1.response : 서버가가 클라이너에게 보내줌
	
	
	public static void m1() {
		//전국 종량제 봉투가격 표준 데이터
		
		//API 주소 ( 주소 + 요청 파라미터 )
		
		
		
		String apiURL = "http://api.data.go.kr/openapi/tn_pubr_public_weighted_envlp_api";		//스트링빌더로 써야 좋은거임
		
		
		try {
			
			String serviceKey = "l90YkDBFRArypcbpBJZ%2F68CPmtS67%2F2j2%2FjHiQM3AWKMJrwWZPcbI77qgQIRq8uABlAJV6DTp0IQEjZJtRLsmw%3D%3D";
			apiURL += "?pageNo";						//숫자는 그냥 보내도 상관없음 page 파라미터 이름임
			apiURL += "&numOfRows=100";
			apiURL += "&type=xml";
			apiURL += "&CTPRVN_NM=" + URLEncoder.encode("인천광역시", "UFR-8");
			apiURL += "&SIGNGU_NM=" + URLEncoder.encode("계양구",  "UTF-8"); 		// 주소에 요청이 다 들어가는 거임. 현재 data 데이터에서 필수/선택 여부를 알 수 없음
			apiURL += "&WEIGHTED_ENVLP_TYPE=" + URLEncoder.encode("규격봉투",  "UTF-8"); 		
			apiURL += "&WEIGHTED_ENVLP_MTHD=" + URLEncoder.encode("소각용",  "UTF-8"); 		
			apiURL += "&WEIGHTED_ENVLP_PRPOS=" + URLEncoder.encode("생활쓰레기",  "UTF-8"); 		
			apiURL += "&WEIGHTED_ENVLP_TRGET=" + URLEncoder.encode("기타",  "UTF-8"); 		
		
			
			
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//API주소 접속
		URL url = null;
		HttpURLConnection con = null;
		try {
			
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");		//	"application/xml이 부분은 데이터 타입 text/html도 있고 application/xml 도 있고  application/json도 있다 
																							// xml데이터 주세요 라는 뜻 (데이터 타입은 xml입니다)
			
		}catch (MalformedURLException e) {
			System.out.println(" API 주소 오류");
		}catch ( IOException e) {
			System.out.println(" API 접속 실패");
		}
		
		
		
		//입력 스트림(응답) 생성
		// 1. 응답 성송 시 정상 스트림, 실패 시 에러 스트림
		// 2. 응답 데이터는 String Builer에 저장
		BufferedReader reader = null;
		   StringBuilder sb = new StringBuilder();
		   
		   try {
		      if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
		         reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		         
		      }else {
		         reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      
		      String line = null;
		      while((line = reader.readLine()) != null) {
		         sb.append(line + "\n");
		      }
		      //스트림 종료
		      reader.close();
		         
		      
		   }catch(IOException e) {
		      System.out.println("API 응답 실패");
		   }
		   
		   String response = sb.toString();
		   System.out.println(response);
		   
		   //접속 종료
		   con.disconnect();
		
		
	}		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
