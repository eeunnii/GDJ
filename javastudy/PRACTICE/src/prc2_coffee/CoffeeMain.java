package prc2_coffee;

import java.util.Scanner;

public class CoffeeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Scanner sc = new Scanner(System.in);
		
	
		System.out.print("무슨 커피 드릴까요? >> ");
		
		String menu = sc.next();

		
		switch (menu) {
		case "에스프레소" : System.out.println("에스프레소는 3500원입니다."); break;
		case "아메리카노" : System.out.println("아메리카노는 3500원입니다."); break;
		case "밀크커피" : System.out.println("밀크커피는 3500원입니다."); break;
		}
		
		
	
		
	}

}
