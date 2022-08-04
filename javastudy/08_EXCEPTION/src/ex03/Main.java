package ex03;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		
		
		//finally 블록
		// try-catch문 마지마게 추가하는 블록
		//언제나 마지막에 실행되는 블록
		//생략가능
		
		
		
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.println("나이 입력 >>> ");
			String strAge = sc.nextLine();
			int age =Integer.parseInt(strAge);
			System.out.println(age>=20? "성인":"미성년자");
	
		}catch (Exception e) {
			System.out.println("예외 발생");
		}finally {
			sc.close();
			System.out.println("finally 블록 실행");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
