package ex01_anoymous_object.sec04;

public class Soil {
	
	private int totalOil = 1000;
	private int payPerLiter = 2000;
	private int earning;
	
	// 3. 메소드의 매개변수로 익명객체 저장하기 
	public void sellOil(Car car) {
		car.addOil();
	}
	
	// 자동차 올 때마다 임시객체를 만들어서 한 번쓰면 없어지게. 한 번쓰면 없어지니까 메모리 관리에 좋음
	
	
	public int getTotalOil() {
		return totalOil;
	}

	public void setTotalOil(int totalOil) {
		this.totalOil = totalOil;
	}

	public int getPayPerLiter() {
		return payPerLiter;
	}

	public void setPayPerLiter(int payPerLiter) {
		this.payPerLiter = payPerLiter;
	}

	public int getEarning() {
		return earning;
	}

	public void setEarning(int earning) {
		this.earning = earning;
	}


	
	
}
