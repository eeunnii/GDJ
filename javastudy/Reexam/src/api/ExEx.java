package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExEx {
	public static void main(String[] args) {
		
		
		try {
			
			String apiURL = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4139055000";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
}
}
