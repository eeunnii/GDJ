package ex03_number;

public class Ex01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 산술 연산
		int a = 7;
		int b = 2;
		int result1 = a+b;
		int result2 = a-b;
		int result3 = a*b;
		int result4 = a/b;            //  몫을 구할 때 
		int result5 = a&b;            //  나머지를 구할 때 
		
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
		System.out.println(result5);
		
		//연습
		// 25를 2와 5로 나누기 
		
		int n = 25 ;
		int ten = n/10; //2
		int one = n%10; //5
		
		System.out.println(ten);
		System.out.println(one);
		
		//연습
		//90초를 1분 30초로 나눠보기 
		
		int second = 90;
		int m = second/60; //1
		int s = second*60; //30
		System.out.println(m);
		System.out.println(s);
		
		
		//a=7이고, b는 2 이므로 
		//a 나누기 b는 3/5이다.
		
		double result = (double)a/b; // 3.5 
		// a만 캐스팅된거고 b는 자바가 알아서 프로모션한거임
		
		System.out.println(result);
		
		
		
	}

}
