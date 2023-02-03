package ex12_ObjectClass;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//Object 클래스는 모든 객체(변수)를 저장할 수 있다.
		Object p = new Person();
	
		
		//Object클래스 타입의 객체는 
		// 항상 다운캐스팅해서 사용해야 한다.
		if(p instanceof Person) {
			((Person) p).eat();
		}
		
		
		
		//새로운 Person(toSting()확인용)
		Person person = new Person();
		person.setName("제임스");
	
		
		System.out.println(person);
		
		
		//새로운 Person(equals() 확인용)
		//name이 같으면 동일한 객체로 인식하기 
		Person p1 = new Person();
		Person p2 = new Person();
		p1.setName("kim");
		p2.setName("kim");
		System.out.println(p2.equals(p1));;
				
	
		
	}

}
