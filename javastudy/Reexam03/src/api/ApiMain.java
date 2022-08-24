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

public class ApiMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		try {
			
			String apiURL = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1154551000";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			 
			while((line = br.readLine())!=null) {
				sb.append(line);
			}
			
			File file = new File("weather.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
			
			JSONObject obj = XML.toJSONObject(sb.toString());
			
			
			
			JSONObject rss = obj.getJSONObject("rss");
			JSONObject channel = rss.getJSONObject("channel");
			String pubDate = channel.getString("pubDate");
			
			
			
			JSONObject item = channel.getJSONObject("item");
			String category = item.getString("category");
			
			
			JSONObject description = item.getJSONObject("description");
			JSONObject body = description.getJSONObject("body");
			JSONArray arrylist = body.getJSONArray("data");
			
			
			
			bw.write(pubDate+'\n');
			bw.write(category+'\n');
							
			
		      for(int i = 0; i < arrylist.length(); i++) {
			  JSONObject data = arrylist.getJSONObject(i);
			  int temp = data.getInt("temp");
			  String wfKor = data.getString("wfKor");
			  int hour = data.getInt("hour");
			  bw.write(hour + "시, " + temp + "도, " + wfKor + "\n");
			      }
			
			
			
				bw.close();
				br.close();
				con.disconnect();
			
		
			
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		

}
}
