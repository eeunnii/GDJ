package ex04_has_a_inherit;



public class Gun {
	
	
	//필드 
	private String model;
	private int bullet;
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getBullet() {
		return bullet;
	}
	public void setBullet(int bullet) {
		this.bullet = bullet;
	}
	private final int Max_BULLET = 15;
	
	
	
	
	//장전
			public void reload(int bullet) {
				if(this.bullet == Max_BULLET) {
					return;
				}
				this.bullet +=bullet;
				this.bullet = (this.bullet>Max_BULLET? Max_BULLET : this.bullet);
				
			}
			
			
			
			//총쏘기 
			public void shoot() {
				if (bullet==0) {
					return;
				}
				bullet--;
					
			}

}
