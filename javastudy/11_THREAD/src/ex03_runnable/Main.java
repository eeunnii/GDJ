package ex03_runnable;

// Runnable 메소드를 만들면 run 메소드를 구현해야함 

// 왜 2가지의 생성법이 있을까 ?
// 다중상속이 안되기 때문이다. 
// 다이아몬드 상속 : 어느 부모한테 상속받은지 모르니까 다중상속 안됨. 
// 기존에 상속받은거 있으면 인터페이스 구현으로 돌리기 

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// runnable 인터페이스를 구현한 클래스는 Thread로 바꿔야 start() 메소드를 호출할 수 있다.

		Runnable robot1 = new WashRobot("로봇1"); // WashRobot이 Runnable를 구현해서 타입을 두개 중 하나로 적어도 상관없음
		Thread thread1 = new Thread(robot1);

		WashRobot robot2 = new WashRobot("로봇2");
		Thread thread2 = new Thread(robot2);

		Thread thread3 = new Thread(new WashRobot("로봇3"));

		thread1.start();
		thread2.start();
		thread3.start();

		// 위와 같은 고민하기 싫으면 아래처럼 하면 됨 (?)
		// Thread thread3 = new Thread(new WashRobot(로봇 3));

	}

}
