package ex02_casting;

public class Ex01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//자동 형 변환
		//promotion 이라고 한다 
		//자동으로 다른 데이터 타입으로 변환화는 것을 말한다 
		// 작은 크기의 데이터 타입이 큰 크기의 데이터 타입으로 변환 될 때 자동으로 진행된다.
		// 정수가 실수로 변환될 때 자동으로 진행된다 .
		long money =  10000; //서로 바이트 크기가 안맞음 
		// 4바이트에 저장된 10000이 8바이트로 promoton 된다.
		System.out.println(money);
		
		int score = 100;
		double realScore = score; // 4바이트 int가 8바이트 double로 promotion 된다 
		
		System.out.println(realScore);
		
		
	}

}
