package ex03_has_a;

public class Soldier {
	
	//필드

	private Gun gun;

	public Gun getGun() {
		return gun;
	}

	public void setGun(Gun gun) {
		this.gun = gun;
		
	}
	
	
	public void reload(int bullet) {
		gun.reload(bullet);
		
	}
	
	
	public void shoot () {
		gun.shoot();
	}
	
	// 메소드 
	

	
	
}
