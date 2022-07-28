package ex07_contstruc;

public class Computer {

	//생성자 ( Constructor)
	// 1. 객체 생성할 때 사용되는 특별한 메소드 
	// 2. 특징 
	//		1)메소드 이름이 클래스 이름과 같다.
	//		2)반환타입이 존재하지 않는다. void 타입 그런거 없음..
	// 3. 역할 : 생성자는 필드 초기화 용도로 사용된다 
	
	// 디폴드 생성자 (Default Constructor)
	// 1. 개발자가 생성자를 만들지 않으면 자바가 자동으로 생성하는 생성자 
	// 2. 아무 일도 안하는 형태 
	// 		Computer() {}					반환타입 메소드명(=computer) (매개변수) 
	
	
	//필드
	String model;
	int price;
	
	
	
	//생성자(필드 초기화용)------------------------심각하게 공부할 필요 없다?~~
	
	Computer() {
		
	}
	
	
	Computer(String pModel, int pPrice) {
		model = pModel;
		price = pPrice;
	}
	
	
	
	
	//메소드 
	void printComputerStatus() {
		System.out.println("모델 : "+model);
		System.out.println("가격 : "+price);
	}
	
	
	
	
}
