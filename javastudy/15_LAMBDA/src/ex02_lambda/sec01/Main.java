package ex02_lambda.sec01;


/*
   람다식
   
   1. 익명 구현 객체를 생성하기 위한 표현식
   2. 이름이 없는 익명 함수를 사용함
   3. 실행(런타임)할 때 익명 구현 객체가 만들어지면서 동작함.
   4. 형식
   		(매개변수)  ->  { 메소드본문 }
   5. 예시
   		1) (int a) -> { System.out.println(a);}
   		2) (a)     -> { System.out.println(a);}      매개변수 타입은 작성하지 않습니다.
   		<<<<무엇이든 전달할 수 있다. List의 제네릭처럼 모든지 저장할 수 있는 형태임>>>>>
   		3) a       -> System.out.println(a);         매개변수가 하나이거나, 메소드본문이 하나이면 중괄호를 작성하지 않는다.
 		4) ()      -> System.out.println("Hello");   매개변수가 없으면 빈 괄호를 작성한다.
 		5) (a, b)  -> {
 						System.out.println(a+b);
 						return a+b;               반환타입의 명시를 하지 않는다.  // 정수가들어오면 정수를 반환, 실수가 오면 실수 반환
		6) (a,b)   -> a + b;                      실행문이 return만 있는 경우 return을 작성하지 않는다. 

*/

/*
 * 
 * car인터페이스에 addOil()하나만 가능 두개 이상은 안됨. 
 * 하나만 들어가는게 functional interface라고함.  자바에 이미 만들어진 functional interface 가 4개가 있음.
 * 람다로 쓸 수 있는 인터페이스가 4개 있다는 거임. 이게 끝나야 스트림공부로 넘어갈 수 있음
 * 
 * functional interface의 추상메소드는 1개, 하나만 람다로 바꿔주면 됨
 * 
 * functional interface란 : 추상메소드가 1개 있는거. 람다로 바꿀 수 있음
 * 
 */

/*
		람다식
		
		1. 익명 구현 객체를 생성하기 위한 표현식
		2. 이름이 없는 익명 함수를 사용함
		3. 실행(런타임)할 때 익명 구현 객체가 만들어지면서 동작함
		4. 형식
			(매개변수) -> { 메소드본문 }
		5. 예시
			1) (int a) -> { System.out.println(a); }
			2) (a) -> { System.out.println(a); }     매개변수의 타입은 작성하지 않는다.
			3) a -> System.out.println(a);           매개변수가 하나이거나, 메소드본문이 하나이면 중괄호를 작성하지 않는다.
			4) () -> System.out.println("Hello");    매개변수가 없으면 빈 괄호를 작성한다.
			5) (a, b) -> {
				System.out.println(a + b);
				return a + b;                        반환타입의 명시를 하지 않는다.
			}
			6) (a, b) -> a + b;                      실행문이 return만 있는 경우 return을 작성하지 않는다.
*/

public class Main {

	public static void main(String[] args) {

		Thread thread = new Thread(()->  System.out.println("헬로월드"));
				
		thread.start();	
		
	}

}
