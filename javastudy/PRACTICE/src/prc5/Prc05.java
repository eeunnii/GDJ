package prc5;

import java.util.Scanner;

public class Prc05 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("피카츄의 점수 입력 >>> ");
		int pscore = sc.nextInt();
		
		
		System.out.print("뽀로로의 점수 입력 >>> ");
		int bscore = sc.nextInt();
		
		System.out.print("브레드의 점수 입력 >>> ");
		int bbscore = sc.nextInt();
		
		
		
		int result = pscore + bscore + bbscore;
		System.out.println("평균 : "+result/3);
		
		
		
		int arr [] = new int[3];
		
		arr[0]=pscore;
		arr[1]=bscore;
		arr[2]=bbscore;
		
		for (int i =0; i<3; i++) {
			
			for (int z =0; z<3; z++) {
				
				if (arr[i]==arr[z] || arr[i]<arr[z]) {
					
					break;
				}
				
				
				
				
				
				
				
				
			}
		
			
			
			
			
			
			
			
			
			
			
			}
		
		
		
		
		
		
		
		
		
		}
		
		
		
		
	}


