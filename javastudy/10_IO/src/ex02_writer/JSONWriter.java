package ex02_writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONWriter {
	
	public static void m1 () {
		//Json
		// 1. JavaScript Object Notation
		// 2. 자바 스크립트 객체 표기법
		// 3. 객체 :  { } 중괄호로 묶어줌
		// 4. 배열은 : [ ] 대괄호로 묶어줌
		
		//  JSON-Java(JSON in Java)라이브러리
		// ★1. 객체 : JSONObject 클래스 ( Map 기반으로 제작됨 = 사용법이 거의 같다)
		// 2. 배열 : JSONArray 클래스 ( List 기반 )
		
		//key와 value로 인식해도 전혀 문제가 없음 
		
		 
		JSONObject obj = new JSONObject();
		obj.put("name" , "정은지");
		obj.put("age", 34);
		obj.put("woman", true);
		obj.put("height", 233);
		
		
		System.out.println(obj.toString());
		
		
		
		
	}
	
	public static void m2() {
		JSONObject obj1 = new JSONObject();
		obj1.put("name", "제임스");
		obj1.put("age", 30);
		
		JSONObject obj2 = new JSONObject();
		obj2.put("name", "에밀리");
		obj2.put("age", 40);
		
		JSONArray arr = new JSONArray();
		arr.put(obj1);
		arr.put(obj2);
		
		System.out.println(arr.toString());
		
	}
	
	
	public static void m3() {
		/// JSON을 읽어보는 연습
		String str = "{\"woman\":true,\"name\":\"정은지\",\"age\":34,\"height\":233}\r\n";
		
		//▲만들어져있는 제이슨 데이터를 오브젝으로 읽어들여서 인식시킨다음에 
		JSONObject obj = new JSONObject(str);
		String name = obj.getString("name");
		boolean man = obj.getBoolean("woman");
		int age = obj.getInt("age");
		double height =obj.getDouble("height");
		//▲타입과 결부된 데이터 
		//JDBC가 뭔지 찾아보기 
		
		System.out.println(name);
		System.out.println(man);
		System.out.println(age);
		System.out.println(height);
	}
	
	
	public static void m4() {
		String str = "[{\"name\":\"제임스\",\"age\":30},{\"name\":\"에밀리\",\"age\":40}]";
		JSONArray arr = new JSONArray(str);
		
		//일반 for문 
		for(int i = 0, length = arr.length(); i < length; i++) {
			JSONObject obj = arr.getJSONObject(i);
			String name = obj.getString("name");
			int age = obj.getInt("age");
			System.out.println(name + "," + age);
		}
		
		
			
		//항상 for 문(★)	
			for(Object o : arr) {
				JSONObject obj = (JSONObject)o;
				String name = obj.getString("name");
				int age = obj.getInt("age");
				System.out.println(name + "," + age);
			}
			
			
		
		
		
				
	}

	public static void main(String[] args) {
		
		
		List<String> product1 = Arrays.asList("100", "새우깡", "1500");
		List<String> product2 = Arrays.asList("101", "양파링", "2000");
		List<String> product3 = Arrays.asList("102", "홈런볼", "3000");
		
		List<List<String>> list = Arrays.asList(product1, product2, product3);
		
	
		//list를 json String으로 만들어서 
		//C:\\storage\\product.json 파일에 writer;

		
		JSONArray arr = new JSONArray();

		for(List<String> line : list) {
			JSONObject obj = new JSONObject();
			obj.put("number", line.get(0));
			obj.put("name", false);
			obj.put("price", line.get(2));
			arr.put(obj);
			
		}
		
		File file = new File("C:\\storage", "product.json");
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(arr.toString());
		} catch  (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw !=null) {
					bw.close();
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
				  
			  }
		
		
		

	}

}
