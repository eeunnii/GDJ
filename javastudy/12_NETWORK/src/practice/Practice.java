package practice;

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

public class Practice {
	
	public static void m1() {
		
		StringBuilder builderurl = new StringBuilder();
		String serviceKey = "l90YkDBFRArypcbpBJZ/68CPmtS67/2j2/jHiQM3AWKMJrwWZPcbI77qgQIRq8uABlAJV6DTp0IQEjZJtRLsmw==";
		
		try {
			
			builderurl.append("http://api.data.go.kr/openapi/tn_pubr_public_weighted_envlp_api");
			builderurl.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			builderurl.append("&pageNo=0");
			builderurl.append("&numOfRows=100");
			builderurl.append("&type=").append(URLEncoder.encode("xml" , "UTF-8"));
			builderurl.append("&CTPRVN_NM=").append(URLEncoder.encode("인천광역시" , "UTF-8"));
			builderurl.append("&SIGNGU_NM=").append(URLEncoder.encode("계양구", "UTF-8"));
			builderurl.append("&WEIGHTED_ENVLP_TYPE=").append(URLEncoder.encode("규격봉투", "UTF-8"));
			builderurl.append("&WEIGHTED_ENVLP_MTHD=").append(URLEncoder.encode("소각용", "UTF-8"));
			builderurl.append("&WEIGHTED_ENVLP_PRPOS=").append(URLEncoder.encode("생활쓰레기", "UTF-8"));
			builderurl.append("&WEIGHTED_ENVLP_TRGET=").append(URLEncoder.encode("기타", "UTF-8"));
		
		
			
		}catch(UnsupportedEncodingException e) {
			System.out.println("인코딩 실패");
		}
		
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			url = new URL(builderurl.toString());
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
			
		}catch (MalformedURLException e) {
			System.out.println("API 주소 오류");
		}catch (IOException e) {
			System.out.println("API 주소 실패");
		}
		
		
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				
			}else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String line = null;
			while((line=reader.readLine())!=null) {
				sb.append(line + "\n");
			}
			reader.close();
						
		}catch(IOException e) {
			System.out.println("API응답실패");
		}
		
		String response = sb.toString();
		
		File file = new File("C:\\storage","api11.xml");
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);
			bw.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		con.disconnect();
		
	}
	
	public static void m2() {
			
		File file = new File("C:\\storage","api11.xml");
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			Element root = doc.getDocumentElement();
			System.out.println(root.getNodeName());
			NodeList list1 = root.getChildNodes();		// 리수트 - 헤더 바디
			for (int i = 0; i<list1.getLength(); i++ ) {
				Node node1 = list1.item(i);			//헤더 바디
				System.out.println("  "+node1.getNodeName());
				NodeList list2 = node1.getChildNodes(); // 헤더 바디의 노드리스트
				for(int j =0; j<list2.getLength(); j++) {
					Node node2 = list2.item(j);
					System.out.println("     "+node2.getNodeName());
					NodeList list3 = node2.getChildNodes();
					for (int k =0 ; k<list3.getLength();k++) {
						Node node3 = list3.item(k);
						System.out.println("      "+node3.getNodeName()+"z");
					}
				}
				
				
			}
			
			
			
		}catch(Exception e) {
			System.out.println("접속오류");
		}
		
		
		
	}
	
	
	public static void main(String[] args) {
		m2();
	}
	

}
