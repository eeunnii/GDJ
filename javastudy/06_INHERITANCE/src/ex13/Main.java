package ex13;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		//Shape 클래스 타입의 객체는 존재할 수 없는 객체이다.
		// abstract 처리해서 객체의 생성을 막을 수 있다.
		
			//Shape s1 = new Shape("도형");
		
		//System.out.println(s1.getType());
		//System.out.println(s1.getArea());
		
		
		Circle s2 = new Circle("원", 1);
		
		System.out.println(s2.getType());
		System.out.println(s2.getArea());
		
	
		
		
		
		
		
		
	}

}
