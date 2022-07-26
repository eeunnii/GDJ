package ex01_network;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Main {
	
	public static void m1() {
		
		
		
		
		//URL 
		// 1. Uniform Resource Locator : 
		// 2. 정형화된 자원의 경로  // 자원의 경로를 표기할 때 형식을 갖춰서 표기하자!!
		// 3. 웹 주소를 의미
		// 4. 구성
		// https : 프로토콜 
		// search.naver.com 호스트 : 포트번호 (생략가능) // 정해진 포트번호가 있다(?)
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
		         System.out.println("정상 : "+HttpURLConnection.HTTP_OK);			//HTTP_OK 정상 200
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
		
		
		//★ 암기해야되는 부분
		//server측의 정보를 내려 받는게  stream in임. 
		//inputStream(바이트)
		//서버측에서 내려받고 싶은게 텍스트면 inputStreamReader(char)로 바꿔줘야함 
		//HttpURLConnection과 스트림
		
		try {
			String apiURL = "http://www.naver.com";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
							
			
			
			//바이트 입력 스트림
			InputStream in = con.getInputStream();  //인풋 스트림으로 끝나면 바이트 스트림임 (입력스트림)
			
			//문자 입력 스트림으로 변환
			InputStreamReader reader = new InputStreamReader(in);
			
			
			//바이트 입력 + 문자입력스트림으로 변환하는 거 한줄로 
			//InputStreamReader reader1 = new InputStreamReader(con.getInputStream());
			
			
			
			
			
			
			
			//모두 읽어서(웹페이지의 텍스트를) StringBuilder에 저장 
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
		   
	public static void m4() {
		
		// 인코딩 디코딩하는 방법을 볼거임
		// 인코딩 : UTF -8 방식으로 암호화 
		// 디코딩 : UTF -8 방식으로 복호화
		// 원본테이너 -> 인코딩 -> 전송 -> 디코딩 -> 원본데이터
		// 실제보내는 건 안하고 인코딩, 디코딩만 볼거임
		
		try {
			
			//원본데이터
			String str = "한글 english 1234 !@#$+"; // 출력해보면 영어랑 숫자는 그냥 사는데 한글이랑 특수문자가 인코딩됨. 공백은 +기호로 인코팅
													// + 인코딩은 %2B로 된거 확인할 수 있음
			//인코딩
			String encode = URLEncoder.encode(str, "UTF-8"); // UTF-8 : 인코딩 방식
			System.out.println(encode);
			
			//디코딩  (인코딩된 데이터를 디코딩하는거임)
			String decode = URLDecoder.decode(encode, StandardCharsets.UTF_8);
			System.out.println(decode);
			
			
			
			
		}catch (UnsupportedEncodingException e) {		//디코딩입센션은 없음 
			e.printStackTrace();
		}
		
	}
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   public static void main(String[] args) {
		      
		      m4();
		      
		   }

		}