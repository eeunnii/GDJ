package constructor;

public class Constructor {
	
	private int userNo;
	private String id;
	private String email;
	
	public Constructor(int userNo, String id, String email) {
		super();
		this.userNo = userNo;
		this.id = id;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Constructor [userNo=" + userNo + ", id=" + id + ", email=" + email + "]";
	}
	
	
	
	
	

}
