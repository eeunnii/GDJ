package loop;

public class Ex05_nated_for {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 1일차 1교시 
		// 1일차 2교시 
		// ,,,
		// 1일차 8교시 
		// 2일차 1교시 
		// ...
		
	for(int day = 1; day<=3; day++) {
		for(int hour = 1; hour <=8; hour++) {
			System.out.println(day + "일차 "+hour+"교시");
		}
	}
		
		
	// 연습
	// 2x1=2
	// 2x2=4
	// ...
	// 9x9=81
	
	for(int num1 = 2; num1<=9; num1++) {
		for( int num2=1; num2<=9; num2++ ) {
			System.out.println(num1+"x"+num2+"="+(num1*num2));
		}
	}
		
		
	
	
	//연습
	// 2x1=2
	// 2x2=4	
	// ...
	// 5x5=25에서 끝낼거임
	
	for(int num3 = 2; num3<=5; num3++) {
		for( int num4 =1; num4 <=9; num4++) {
			System.out.println(num3+"x"+num4+"="+(num3*num4));
			if(num3 == 5 && num4==5) {
				break;                 //안쪽 for문이 끝나는거임
			}
		}
	}
	
	System.out.println();
	System.out.println();
	
	for(int num3 = 2; num3<=9; num3++) {
		for( int num4 =1; num4 <=9; num4++) {
			System.out.println(num3+"x"+num4+"="+(num3*num4));
			if(num3 == 5 && num4==5) {
				break;                 //안쪽 for문이 끝나는거임
			}else if(num3 >= 5){
				 break;
			}
			
		}
	}
	
	System.out.println();
	System.out.println();
	
	
	
	
	//lebal 을 이용한 풀이
	
	
	outer: for(int num3 = 2; num3<=9; num3++) {
		for( int num4 =1; num4 <=9; num4++) {
			System.out.println(num3+"x"+num4+"="+(num3*num4));
			if(num3 == 5 && num4==5) {
				break outer;                 //안쪽 for문끝남, outer  때문에 바깥쪽 for문 끝남
			}
		}
	}
	
	
	System.out.println();
	System.out.println();
	
	
	
	
	// 연 습 문 제 
	// 2x1=2 3x1=3 ... 9x1=0
	// 2x2=4 3x2=6 ... 9x2=18
	
	
	
	for (int num5 = 1; num5<=9; num5++) {
		for(int num6 = 2; num6 <=6; num6++) {
			System.out.print(num6+"x"+num5+"="+(num5*num6)+"\t");
			
		}
		System.out.println();
		
	}
	
	
	
	
	
	
		
	}

}
