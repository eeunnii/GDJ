package pra1;

import java.util.Scanner;

public class PRA01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 점수와 학년을 입력 받아 60점이상이면 합격, 60점 미만이면 불합격을 출력하시오.
		// 4학년인 경우 60점 이상이어야 합격이다.
		
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print("점수를 입력하세요 >>> ");
		int score = sc.nextInt();
		
		
		
		System.out.print("학년을 입력하세요 >>> ");
		int grade = sc.nextInt();		
		
		
		
		if (grade <= 3) {
			
			if(score<60) {
				System.out.println("불합격!");
			} else {
				System.out.println("합격!");
			}
			
		} else if (grade == 4) {
			if(score>=70){
				System.out.println("합격!");
			}else {
				System.out.println("불합격!");
			}
			
		
		} else if (grade>4){
			System.out.println("잘 못된 학년입니다.");	
		}
		
		
		
		
//		if (grade < 0) {
//			System.out.println("잘못된 점수입니다.");
//		} if else {
//			System.out.println();
//			
//		}
//		
		
		
		
		
		
		
		
		
		
		
		
		
		
		}
		
}

