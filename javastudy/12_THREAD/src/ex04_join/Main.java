package ex04_join;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		// calculator를 2개 준비 (계산을 빨리하고 싶어서)
		// 작업을 반으로 나눠서 진행 
		
		
		// 근데 쓰레드 안쓰면 1~5000까지 더하고, 5001~10000까지 더하는 메소드가 순차적으로 실행. 즉 동시에 진행하는 것이 아닌 순서에 따라 진행 
		// 동시에 진행하면 더 빨라지니까 쓰레드 쓰는거임 
		
		// calculator가 동시에 연산을 수행하려면 calculator 스레드로 처리해야함 
		
		
		
		Calculator calc1 = new Calculator(1, 50000);
		Thread thread1 = new Thread(calc1);
		thread1.start();		//	 1번째 계산기 동작 시작
		
		
		// 위와 같은 식
		//Thread calc1 = new Thread(new Calculator(1, 5000));
		//calc1.start();
		
		
		Calculator calc2 = new Calculator(50001, 100000);
		Thread thread2 = new Thread(calc2);
		thread2.start();		// 2번째 계산기 동작 시작 
		
		
		
		try {	
			// 모든 계산기 동작이 끝날때까지 기다린다.
			// join() :  스레드가 종료(die)될 때까지 기다린다.
			thread1.join();
			thread2.join();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
//		Calculator cal1 = new Calculator();
//		int total = cal1.add(1, 5000);
//		System.out.println(total);
//		
//		Calculator cal2 = new Calculator();
//		int total2 = cal2.add(5001, 10000);
		
		System.out.println(thread1.isAlive());
		System.out.println(thread2.isAlive());
		
		System.out.println(calc1.getTotal()+calc2.getTotal());				//출력결과 0임  왜냐면 메인 메소드는 쓰레드가 끝나는걸 기다리지 않기 때문임~~ 쓰레드는 계속 실행중인데 메인메소드가 끝나서 출력은 0	 
																			// 실행을 시켜놓고 결과를 기다릴 생각이 없는 메인 메소드
																			//아니면 Thread.sleep(1)을 걸어서 메인메소드가 쓰레드를 기다리게 할 수 있음 
	}
	

}
