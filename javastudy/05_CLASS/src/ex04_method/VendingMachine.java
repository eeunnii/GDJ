package ex04_method;

public class VendingMachine {
	
	
	// 커피 뽑기 시나리오
	// 한 잔 1000원
	// 종류 : 1(아메리카노), 2(카페라떼)
	
	
	
	

	String getCoffee(int money, int button) {
		
		String[] menu = {"아메리카노", "카페라떼"};
		return menu[button - 1] + " " + (money / 1000) + "잔";

		// String[] menu = {"", "아메리카노", "카페라떼"};
		// return menu[button] + " " + (money / 1000) + "잔";
		
	}
	
	
	
	
	
	
	
	
	
}
