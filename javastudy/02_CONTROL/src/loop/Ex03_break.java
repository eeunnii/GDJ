package loop;

public class Ex03_break {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// break 문
		// switch문을 종료할 때 사용한다 .
		// 반복문(for, while)을 종료할 때 사용한다.
		
		// 모금 목표 : 100000원
		// 한 번에 30원씩 모금
		
//		while(true) {   //언제나 만족한다 = 끝나지 않는다 = 무한루프  // 언리처드코드=데드코드
//		       if(조건문) {
//		    	   break;
//		       }
//			
//		}
		
		
		int total = 0;
		int money = 30;
		int serial = 0;
		int goal = 100000;
		while(true) { //무한루프 
			if (total >= goal) {
				break;
			}
			total += money;
			serial++;
			System.out.println(serial+ "회 모금액 " +money+"\t현재" + total + "원");
		}
		
		
		
		
		
		
		
	}

}
