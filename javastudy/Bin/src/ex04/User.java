package ex04;

public class User {

	
	
	private int userNo;
	private String id;
	private String email;
	
	//필드(Builer 객체가 가진 값을 받아 옴)
	public User(Builer builer) {
		this.userNo = builer.userNo;
		this.id=builer.id;
		this.email = builer.email;
		
		
	
		
	}
	
	//Builder 반환 메소드
	public static Builer builder() {
		return new Builer();
	}
	
	
	
	
	//User 클래스 내부에 Builder 클래스 생성
	//User 클래스를 이용해서 호출라기 위해 static 처리 
	public class Builer {
		
		
		//내부 필드 (여기에 값을 전달 받아서 User의 필드로 보내는 원리)
		private int userNo;
		private String id;
		private String email;
	
		//userNo() 메소드
		public Builer userNo(int userNo)	{
			this.userNo = userNo;
			return this;
		}
		
		//id() 메소드
		public Builer id(String id) {
			this.id = id;
			return this;
		}
		
		public Builer email(String email) {
			this.email = email;
			return this;
		}
		public User build() {
			return new User(this);
		}
		
		
		
	} // /class Builder

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", id=" + id + ", email=" + email + "]";
	}
	
}
