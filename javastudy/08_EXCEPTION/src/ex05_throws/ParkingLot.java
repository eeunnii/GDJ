package ex05_throws;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ParkingLot {
	
	private Car[] cars;
	private int idx;
	private Scanner sc;
	
	
	
	
	

	
	
	
	

	
	public void addCar() throws RuntimeException {
	
		if (idx == cars.length) {
			throw new RuntimeException("FULL");
		}
	}
	
	public void deletCar() {
		
		
		try {
		
		if (idx == 0) {
			System.out.println("EMTPY");
		}
		
		} catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
	
	}
	
	public void findCar() {
		
	}
	
	public void printAllCars () {
		
	}
	
	
	public void manage() {
		
		
		while(true) {
		
		try {
			
			
				
				System.out.println("1.추가 2.제거 3.조회 4.전체목록 5.종료 >>> ");
				int choice = sc.nextInt();
				switch(choice){
					case 1 : addCar(); break;
					case 2 : deletCar(); break;
					case 3 : findCar(); break;
					case 4 : printAllCars(); break;
					case 0 : return;
					default : new RuntimeException("Bad Request");
					
					
				}
			
		
			
			
			
		
	} catch (InputMismatchException e) {
		sc.next();
		System.out.println("처리명령은 1~4, 0 으로 입력하시오");
		
	} catch (RuntimeException e) {
		System.out.println(e.getMessage());
	}
		}
		


	}
}

	

