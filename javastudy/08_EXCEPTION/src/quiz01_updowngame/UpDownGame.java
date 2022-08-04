package quiz01_updowngame;

import java.util.Scanner;

public class UpDownGame {
	
	
	

	//필드 
	private int rand; // 1~100사이 난수 
	private int count;
	private Scanner sc;
	
	
	//생성자
	public UpDownGame () {
		// rand, sc 만들기 
		
			int rand = (int)(Math.random()*100) + 1 ;
			sc = new Scanner(System.in);
			
			
			
			while (true) {
				
				
				if (count == rand) {
					
					System.out.println("정답!");
							this.count++;
					
				} else if (count < rand) {
					
					System.out.println("Up!");
					this.count++;
					
				} else if (count>rand) {
					
					System.out.println("Down!");
					this.count++;
				}
				
				
				
				
				
				
				
				
				
				
			}
				
			
			
			
			
			
			
			
		
		
		
		
		
	}
	
	
	
	
	//입력
	public int input() {
		return 0;
		
		
		
		
		
		
		
		

	}
	
	//실행 
	public void play() {
		
		
		//맞힐 때 까지 무한 루프
			while (true) {
				int n = input();
				
				
				if (n<rand) 
					System.out.println("Up!");
				} else if (n>rand) {
					System.out.println("Down!");
				} else {
					System.out.println(rand + "정답입니다"+count+"번만에 맞춤");
					break;
				}
				
			}
		
		
	}
	
	
	
	
		

