
public class Ex03_else_if {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		/* else if 문
		 * 조건이 여러 개 사용되는 경우에 각 조건을 처리한다 
		 * if(조건) {
		 * 		실행문
		 * } else if(조건) {
		 * 		실행문
		 * } else if(조건) {
		 * 		실행문
		 * }
		 */
		
		
		//나이에 따른 결과 출력
		// 0~7 미취학아동
		// 8~13 초등학생
		// 14~16 중학생
		// 17~19 고등학생
		// 20 성인
		
		int age = 10;
		
		if(age<0 || age>100) {
			System.out.println("잘못된 나이");
		} else if (age <=7) {
			System.out.println("미취학아동");
		} else if (age<=13) {
			System.out.println("초등학생");
		} else if (age<=16) {
			System.out.println("중학생");
		} else if (age <=19 ) {
			System.out.println("고등학생");
		} else {
			System.out.println("성인");
		}
			
	
		//연습
		//월에 따른 계절 출력
		//봄	: 3~5
		//여름 : 6~8
		//가을 : 9~11
		//겨울 : 12, 1~2
		int month = 1;
		
		
	//	if(month==12 || month<3) {
	//		System.out.println("겨울");
	//	} else if (8<month<12)
			
		
		// 나머지 연산을 활용한 modular 연산
//		
//		int mod = month % 12;
//		if(mod < 1 || month >12) {
//			System.out.println("잘못된 월");
//		} else if (mod<=2) {
//			System.out.println("겨울");
//		} else if (mod <= 5 ) {
//			System.out.println("봄");
//		} else if (mod<=9) {
//			System.out.println }
				
		
		
		
			
	
			
			
			//연습
			//점수에 따른 학점
			//score~grade		
			//100~90 A
			//89~80 B
			//79~80 C
			//89~80 D
			//89~80 E
			//89~80 F
		
		int score = 1155;
		char grade;
		
	
			if (score<0 || score>100) {
				grade = 'x';	
			} else if(score >=90) {
				grade = 'A' ;
			}
			
			
			
			//연습
			//오직 일 수만을 고려한다.
			// 1일이 수요일이다,
			// n일 후 무스슨 요일 인지 출혁하기 
//			
//			int day = 1 ; 
//			int n = 1;
//			String weekName; //a목요일 
//			
//			day += n;
//			 
//			if (day%7 == 0 ) {
//				weekName = "화";
//			} else if (day%7 == 1) {
//				weekName = "수";	
//			} else if (day%7 == 2) { 
//				weekName = "목";
//			} else if (day%7 == 3) {
//				weekName = "금";
//			} else if (day%7 == 4) {
//				weekName = "토";
//			}

			
//			if (day&7=1)
	
	
	
	
	
	
	
	
	
	
		
		
		
	}

}
