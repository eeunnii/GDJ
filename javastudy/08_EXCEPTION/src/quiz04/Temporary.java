package quiz04;

public class Temporary extends Employee{
	
	private int pay;
	private int WorkTimes;
	
	public Temporary(int empNo, String name) {
		super(empNo, name);
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public int getWorkTimes() {
		return WorkTimes;
	}

	public void setWorkTimes(int workTimes) {
		WorkTimes = workTimes;
	}

	@Override
	public String toString() {
		return "Temporary [pay=" + pay + ", WorkTimes=" + WorkTimes + "]";
	}
	
	

}
