package ex13;


//추상메소드
//본문이 없는 메소드
//호출용으로 사용되는 메소드
//중괄호 자체를 없애고 세미콜론(;)을 추가함
//public abstract 또는 abstract public

//추상 클래스
//1. 추상 메소드가 한 개 이상 존재하는클래스
//2. public abstract class
//3. 본문이 없는 메소드를 포함하기 때문에 객체 생성이 불허
//4. 추상클래스를 상속 받는 클래스는 반드시 모든 추상메소드를 overrride 해야함
public abstract class Shape {
	
	private String type;

	public Shape(String type) {
		super();
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	public abstract double getArea();     //Shape 을 상속 받는 객체들이 호출할 때 사용하는 메소드
								//사용되지 않는다 = 추상메소드로 바꿔준다.
								//추상메소드 : 본문이 없는 메소드
	
	
	
	
	
	
	

}
