package ex04_map;

import java.util.HashMap;
import java.util.Map;

public class Main {

public static void m1() {
	// Map 생성 
	Map<String,String> dictionary = new HashMap<String, String>();
	
	
	//추가
	//새로운 키값을 사용하면 추가
	dictionary.put("apple", "사과"); // put(Key, value)
	dictionary.put("banana", "바나나"); 
	dictionary.put("tomato", "토마토"); 
	dictionary.put("mango", "망고"); 
	dictionary.put("melon", "멜론"); 
	
	//수정
	//기존의 key값을 사용하면 수정
	dictionary.put("melon", "메론");
	
	//삭제 
	//삭제할 요소의 key를 전달하면 삭제됨
	//삭제된 value가 반환됨
	String removeItem = dictionary.remove("tomato");
	System.out.println(removeItem);  //토마토
	
	//값(Value) 확인
	System.out.println(dictionary.get("apple")); 	//사과
	System.out.println(dictionary.get("peach"));
			
	
	
	//확인
	System.out.println(dictionary);
}

public static void m2() {
	
	//Value를 String으로 관리 
	Map<String, String> map1 = new HashMap<String, String>();		//그다음엔 이거 
	map1.put("title", "어린왕자");
	map1.put("auther", "생택쥐베리");
	map1.put("price", 10000+"");
	System.out.println(map1);
	
	Map<String, Object> map2 = new HashMap<String, Object>();          // 실무에서 젤 많이 보는 맵
	map2.put("author", "허균");
	map2.put("price", 20000);
	System.out.println(map2);
}

public static void m3() {
	
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("title", "소나기");
	map.put("author", "황순원");
	map.put("price", 2000);
	
	
	
	//Entry 단위로 순회 (for)   <<< 공부는 주로 얘로 하고 
	
	
	//Key를 이용한 순회(for)	<< 가능하다는 것 정도 알기 
//	for(String key : map.keySet()) {
//		System.out.println(entry.);
//	}
//	
	
}	
	
public static void m4() {
		//연습
		//title, author, price 정보를 가진 임의의 map 3개를 만들고, 
		//생성된 Map 3개를 ArrayList에 저장한 뒤
		//ArrayList에 저장된 Map 3개를 for문으로 순회하시오.
	
		for(Map<String,Object> map : list) {
			
		}
	
	
	
	
	
	}
		
	

 

public static void main(String[] args) {
	m1();
}
}
