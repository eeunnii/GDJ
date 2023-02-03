package ex05_constructor;

// 서브클래스는 슈퍼클래스의 생성자를 "반드시"호출해야 한다 .
// 자식이 태어날려면 부모가 태어나 있어야 한다.

public class Student extends Person{
	
	//개발자가 슈퍼클래스의 생성자를 호출하지 않으면 
	// 자동으로 jvm이 호출한다.(디폴드형식의 슈퍼클래스만 자동 호출 가능- 디폴드형식 : 매개변수 없는거)

	
	public Student () {              //매개변수 없는 타입은 jvm이 자동으로 호출한다. 
		System.out.println("Student 생성");
	}
	
	
}
