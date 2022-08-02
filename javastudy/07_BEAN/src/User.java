import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class User {

	
	
	
	
	private String id;
	private String pw;
	
	
	
	public static void main (String[] args) {
		
		User user = new User();
		user.setId("admin");
		System.out.println(user.getId());
		
	}
	
	
}
