package loop;

public class Ex01_for {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//for 문
		//연속된 숫자를 생성할 때 주로 사용한다 
		//배열과 함께 자주 사용된다 
		//for(초기문; 조건문; 증감문) {
		//		실행문
		// }
		
		// 1~10
		for(int n = 1; n <= 10; n++ ) {
			System.out.println(n);//선언하듯이 초기설정
		}
//		System.out.println(n);     ---> 여기선 n이 11임. 근데 실행안됨.
		
		// 초기문->조건문 ->실행문 -> 증감문 -> 조건문 ->실행문 ->증감문 .. . 
		// 초기문은 한번만
		
//		System.out.println(); -- 줄 바꿔서 출력해줌
//		System.out.print(); -- 줄바꿈 없이 연속해서 출력 
		
		
		//연습 
		// 10~1 출력하기 
		
		for(int a = 10; a>=1; a--) {
			System.out.print(a);
		} 
		
		System.out.println();
		
		
		
		
		
		//연습
		// 구구단 7단 출력하기 
		
		for (int y=1; y <=9; y++) {
			System.out.println("7x"+y+"="+(7*y));
		}
			
		
		//연습 
		// 1~100사이의 모든 3의 배수만 출력하기 
		
		for (int z = 1; z<=100; z++ ) {
			
			if (z%3==0) {
				System.out.print(z+" ");  // ""하면 출력값이 따닥따닥 붙어서 나오는게 아니고 띄엄 띄엄 나오게 됨
			}
		}
		
		System.out.println();
		
		
		
		int wallet = 0;
		wallet =+ 1000;
		wallet =+ 2000;
		wallet =+ 3000;
		System.out.println(wallet);
		
		// 연습
		// 1~100 모든 점수 더하기 (5050)
		
		
		int total = 0;
		for (int n = 1; n<=100; n++) {
			total += n;
		}
		
		System.out.println("전체 합은" + total + "이다");
		
		
		for (int n = 1; n<=100; n++) {
			n += n;
		}
		
//		System.out.println("전체 합은" + n + "이다");
		
		
		
		// 연습
		// begin ~ end 모든 정수 더하기 
		// begin 과 end 중 누가 큰 지 모르는 상황 
		// begin을 end 보다 항상 작은 값으로 바꾼 뒤 begin ~end 모두 더하기 진행 
		// begin이 end 보다 크다면 begin 과 end를 교환 
		
		
		int begin = 100;
		int end = 1;
		

		
		if(begin > end ) {
			int temp;
			temp = begin;
			begin = end;
			end = temp;
		}
		
		int sum = 0;
		for(int n=begin; n<=end; n++) {
			sum +=n;
		}
		System.out.println(sum);
		
	
		
		
		
		
		
		
		
		
		
		
		// 연습
		// 평점 (1~5)에 따른 별 (★)출력하기 
		int star = 5;
		
		for(int n = 1; n<=5; n++) {
			switch(n) {
			case 1 : System.out.println(n+" "+": ★"); break;
			case 2 : System.out.println(n+" "+": ★★"); break;
			case 3 : System.out.println(n+" "+": ★★★"); break;
			case 4 : System.out.println(n+" "+": ★★★★"); break;
			case 5 : System.out.println(n+" "+": ★★★★★"); break;
			}
		}
		
		
		int point = 5;
		
		String star1 = "";
		for(int n = 1; n<=point; n++) {
			star1 += "★";
		}
		System.out.println(star1);
		
		
		//String star;         
		//star = "★";
		//star = "★";;          //-> star = "★" 를 두 번 해줘도 star에 별은 하나임
		
		//String star="";        //빈 문자열으로 초기화 해줘어야 별이 누적 가능함.
		//star += "★";
		//star += "★";

		
		for(int i=1; i<200; i++) {
			
			System.out.println(
					"INSERT INTO BBS VALUES(BBS_SEQ.NEXTVAL, '작성자"+i+"', '제목"+i+"', '0:0:0:0:0:0:0:1', SYSDATE,1,0, BBS_SEQ.CURRVAL,0);"
					);
			
		}
		
		
		
	}

}
