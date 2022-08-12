package ex01_network;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
	
	public static void m1() {
		
		//URL 
		// 1. Uniform Resource Locator : 
		// 2. 정형화된 자원의 경로  // 자원의 경로를 표기할 때 형식을 갖춰서 표기하자!!
		// 3. 웹 주소를 의미
		// 4. 구성
		// https : 프로토콜 
		// search.naver.com 호스트
		// search.naver 서버경로
		// ? 의 뒷부분은 파라미터 = 값 (전송하고자하는 데이터) &를 이용해 연결함
		//    https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%9E%90%EB%B0%94+%ED%8C%8C%EC%9D%BC+%EB%B3%B5%EC%82%AC+&oquery=%EC%8A%A4%EC%BA%94%EA%B8%B0&tqi=hYTrMsprvxZsshg9hldssssstOG-268285
		// 1) https : secure http, 하이퍼텍스트 전송프로토콜(통신규약)
		// 2) 호스트  : 서버주소.(도메인)    //local.host를 많이 쓸 예정
		// 3) 서버경로 : URL Mapping 
		// 4) 파라미터 : 서버로 전송하는 데이터
		
		
		
		
		 try {
		      // URL 처리를 위한 URL 클래스
		            String apiURL="https://search.naver.com/search.naver?query=날씨";
		            URL url=new URL(apiURL);

		            System.out.println("프로토콜:"+url.getProtocol());
		            System.out.println("호스트:"+url.getHost());
		            System.out.println("파라미터:"+url.getQuery());
		            
		      }catch(MalformedURLException e) {
		         System.out.println("API 주소 오류");
		      }
		      
		      
		      
		      
		   }
		   
	public static void m2() {
		      
		      
		      // ★ 외우기
		      
		      // HttpURLConnection 클래스
		      // 1.웹 접속을 담당하는 클래스
		      // 2.URL 클래스와 함께 사용
		      // 3.URLConnection 클래스의 자식 클래스
		      // 4.URL 클래스의 openConnection() 메소드를 호출해서 HttpURLConnection 클래스 타입으로 저장
		      
		      try {
		         
		         String apiURL="https://www.google.com";
		         URL url=new URL(apiURL);
		         
		         HttpURLConnection con=(HttpURLConnection)url.openConnection(); // 여기까지 외워야함  // HttpURLConnection 컨트롤 누르고 클릭해보면 에러종류를 볼 수 있음 404, 200 등등
		         
		         
		         
		         
		         // HTTP 응답 코드
		         // 1.200:정상
		         // 2.400~(사백번대):요청이 잘못됨(사용자 잘못)
		         // 3.500~(오백번대):서버 오류

		         System.out.println("응답 코드:"+con.getResponseCode());                    //멀리가지말고
		         System.out.println("정상 : "+HttpURLConnection.HTTP_OK);
		         System.out.println("컨텐트 타입:"+con.getContentType());
		         System.out.println("Not Found : "+HttpURLConnection.HTTP_NOT_FOUND);
		         System.out.println("Internal Error"+HttpURLConnection.HTTP_INTERNAL_ERROR);
		         System.out.println("요청 방식 : " + con.getRequestMethod());				//여기서부터 공부하기 
		         con.disconnect(); // 접속 해제 (생략 가능)
		         
		      }catch(MalformedURLException e) { // URL 클래스
		         System.out.println("API 주소 오류");
		      }catch(IOException e) { // HrrpURLConnection 클래스
		         System.out.println("API 접속 오류");
		      }
		      
		      
		      
		   }
		   
	public static void m3() {
		//HttpURLConnection과 스트림
		
		try {
			String apiURL = "http://www.naver.com";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
							
			
			
			//바이트 입력 스트림
			InputStream in = con.getInputStream();  //인풋 스트림으로 끝나면 바이트 스트림임 (입력스트림)
			
			//문자 입력 스트림으로 변환
			InputStreamReader reader = new InputStreamReader(in);
			
			//모두 읽어서 StringBuilder에 저장 
			StringBuilder sb = new StringBuilder();
			char[] cbuf = new char[100];		//100글자씩 처리
			int readCnt = 0; //실제로 읽은 글자 수 
			while((readCnt = reader.read(cbuf)) != -1) {
				sb.append(cbuf, 0, readCnt);		//모든 내용 저장
			}
			
			//StringBuiler의 모든 내용을 C:\\stroage\\naver.com 로 내보내기
			File file = new File("c:\\storage" , "naver.html");
			FileWriter fw = new FileWriter(file);
			
			fw.write(sb.toString());
			
			fw.close();
			reader.close();
			con.disconnect();
			
			
		}catch(MalformedURLException e) {
			System.out.println("API 주소 오류");
		}catch(IOException e) {
			System.out.println("API 접속 및 연결 오류");
		}
	}
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   public static void main(String[] args) {
		      
		      m3();
		      
		   }

		}