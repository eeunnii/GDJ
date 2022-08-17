package ex02_thread;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		// 스레드 우선 순위 확인 
		System.out.println("가장 높은 우선순위 : " + Thread.MAX_PRIORITY);
		System.out.println("가장 낮은 우선순위 : " + Thread.MIN_PRIORITY);
		System.out.println("보통 우선 순위 : " + Thread.NORM_PRIORITY);
		
		//스레드 2개니까 
		Soldier s1 = new Soldier("김성사", new Gun(6));
		Soldier s2 = new Soldier("장병장", new Gun(10));		// 출력해보면  둘 다 5로 동일 그래서 어떤걸 먼저 실행할지 모름~
		
		
		//우선순위가 높은 스레드를 (최대한) 먼저 실행 -- 우선순위 제일 나중에 있는 놈이 먼저 실행되기도 함 그래ㅔ서 최대한임
		// 우선 순위 조정
		s1.setPriority(Thread.MIN_PRIORITY);  //가장 낮은 우선순위
		s2.setPriority(Thread.MAX_PRIORITY);  //가장 높은 우선순위 
								// s2 가 먼저 실행될 가능성이 높다. 보장XXX  스케줄러는 우선순위만 보고 실행시키지 않기 때문이다
		
		
		// 각 스레드의 우선순위 
		System.out.println("s1 우선순위 :" + s1.getPriority());
		System.out.println("s1 우선순위 :" + s2.getPriority());
		
		s1.start();
		s2.start();			//현재 순서는 s1, s2 지만 꼭 s1 먼저 실행되리라는 보장 없음(자바 버추얼머신, 스케줄에 의해서)

	}

}
