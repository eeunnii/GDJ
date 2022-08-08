package ex10_downcasting;

public class Main {

	public static void main(String[] args) {
		
		//클래스 타입 : person 
		//객체(인스턴스) : p
		
		Person p = new Alba();   	//업캐스팅
		
		
		
		//instancof 연산자 
		// 특정 인스턴스가 어떤 클래스 타입인지를 점검하는 연산자
		// 해당 클래스 타입이면 true 반환, 아니면 false 반환
		
		
		
		System.out.println(p instanceof Person);
		System.out.println(p instanceof Student);
		System.out.println(p instanceof Alba);
		

		
		//p가 Student 타입의 인스턴스이면 study()메소드를 호출할 수 있다.
		
		if (p instanceof Student) {
			((Student) p).study();
		}
											///p를 student타입으로 바꾸고 study호출함
		
		
		
		//p가 Alba타입의 인스터스이면 work메소드를 호출할 수 있다
		if (p instanceof Alba) {
			((Alba) p).work();
		}
		

	}

}
