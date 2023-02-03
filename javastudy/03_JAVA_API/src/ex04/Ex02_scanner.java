package ex04;

import java.util.Scanner;

public class Ex02_scanner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	

		//java.util.Scanner 클래스 
		
		//데이터 타입별로 입력 받을 수 있는 메소드를 제공함.
		//Int : nextInt()
		//long : nextLong()
		//double: nextDouble()
		//String:nextLine (공백포함입력, next() 공백 포함 
		
		Scanner sc = new Scanner(System.in);                //자바 유틸의 스캐너 
		                                                    //객체 sc는 System.in(키보드)으로부터 입력을 받는다.
		System.out.print("이름을 입력하세요 >>>");
		String name = sc.next();
		
		System.out.println("나이를 입력하세요 >>>");
		int age = sc.nextInt();
		
		System.out.println(name);
		System.out.println(age);
		
		//sc.close();  //생략이 가능하다.
		
		//연습 문자열 'abc12345'
		
		
		//연습, 성별을 입력 받기 
		System.out.println("성별(남/여)를 입력하세요 >>> ");
		String gender = sc.next();
		
		System.out.println(gender);
	
		
		

		
		
		
		
		
	}

}
