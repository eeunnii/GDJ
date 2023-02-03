package ex02_api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


//지금 배우고 있는거 : ★공공테이터 수집을 위한 객체지향 프로그램 구현


//요청( 어떤 데이터를 (처리)달라고 요청하는 것 )
// 클라이언트 -> 서버 
//요청할 주소 : apiURL
//요청 파라미터(서버로 보내줄 데이터)
// URL 문서 보면 필수/선택으로 보내야하는게 분리되어 있음
// apiURL? 파라미터=값&파라미터=값   ...     --> <파라미터=값&>  이 보내줘야하는 데이터

public class Main_XML {
	
	//알아야될 용어 
	// 요청
	// 1. Request
	// 2. 클라이언트 -> 서버 
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
			
			String serviceKey = "l90YkDBFRArypcbpBJZ/68CPmtS67/2j2/jHiQM3AWKMJrwWZPcbI77qgQIRq8uABlAJV6DTp0IQEjZJtRLsmw==";		//	 그리고 보통 인코딩데이터를 주소에 붙이면 누구나 뭔지 알 수 있으니까 디코딩 데이터를씀	.
																																// APT는 보통 디코딩 키 값을 제공함 
			
			
			
			apiURL += "?pageNo=" + URLEncoder.encode("0", "UTF-8");			//영문 ,숫자는 그냥 보내도 상관없음 page 파라미터 이름임
			apiURL += "&numOfRows=" + URLEncoder.encode("100", "UTF-8");
			apiURL += "&type=" + URLEncoder.encode("xml", "UTF-8");
			apiURL += "&CTPRVN_NM=" + URLEncoder.encode("인천광역시", "UTF-8");
			apiURL += "&SIGNGU_NM=" + URLEncoder.encode("계양구", "UTF-8");		// 주소에 요청이 다 들어가는 거임. 현재 data 데이터에서 필수/선택 여부를 알 수 없음
			apiURL += "&WEIGHTED_ENVLP_TYPE=" + URLEncoder.encode("규격봉투", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_MTHD=" + URLEncoder.encode("소각용", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_PRPOS=" + URLEncoder.encode("생활쓰레기", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_TRGET=" + URLEncoder.encode("기타", "UTF-8");
			apiURL += "&serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8");				// 서버측에서 디코딩이 기다리고 있어서 디코
			
			
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
		      while((line = reader.readLine()) != null) {		//readLine 버퍼리더에서 지원하는 메소드
		         sb.append(line + "\n");
		      }
		      //스트림 종료
		      reader.close();
		         
		      
		   }catch(IOException e) {
		      System.out.println("API 응답 실패");
		   }
		   
		   
		   // api로부터 전달받은 xml 데이터
		   String response = sb.toString();				//	 최종적인 응답데이터		
		   
		   //file생성
		   File file = new File("c:\\storage", "api1.xml");
		   
		   try {
			   BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			   bw.write(response);
			   bw.close();
			   
		   }catch (IOException e) {
			   e.printStackTrace();
		   }
		   
		   
		   //xml 분석 
		   
		   try {
			   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(file);
				
				Element root = doc.getDocumentElement();   // <response> (최상위 태그)
				System.out.println(root.getNodeName());
				
				NodeList nodeList = root.getChildNodes();  // <response>의 자식 태그(<header>, <body>)
				for(int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);          // <header>와 <body>
					System.out.println("  " + node.getNodeName());
					NodeList nodeList2 = node.getChildNodes();        // <header>의 자식 태그(<resultCode>, <resultMsg>), <body>의 자식 태그(<items>, <numOfRows>, <pageNo>, <totalCount>)
					for(int j = 0; j < nodeList2.getLength(); j++) {
						Node node2 = nodeList2.item(j);
						System.out.println("    " + node2.getNodeName());
						if(node2.getNodeName().equals("items")) {     // <items> 태그 대상
							NodeList items = node2.getChildNodes();   // <items>의 자식 태그(<item>)
							for(int k = 0; k < items.getLength(); k++) {
								Node item = items.item(k);
								System.out.println("      " + item.getNodeName());
								NodeList itemChildren = item.getChildNodes();        // <item>의 자식 태그
								for(int l = 0; l < itemChildren.getLength(); l++) {
									Node itemChild = itemChildren.item(l);
									System.out.println("        " + itemChild.getNodeName() + ":" + itemChild.getTextContent());
								}
							}
						}
					}
					
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			// 접속 종료
			con.disconnect();
			
		}
	
	
	public static void m2() {
		
		StringBuilder urlBuilder = new StringBuilder();
		String serviceKey = "bEQBRPHjt0tZrc7EsL0T8usfsZ1+wT+5jqamBef/ErC/5ZO6N7nYdRmrwR91bh5d3I1AQeY5qdbJOF6Kv0U1CQ==";

		try {

			urlBuilder.append("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson");
			urlBuilder.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			// sb.append("?serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8")); // 내가 한거
			urlBuilder.append("&startCreateDt=20220808");
			urlBuilder.append("&endCreateDt=20220812");
			// sb.append("&startCreateDt=" + URLEncoder.encode("20220808", "UTF-8"));
			// sb.append("&endCreateDt=" + URLEncoder.encode("20220812", "UTF-8"));

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String apiURL = urlBuilder.toString();

		// API 주소 접속
		URL url = null;
		HttpURLConnection con = null;

		try {

			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");

		} catch (MalformedURLException e) {
			System.out.println(" API 주소 오류");
		} catch (IOException e) {
			System.out.println(" API 접속 실패 ");
		}

		// 입력 스트림(응답) 생성

		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();

		try {
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new java.io.InputStreamReader(con.getInputStream()));

			} else {
				reader = new BufferedReader(new java.io.InputStreamReader(con.getErrorStream()));

			}

			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("API 응답 실패");
		}

