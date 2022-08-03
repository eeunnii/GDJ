package quiz06;

public abstract class  GameUnit {
	
	private String name;
	private int energy;
	private boolean isAlive;
	private int power;// boolean alive;로 해도 동일한 Getter/Setter가 생성됨
	


	public GameUnit(String name, int energy, int power) {
		super();
		this.name = name;
		this.energy = energy>0 ? energy : 0;
		this.isAlive = energy>0;
		this.power = power;
	}
	
	
	
	public void info () {
		System.out.println(name + "에너지"+energy+ "공격력"+power);
	}

	
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getEnergy() {
		return energy;
	}


	public void setEnergy(int energy) {
		this.energy = energy;
	}


	public boolean isAlive() {      //boolean Alive;로 해도 동일한 Gatter/Setter가 생성됨
		return isAlive;
	}


	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}


	public int getPower() {
		return power;
	}


	public void setPower(int power) {
		this.power = power;
	}
	
	
	
	public abstract void attack(GameUnit unit);
	
	
	

	
}

