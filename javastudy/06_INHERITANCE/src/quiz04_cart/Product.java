package quiz04_cart;

public class Product {
	
	
//	- Snack, Meat, Milk는 모두 Product이다.
//	- 모든 Product은 이름(name)과 가격(price)만 가진다.
//	- 고객 (Customer)은 모든 Product를 10개 담을 수 있는 cart를 가진다.
//	- 고객은 돈(money)과 보너스포인트(bonusPoint,구매액의 10%)를 가진다.
	
	
//	
	private String name;
	private int price;
	
	public Product(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}



	
	
	
	
	
	

}
