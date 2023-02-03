package ex03_functional_interface.sec01;

public class Main {
	
	public static void main(String[] args) {
		
//		MyInterface1 myInterface1 = new MyInterface1() {
//			
//			// 메소드를 람다로 바꾼거임 
//			@Override
//			public void method() {
//				System.out.println("집에가고싶다");
//			}
//		};
		MyInterface1 myInterface1 = ()  ->  System.out.println("집에가고싶다");
		myInterface1.method();
		
		MyInterface1 myInterface2 = ()  ->  System.out.println("나는 자고 싶다");
		myInterface2.method();
		
	}

}
