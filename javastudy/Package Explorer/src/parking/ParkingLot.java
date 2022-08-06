package parking;

import java.util.Scanner;

public class ParkingLot {
	
	
	private String name;
	private Car[] cars;
	private int idx;  // 배열의 인덱스 
	private Scanner sc;
	
	
	public ParkingLot(String name) {
		super();
		this.name = name;
		cars = new Car[10];
		sc = new Scanner(System.in);
	}
	
	
	
	public void addCar() throws RuntimeException {
		
		
		
		
		System.out.println("현재 등록된 차량 "+idx+"대");
		
		if(idx == cars.length) {
			System.out.println("더 이상 차량 등록이 불가능합니다.");
			return;
		}
		
		System.out.println("▶▶▶차량 등록◀◀◀ ");
		System.out.println("차량 번호 입력 >>> ");
		String carNo = sc.nextLine();
		System.out.println("차량 모델 입력 >>> ");
		String carModel = sc.nextLine();
		
		Car car = new Car(carNo.replace(" ", ""), carModel.replace(" ", ""));
		cars[idx++]=car;

		System.out.println("차량번호 "+carNo.replace(" ", "")+"차량이 등록되었습니다.");

	}
	
	


	
	
	
	
	
	
	public void deleteCar() {            
		
		if(idx==0) {
			System.out.println("등록된 차량이 없습니다.");
			return;
		}
		
		
		System.out.println("▶▶▶차량 삭제◀◀◀");
		System.out.println("삭제할 차량 번호을 입력하세요 >>> ");
		String carNo = sc.nextLine();
		
		for(int i = 0; i<idx; i++) {
			if(cars[i].getCarNo().equals(carNo.replace(" ", ""))) {
				System.arraycopy(cars, i+1, cars, i, idx-i-1);
				cars[--idx] = null;
				System.out.println("차량번호 " + carNo.replace(" ", "") + "인 차량을 삭제했습니다.");
				return;
			}
			
		}
	
	System.out.println("대상 차량이 존재하지 않습니다.");
	
	}
	
	
	
	
	
	
	
	
	
	
	
	public void printAllCars() { 
		
		if(idx == 0) {
			System.out.println("등록된 차량이 없습니다.");
			return;
		}
		
		System.out.println(name);
		
		
		for (int i = 0; i<idx; i++) {
			System.out.println(cars[i]);
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void manage () {
		
		
		while(true) {
			System.out.println("1.추가 2.삭제 3.전체 0.종료");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1 : addCar(); break;
			case 2 : deleteCar(); break;
			case 3 : printAllCars(); break;
			case 0 : System.out.println("종료"); return;
			default : System.out.println("존재하지 않는 메뉴입니다.");
			}
		} 
			
		
		
	}
	
	

	
	
	
	
	
	


}
