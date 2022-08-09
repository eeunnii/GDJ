package ex01_list;

import java.util.ArrayList;
import java.util.List;



public class exMain {
	
	public static void m1() {
		List<String> list = new ArrayList<String>();
		
		list.add("월");
		list.add("화");
		
		boolean result = list.remove("일");
		System.out.println(result);
		String removeItemString = list.remove(1);
		System.out.println(removeItemString);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		m1();
	}

}
