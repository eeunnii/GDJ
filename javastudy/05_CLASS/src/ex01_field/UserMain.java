package ex01_field;

public class UserMain {
	
	//int a; //필드 - 필드는 메소드에서 사용 됨. 하늘색임

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 //클래스 User : 데이터타입
		 //객체(변수의 개념으로 인스턴스라고도 함) user : 변수
		
		// 클래스(데이터타입)			: User	 -- 클래스는첫글자 대문자 
		// 객체(변수 개념, 인스턴스) 	: User
		
		int a = 10;         //여기서 a는 변수 -- 기본타입 8가지 뒤에오는 것만 변수됨
		String s = "hi";    // 여기서 S는 객체 -- String class 있음
		
							//동일 패키지는 import없이 사용 가능하다. (동일 패키지 규칙)
		
		// 객체 선언 
		User user = null;
		
		// 객체 생성
		user = new User();
		
		// 모든 User객체는 필드값을 가지고 있다 
		// 마침표(.)를 이용해서 필드값을 호출한다 
		System.out.println(user.id);
		System.out.println(user.password);
		System.out.println(user.email);
		System.out.println(user.point);
		System.out.println(user.isVip);
		
		//필드값 변경
		user.id = "admin";
		user.password = "12345";
		user.email = "admin@ndnf.kfd";
		user.point = 1000;
		user.isVip = (user.point>=10000);
		
		System.out.println(user.id);
		System.out.println(user.password);
		System.out.println(user.email);
		System.out.println(user.point);
		System.out.println(user.isVip);
		
		
		
		
	}

}
