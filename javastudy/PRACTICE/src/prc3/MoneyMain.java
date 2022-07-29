package prc3;

import java.util.Scanner;

public class MoneyMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		System.out.print("금액을 입력하시오 >> ");
		
		int money = sc.nextInt();
		
		int[] unti = {50000,10000,5000,1000,500,100,50,10,5,1};
		
		
		for (int i = 0; i < unti.length; i++) {
			
			
			if ( money> unti[i] ) {
			
		
			System.out.println(unti[i]+"원 짜리 : "+money/unti[i]+"개");
			
			money %= unti[i];
			
			
			}
		
			
		}
		
		
		
		
		
		
		
		
		
	}

}
