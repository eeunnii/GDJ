package ex08_access_modi;

public class User {

	
	// 필드는 private이다. 
	
	private String id; 
	private String password;
	private String email;
	private int point;
	private boolean isVip;
	
	//메소드는 pubilc이다.
	public String getId() {
		return id;
	}
	
	public void setId(String pId) {
		id = pId;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String pPassword) {
		password = pPassword;
	}
	
	
	
	public String getemail()
	{
		return email;
	}
	
	public void setEmail (String setEmail) {
		email = setEmail;
	}
	
	
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int setPoint) {
		point = setPoint;
		setVip(point >=10000);     // 아랫줄에 있는 setVip 메소드 호출함
	}
	
	
	
	
	
	
	
	public  boolean getVip() {
		return isVip;
	}
	
	
	
	private void setVip(boolean pVip) {
		isVip = pVip;
	}
}
