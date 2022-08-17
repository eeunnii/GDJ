package ex01;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Main 시작");
		
		Process process1 = new Process("연산");
		process1.start();  // process 클래스의 오버라이드 된 run() 메소드가 호츨		
							//	이 메소드는 main 하는게 아니라 프로세스가 하는거임 
							// Main 메소드는 process.start(); 노크 하고 그대로 내려감. 
							// 스레드는 mian이 끝나든 말든 쭉 감
		
		Process process2 = new Process("제어");
		process2.start();
		
												// 간혹 제어 먼저 실행될 때가 있는데 ? 이유가 뭐더라 
		
		System.out.println("main 종료");

	}

}
