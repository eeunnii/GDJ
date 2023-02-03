package api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//xml 파싱
		// api 응답(xml) -> xml 파일 ->documnet
		
		//json 파싱
		//api 응답(JSON) -> JSONOBJECT  >> xml와 달리 xml파일생성과정이 필요하지 않다.
		
		
		try {
			
			
			
			String apiURL = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1154551000"; 		//기상청rss 파라미터를 붙일게 없음 지역 하나밖에 없음 (?뒤에보기) - 인코딩 할 필요가 없음.. 왜?
			URL url = new URL(apiURL);															// 주소에 파라미터 붙이는게 get임?
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			//con.setRequestMethod("GET");
			//con.setRequestProperty("Content-Type", "application/xml; charet=UTF-8" );
			
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			
			while((line = br.readLine())!=null) {
				sb.append(line);
			}
			
			// 파싱결과를 저장할 파일
			File file = new File("test.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
			//StringBuilder에 저장된 응답(XML)데이터를 JSON으로 변경하기 channel가지고 뽑을 수 있는거 전체 파싱
			  JSONObject obj = XML.toJSONObject(sb.toString());
		      JSONObject rss = obj.getJSONObject("rss");
		      JSONObject channel = rss.getJSONObject("channel");
		      String link = channel.getString("link");
		      String description = channel.getString("description");
		      String generator = channel.getString("generator");
		      String language = channel.getString("language");
		      String title = channel.getString("title");
		      String pubDate = channel.getString("pubDate");
		      bw.write(pubDate + "\n");
		      JSONObject item = channel.getJSONObject("item");
		      String author = item.getString("author");
		      String link2 = item.getString("link");
		      String category = item.getString("category");
		      bw.write(category + "\n");
		      String title2 = item.getString("title");
		      JSONObject description2 = item.getJSONObject("description");
		      JSONObject body = description2.getJSONObject("body");
		      JSONArray dataList = body.getJSONArray("data");
		      
		      for(int i = 0; i < dataList.length(); i++) {
		         JSONObject data = dataList.getJSONObject(i);
		         int sky = data.getInt("sky");
		         int temp = data.getInt("temp");
		         String wfKor = data.getString("wfKor");
		         int wd = data.getInt("wd");
		         int hour = data.getInt("hour");
		         bw.write(hour + "시, " + temp + "도, " + wfKor + "\n");
		      }
		      

			
			
			
			
			
			
			
			
			
	

	}catch(Exception e) {
		e.printStackTrace();
	}

}
}
