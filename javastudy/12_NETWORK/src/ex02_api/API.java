package ex02_api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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


// 줄바꿈 : 노드에 포함
// 줄바꿈 : 엘러먼트에 포함 X
// 엘러먼트는 태그만!
// 모든 엘러먼트 들은 getElementBytagName() 쓸수 있다. 근데 node에서 지원안함 
//노드 : 부모 엘러번트 : 자식
//noed -> element
// 부모 -> 자식 (downcasting)
// 노드상태에서 getElementBytagName() 못씀
// 그래서 노드를 자식인 엘러먼트로 다운캐스팅 한 후 getElementBytagName() 쓸 수 있음

public class API {

	public static void m3() {

		StringBuilder urlBuilder = new StringBuilder();
		String serviceKey = "bEQBRPHjt0tZrc7EsL0T8usfsZ1f+wT+5jqamBef/ErC/5ZO6N7nYdRmrwR91bh5d3I1AQeY5qdbJOF6Kv0U1CQ==";

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

	public static void m4() {

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
	}

	// 줄바꿈이 포함되어있으면 줄바꿈도 요소에 들어감

	public static void m5() {
		
		StringBuilder sb = new StringBuilder();
		String serviceKey = "l90YkDBFRArypcbpBJZ/68CPmtS67/2j2/jHiQM3AWKMJrwWZPcbI77qgQIRq8uABlAJV6DTp0IQEjZJtRLsmw==";
		
		
		try {
			
			sb.append("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0");
			sb.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UFR-8"));
			sb.append("&numOfRows=10");
			sb.append("&pageNo=1");
			sb.append("&base_date=20210628");
			sb.append("&base_time=0600");
			sb.append("&nx=55");
			sb.append("&ny=127");
	
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String apiURL = sb.toString();
		
		
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
			
		}catch ( MalformedURLException e) {
			System.out.println( "API 주소 오류");
		}catch( Exception e) {
			System.out.println( "API 접속 실패");
		}
		
		
		BufferedReader bf = null;
	
		
		
		
		
	}
	
	public static void m6() {
	File file = new File("C:\\storage", "api3.xml");
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			Element root = doc.getDocumentElement();
			
			NodeList items = root.getElementsByTagName("item");
			for(int i = 0; i < items.getLength(); i++) {
				Element item = (Element)items.item(i);  // Node -> Element 타입으로 다운캐스팅
				Node category = item.getElementsByTagName("category").item(0);
				Node obsrValue = item.getElementsByTagName("obsrValue").item(0);
				String strCategory = null;
				switch(category.getTextContent()) {
				case "PTY": strCategory = "강수형태"; break;
				case "REH": strCategory = "습도"; break;
				case "RN1": strCategory = "강수량(1시간)"; break;
				case "T1H": strCategory = "기온"; break;
				case "UUU": strCategory = "동서바람성분"; break;
				case "VEC": strCategory = "풍향"; break;
				case "VVV": strCategory = "남북바람성분"; break;
				case "WSD": strCategory = "풍속"; break;
				}
				System.out.println(strCategory + ":" + obsrValue.getTextContent());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {

		m3();

	}

}
