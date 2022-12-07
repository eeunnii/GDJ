package com.gdu.serch;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiSearchBook {
	
	public static void main(String[] args) {
		String clientId="_48qz3Z7EcTkgD6lEtVx";
		String clientSecret="_fp9SsfDX0";
		
		try {
	         Scanner sc = new Scanner(System.in);
	         String search = sc.next();
	         
	         String apiURL = "https://openapi.naver.com/v1/search/book?query=" + URLEncoder.encode(search, "UTF-8");
	         URL url = new URL(apiURL);
	         HttpURLConnection con = (HttpURLConnection)url.openConnection();
	         con.setRequestMethod("GET");
	         con.addRequestProperty("X-Naver-Client-Id", clientId);
	         con.addRequestProperty("X-Naver-Client-Secret", clientSecret);
	         
	         BufferedReader br = null;
	         if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
	            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	         } else {
	            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	         }
	         
	         StringBuilder sb = new StringBuilder();
	         String line;
	         while((line = br.readLine()) != null) {
	             sb.append(line);
	          }
	         
	         br.close();
	         sc.close();
	         con.disconnect();
	         
	         
	         
	         // sb에는 검색결과가 담겨있음// 데이터는 {}이런식으로 담겨있어서 JSON으로 변환해줘야함
	         
	         JSONObject obj = new JSONObject(sb.toString());
	         JSONArray items = obj.getJSONArray("items");
	         
	         
	         
	         
	         File dir = new File("C:/download");  // 시험문제에서 저장경로 나옴
	         if(dir.exists() == false) {
	        	 dir.mkdirs();
	         }
	         File file = new File("C:/download", search + "." + ".html"); // 파일의 이름은 검색어와 똑같음
	         PrintWriter out = new PrintWriter(file);
	         out.print("<!DOCTYPE html>");
	         out.print("<html>");
	         out.print("<head>");
	         out.print("<meta charset=\"?UTF-8\">");
	         out.print("<title>검색결과입니다</title>");
	         out.print("<body>");
	         
	         
	         for(int i=0; i<items.length(); i++) {
	        	 JSONObject item = items.getJSONObject(i);
	        	 String title = item.getString("title");
	        	 String link = item.getString("link");
	        	 String image = item.getString("image");
	        	 
	        	 
	        	 
	        	 out.print("<a href='"+link+"'><strong>"+title+"</strong></a>");
	        	 out.print("<br>");
	        	 out.print("<img alt = \"오류\" src = \""+image+"\" height=\"150px\">");
	        	 out.print("<hr>");
	        	 
	         }
	         
	        
	         
	         
	         out.print("</body>");
	         out.print("</html>");
	         out.close();
	         
	         
	         
	         

		}catch(Exception e) {
			
			
			try {
				Date date = new Date(System.currentTimeMillis());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a h:mm:ss");
				String now=sdf.format(date);
				
				File dir = new File("C:/download/log");
				if(dir.exists()==false) {
					dir.mkdirs();
				}
				File file = new File(dir, "error_log.txt");  // 파일명은 정해져있음
				PrintWriter out = new PrintWriter(file);
				out.println("예외메시지   "+e.getMessage());
				out.println("예외발생시간 "+now);
				out.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			
			}
			
		}
	}
	
	

}
