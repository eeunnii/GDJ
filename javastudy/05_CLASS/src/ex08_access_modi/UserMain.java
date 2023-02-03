package ex08_access_modi;



public class UserMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		User user = new User();
		
		System.out.println(user.getId());
		
		//user.id = "dfdf"; // 이거 안됨. 정보은닉이라 직접 필드에 접근 못함
		
		user.setId("admin");
		
		System.out.println(user.getId());
		
		
		
		System.out.println(user.getPassword());
		
		user.setPassword("eee");
		
		System.out.println(user.getPassword());
		
		
		
		user.setEmail("mdfdf");
		System.out.println(user.getemail());
		
		
		
		user.setPoint(45);
		System.out.println(user.getPoint());
		
		
		
		//user.setVip(false); {
			System.out.println(user.getVip());
		//} 										----> User클래스에서 setVip메소드를 private처리해서 실행안됨.
		
			
			
			
			
		
	}

}
