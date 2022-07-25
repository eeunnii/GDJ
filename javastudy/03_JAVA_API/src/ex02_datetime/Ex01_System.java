package ex02_datetime;

public class Ex01_System {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		// 핑크색 클래스, 주황색 메소드

		// 1. 타임스탬프 (timestamp) ★★★★★★★
		// 1970-01-01 0:00부터 1/1000 초마다 증가하는 long 타입의 정수값  천부의 일초 = 엠에스
		long timestamp = System.currentTimeMillis();
		System.out.println(timestamp);
		
		// 2.나노타임
		// s > ms > ㎲ > ㎱(십억분의 1초)
		// 나노초(ns) 값을 가지는 long 타입의 정수값
		// 어떤 기준일자는 없어서 두 개의 나노초(ns)사이의 경과시간 계산용
		// 자주 쓸건아니라서 한번쓰고 말거라고 햇 삼
		
		long beginTime = System.nanoTime();
		
		int total = 1+2+3+4+5;
		long endTime = System.nanoTime();
		System.out.println(total + " 계산에 걸린시간 : " + (endTime - beginTime) +"ns");
		
		

		
		
		
		
		
		
		
		
		
	}

}
