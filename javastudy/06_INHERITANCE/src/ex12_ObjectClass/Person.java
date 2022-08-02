package ex12_ObjectClass;

public class Person {
	
	
	private String name;
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
	public void eat() {
		System.out.println("먹는다");
	}

	
	@Override
	public String toString() {            //어떤 객체가 가지고 있는 값을 찍어볼 때 syso로 찍어보는거임
		return "이름 : " + name;
	}
	
	
	@Override
	public boolean equals(Object anObject) {  // p1.equals(p2) 에서 사용됨
		Person p = (Person) anObject;
		return name.equals(p.name);
	}
	
}
