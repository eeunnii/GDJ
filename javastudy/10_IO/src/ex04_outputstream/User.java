package ex04_outputstream;

import java.io.Serializable;

//User user = new User(1, "Kim", 30);
//user에 순서없이 3개의 객체가 있는거임. 
//스트림에 보낼려면 줄세워야되는데 이를 줄세우는게 직렬화! ( Serializable interface 사용 )

//스트림을 이용해서 객체를 전송하려면 직렬화를 해야한다.
//직렬화가 필요한 객체는 Serializable 인터페이스를 구현해야한다.
// Serializable 인터페이스를 구현한 클래스는 ★serialVersionUID 필드가★ 필요하다. (없으면 오류뜸)
// 그럼 클래스 이름 (User)에 마우스 올려보면 경고메세지 2개 생기는데 첫번째꺼 선택 
// 필드값을 구현하는 이유?


public class User implements Serializable {							//java.io 클래스
	
	/**
	 * 
	 */
	
	
	
	private static final long serialVersionUID = 1L;
	private int userNo;
	private String name;
	private int age;
	
	
	public User(int userNo, String name, int age) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.age = age;
	}


	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", name=" + name + ", age=" + age + "]";
	}
	
	
	
	

}
