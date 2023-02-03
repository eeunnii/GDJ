package ex03_singleton;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// singleton 객체는 하나만 생성된다.
		
		
		User user1 = User.getInstance();
		System.out.println(user1);
		
		User user2 = User.getInstance();          //여러개의 객체가 만들어지면 안될 때 쓴다 
		System.out.println(user2);
		
		//User user = new User();			// 외부에서 새로운 객체를 생성할 수 없음
											// 이유:
		
	}

}
