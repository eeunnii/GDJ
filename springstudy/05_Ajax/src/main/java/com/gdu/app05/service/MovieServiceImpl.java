package com.gdu.app05.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MovieServiceImpl implements MovieService {
	
	
	
	
	
	@Override
	public String getBoxOffice(String targerDt) {
		
		String key = "3162521e5cdc63f914f0403d8b7bbec3";
		String apiURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key="+key+"&targetDt="+targerDt;
		StringBuilder sb = null;
		
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			BufferedReader br = null;
			if(con.getResponseCode() == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			sb = new StringBuilder();
			sb.append()
			
		}
		
		
		
		return null;
	}
	

}
