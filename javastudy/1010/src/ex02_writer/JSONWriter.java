package ex02_writer;

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
		
		 
		JSONObject obj = new JSONObject();
		obj.put("name" , "정은지");
		obj.put("age", 34);
		obj.put("woman", true);
		obj.put("height", 233);
		
		
		System.out.println(obj.toString());
		
		
		
		
	}
	
	public static void m2() {
		JSONObject obj1= new JSONObject();
		obj1.put("name", "제임스");
		obj1.put("age", "30");
		
		JSONObject obj2= new JSONObject();
		obj1.put("name", "에밀리");
		obj1.put("age", "49");

		JSONArray arr= new JSONArray();
		arr.put(obj1);
		arr.put(obj2);
		System.out.println(arr.toString());
		
	}
	
	
	public static void m3() {
		String str = "{\"woman\":true,\"name\":\"정은지\",\"age\":34,\"height\":233}\r\n";
		JSONObject obj = new JSONObject(str);
		String name = obj.getString("name");
		boolean man = obj.getBoolean("woman");
		int age = obj.getInt("age");
		double height =obj.getDouble("height");
		
		System.out.println(name);
		System.out.println(man);
		System.out.println(age);
		System.out.println(height);
	}

	public static void main(String[] args) {
		
		
		m1();
		
		
		

	}

}
