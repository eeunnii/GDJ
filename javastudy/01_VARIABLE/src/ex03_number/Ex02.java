package ex03_number;

public class Ex02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 증감연산
		// 1씩 증가하거나 감소하는 연산
		//증가는 ++ 감소는 --를 사용한다.
		// 전위연산(pre operator)
		// ++a
		// 변수 a의 값을 1 증가사키고 사용한다 
		int a = 1;
		int b = ++a;
		System.out.println(a);
		System.out.println(b);
		
		
		//후위 연산 (post operator)
		//a++
		// 변수 a의 값을 사용하고 나서 1 증가 시킨다.
		int x = 1;
		int y = x++;  //////// x를 먼저 사용한다. y에 저장한다는 뜻
		System.out.println(x);
		System.out.println(y);
		
		int i = 1;
		System.out.println(i);
		

	}

}
