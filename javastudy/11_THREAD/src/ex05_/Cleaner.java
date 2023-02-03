package ex05_;


// Cleaner 1개 
// : 공유 자원



// cleaner를 사용할 Robot은 2개 
// : 스레드 


// Robot이 Cleaner를 차지하기 위한 쟁탈전이 벌어진다.

// synchronized 씽크
// 스레드 충돌 방지하기 위해서 한 번에 한 스레드만 접근할 수 있도록 허용
// 공유 자원 일관성을 보장할 수 있다. (이게 먼소리여)
// 한 번에 한 스레드만 접근할 수 있는 영역을 임계 영역(Critical section)이라고 함

// wait() - Object 클래스의 wait() 메소드
// 스레드가 대기 상태가 됨
// 다른 스레드가 깨울때까지 대기상태!
// 인셉션필요 try catch 해야함


// Object 클래스의 notify() 메소드
// 1 다른 스레드를 깨움
// 2 스레드가 여러개인 경우에는 notifyAll() 을 씀 



public class Cleaner {
	
	public synchronized void toiletCleaing() {
		
		try {
			System.out.println("화장실 청소");
			notify(); 		// 화장실 청소 끝났다 라고 알려줌 RoomRobot 한테 
			wait();			 // 잠깐 쉼
			
		}catch (InterruptedException e) {	//	wait() 메소드는 예외처리 필요
			e.printStackTrace();
		}
	}
	
	public synchronized void RoomrRobot() {
		
		try {
			System.out.println("방 청소");
			notify();
			wait();
			
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void roomCleaning() {
		System.out.println("방 청소");
	}

}
