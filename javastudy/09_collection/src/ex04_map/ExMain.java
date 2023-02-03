package ex04_map;

import java.util.HashMap;
import java.util.Map;

public class ExMain {
	
	public static void m1() {
		Map<String, String> a1 = new HashMap<String, String>();
		
		a1.put("딸", "기");
		a1.put("포", "모");
		a1.put("사", "과");
		
		a1.put("포", "도");
		
		String remove = a1.remove("사");
		System.out.println(remove);
		
	}
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			m1();
	}

}
