package prc03_bakery;

public class Bakery {
	
	//필드 
	private int price ; //빵가격
	private int cnt; // 빵개수
	private int money; // 자본금
	
	//생성자
	
	public Bakery(int price, int cnt, int money) {
		super();
		this.price = price;
		this.cnt = cnt;
		this.money = money;
	}
	
	
	//개터새터
	
	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getCnt() {
		return cnt;
	}


	public void setCnt(int cnt) {
		this.cnt = cnt;
	}


	public int getMoney() {
		return money;
	}


	public void setMoney(int money) {
		this.money = money;
	}
	

	
	
	//판매 //판매할 수 있는 빵은 몇 개인가?
	public BreadAndCharge sell(int custMoney) throws RuntimeException{
		
		//판매불가 
		if(custMoney < price) {
			throw new RuntimeException("판매불가");
		}
		
		int sellCnt = custMoney / price;
		
		//잔돈은 얼마인가?
		int change = custMoney%price;
		
		
		//매장 내부의 변화 처리 
		cnt -= sellCnt;
		money += (custMoney - change);
		
	
		//고객에게 되돌려 줄 빵과잔돈
		return new BreadAndCharge(sellCnt, change);
		
		
	}
	
	



	


	//정보화인
	public void info() {
		
		System.out.println("빵 " + cnt + "개, 자본금"+money+"원");
		
	}
	

}
