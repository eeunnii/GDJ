package exam01;


class A {
	public void method(I i) {  // 인터페이스 I를 구현한 넘들만 들어오라!!
		i.method();			//인터페이스 I를 구현한 넘들만 매개변수로 가능함
	}
}

interface I {
	public void method();
}

class B implements I{
	public void method() {
		System.out.println("B클래스의 메서드");
	}
}

class C implements I{   //C가 인터페이스 method()의 매개변수로 가능
	public void method() {
		System.out.println("C클래스의 메서드");
	}
}

public class InterfaceTest {

	public static void main(String[] args) {
		
		A a = new A();
		a.method(new B());	
	}
}
