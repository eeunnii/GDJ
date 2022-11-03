package com.gdu.app05.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MovieServiceImpl implements MovieService {
	
	
	
	
	
	@Override
	public String getBoxOffice(String targerDt) {
		
		String key = "3162521e5cdc63f914f0403d8b7bbec3";
		String apiURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key="+key+"&targetDt="+targerDt;
		
		
		// 영화홈페이지에 GET으로 하라고 명시되어있음
		
		// API 요청
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL); // malformedURLException
			con = (HttpURLConnection)url.openConnection();  // IOException 발생가능
			con.setRequestMethod("GET"); // get 대문자로 지정해야함
			
			
			
			
			
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		// API 응답
		StringBuilder sb = new StringBuilder();
		try(BufferedInputStream reader = new BufferedReader(new InputStreamReader(con.getInputStream()))){ // try -catch-resources문은 자원의 close를 생략할 수 있다..
			String line = null;
			while((line=reader.readLine())!=null) {
				
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// con 닫기 
		con.disconnect();
		
		// 반환 (API로부터 가져온 모든 텍스트 정보)
		return sb.toString();
		
		
		
		
		return null;
	}
	

}