		// api로부터 전달받은 xml 데이터
		String response = sb.toString(); // 최종적인 응답데이터

		// xml 파싱
		File file = new File("c:\\storage", "api2.xml");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
		
	}
	
	
	public static void m3() {
		File file = new File("c:\\storage", "api2.xml");

		try {
			// apr2.xml 문서 -> doc 객체
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);

			/// apr2. xml 문서의 최상위 태그 -> root
			Element root = doc.getDocumentElement();

			StringBuilder sb = new StringBuilder();

			// <item>..<item> 태그 하나 == 특정 날짜의 데이터
			NodeList items = root.getElementsByTagName("item");
			// 태그 이름으로 요소 찾기 ↑
			// getElements 여기에 S 있는데 S있으면 ?? 중요!! 이해하기 -- 스크립트에서 계속 씀
			for (int i = 0; i < items.getLength(); i++) {
				Node item = items.item(i); // 컬렉션의 get과 기능이 같음
				NodeList itemChildren = item.getChildNodes();

				for (int j = 0; j < itemChildren.getLength(); j++) {
					Node itemChild = itemChildren.item(j);
					if (itemChild.getNodeName().equals("stateDt")) {
						sb.append(" 날짜 : ").append(itemChild.getTextContent());
					}
					if (itemChild.getNodeName().equals("stateDt")) {
						sb.append(" 확진자수 : ").append(itemChild.getTextContent());
					}
					if (itemChild.getNodeName().equals("stateDt")) {
						sb.append(" 사망자 : ").append(itemChild.getTextContent());
					}

					// Node stateDt = itemChildren.item(4); // 5번째 노드

					// Node stateDt == <stateDt>20220812</stateDt>
					// stateDt.getNodeName() == stateDt (태그이름)
					// stateDt.getTextContent() == 20220812(태그내부텍스트)

					// System.out.println(stateDt.getTextContent());
				}
				sb.append("\n");
			}
			System.out.println(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 줄바꿈이 포함되어있으면 줄바꿈도 요소에 들어감
	}
	
	
	public static void m10() {
		File file = new File("C:\\storage", "sfc_web_map.xml");
		try {
			
			StringBuilder sb = new StringBuilder();
			
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builer = factory.newDocumentBuilder();
			Document doc = builer.parse(file);
			
			Element root = doc.getDocumentElement();
			
			Element weather =(Element)root.getElementsByTagName("weather").item(0);       // <weather year="2022" month="08" day="16" hour="09">
			sb.append(weather.getAttribute("year")+"년 ");
			sb.append(weather.getAttribute("month")+"월 ");
			sb.append(weather.getAttribute("day")+"일 ");
			sb.append(weather.getAttribute("hour")+"시\n ");
			
			NodeList locals = root.getElementsByTagName("local");
			for(int i = 0; i<locals.getLength(); i++) {
				Element local = (Element)locals.item(i);
				sb.append(local.getTextContent()+ ":");
				sb.append(local.getAttribute("ta")+"℃");
				sb.append(local.getAttribute("desc")+ "\n");		//지역 하나당 줄바꿈 하나씩 먹음
			}
			
			System.out.println(sb.toString());
			
			
			
			System.out.println(root.getNodeName());
			System.out.println(root.getAttribute("xmlns"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
		
		public static void main(String[] args) {
			m10();
		}
}