package coffee;

public class Coffee {
	
	
	String getCoffee(int money, int button) {
		String[] menu = {"아메", "라떼"};
		return menu[button-1]+" "+(money/1000)+"잔";
		
	}
	
	
	

}
