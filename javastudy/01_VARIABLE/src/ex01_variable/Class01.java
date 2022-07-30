package ex01_variable;

public class Class01 {

	public static void main(String[] args) {
		// single comment - 한줄짜리 주석
		/*
		 * multiful coment = 여러줄의 주석
		 */
	
		 // 키워드 이름 규칙
		 //1. 패키지 이름 주는 법 : 모두 소문자 (실제로는 회사 도메인을 거꾸로 작성
		 //2. 클래스 : 각 단어의 첫글자만 대문자, 나머지는 소문사 Upper Camel Case
		//3. 변수/메소드 : 첫글자는 소문자, 이후 단어는 첫그자 대문자 나머지 소문자 (Lower camer case)
		//4 상수: 모두 대문자, 밑줄로 연결 (_)(Snaje case)
		// main 메소드 
		//1. w자바 코드를 실행할 때 반드시 필요함
		//2. jvm 은 열려 있는 main 메소드 실행함
		//3. 열려 있는 mian 메소드가 없으면 최근에 샐항한 main 메소드를 실행한다 
		
		//변수선언
		//사용할 변수의 데이터 타입과 이름을 미리 결정해야한다
		//형식 : 데이터타입 변수병 = 초기값
		
		//논리타임
		boolean  isGood = true; // is로 시작하는 식별자는 대부분 불린타입
		boolean isAlive = false; 
		System.out.println(isGood);
		System.out.println(isAlive);
		
		//문자 타입(Character) 타입 : 딱 1글자 
		char ch1 = 'A'; // 한글자는 작은 따옴표로 묶어줘야함
		char ch2 = '홈';
		char ch3 = '\n'; // 라인피드 - 줄 바꿈
		char ch4 = '\t'; // 탭 ( 8개)
		char ch5 = '\''; // 작은 다옴표
		char ch6 = '\"'; //큰 다옴표
		
		System.out.println(ch1);
		System.out.println(ch2);
		System.out.println(ch3);
		System.out.println(ch4);
		System.out.println(ch5);
		System.out.println(ch6);
		
		//정수 타입 
		int score = 100;
		int point = 30000;
	    long view = 1000000000L; //long 타입의 값은 마지막에 L 또는 1을 추가해야한다.
		
	    //실수 타입
	    double dicount = 0.5;
	    double pi = 3.1415926535;
	    System.out.println(dicount);
	    System.out.println(pi);
	  
		
		
	}

}
