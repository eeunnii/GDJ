package ex03_number;

public class Ex03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//대입 연산
		int score = 100; //등호(=)거 대입 연산자임. 같다는 뜻x
		//( score <- 100 이런 뜻임. 대입 대입 대입_
		System.out.println(score);
		
		
		
		//연습
		//x에 10이 있고 y에 20이 있다 
		// 와 y를 교환하시오
		int x =10;
		int y = 20;
		int temp; //
		
		temp = x;
		x=y;
		y= temp;
		
		System.out.println(x);
		System.out.println(y);
		
		//복합 대입 연산자 
		// +=,-=,*=,/=,%= 등
		int wallet = 0;
		wallet = wallet + 58000;
		wallet += 5000; //wallet = wallet + 58000
		wallet -= 3000; //wallet = wallet - 58000
		System.out.println(wallet);
		
		//연습
		//통장잔액(balance)에서 이자 5%를 받았음을 나타내자, 
		long balance = 10000;
		balance *=1.05; //프로모션 적용됨
		System.out.println(balance);
	
		
	}

}
