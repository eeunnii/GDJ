package ex04_method;

public class Car {
	
	
	
	//필드
	int oil;
	int speed;
	
	//메소드
	
	// 1. 기름 넣기 
	// 반환 타입 : void (반환값이 없다 )
	// 메소드명 : addoil
	// 매개변수 : int o
	void addoil(int o) {
		
		oil += o;
		
		if(o>60) { 
			oil = 60;
		}
		
	// 2. 달리기 
	// 반환타입 : void
	// 메소드명 : pushAccel
	// 매개변수 : X
		
	}
		
		
	
	
	void pushAccel() {
	
		
		//속도는 25씩 증가, 최대 속도 120
		// 기름은 1씩 사용
		if (oil == 0) {
			return;   //반환타입이 voil일때만 사용가능
		}
		if (speed ==120) {
			oil--;
			return;
		}
		speed += 25;
		if (speed>120) {
			speed = 120;
			
		}
		
		oil--;
		
		
	}
		
	
	
	
	//반환타입 : void
	//메소드명 : pushBrake
	//매개변수 : X
	
	
	
	
	void pushbrake() {
		
		 
		//속도 25씩 감소 
		
		
		speed -= 25;
		
		
		if (oil == 0) {
			
		}
		
		
		
		//4. 계기판(기름, 속도) 확인
		//반환타입 : void
		//메소드명 : panel
		//매개변수 : X
		
		void panel() {
			
		}
		
		
		
	}
	
	
	

		
	

}
