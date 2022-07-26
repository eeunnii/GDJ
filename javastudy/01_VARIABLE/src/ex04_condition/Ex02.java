package ex04_condition;

public class Ex02 {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		
		// 논리 연산자
		// 논리 AND : &&, 모두 TURE이면 TURE이다. 하나라도 FALSE이면 FALSE임
		// 논리 OR : ||(원화키에있음) 하나라도 TURE이면 TURE이다. 모두 FALSE이면 FALSE이다 
		// 논리 NOT : !, TURE는 FALSE이다. FALSE는 TURE. 반대로 바꿔줌
		
		int a = 10;
		int b = 10;
		
		boolean result1 = (a == 10) && (b == 10);  // () 선택사항
		boolean result2 = (a == 10) || (b == 10);  
		boolean result3 = (a == 10) && (b == 20);  
		boolean result4 = (a == 10) || (b == 20);  
		
		
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
		
		
		int c = 10;
		boolean result5 = !(c==10);
		boolean result6 = !(c==20);
		
		System.out.println(result5);
		System.out.println(result6);
		
		// 연습.
		// 변수 n보다 10 증가시킨 뒤 100보다 크다면 true, 아니면 false
		int n = 95;
		boolean result7 = (n+=10) > 100 ;
		
		System.out.println(n);
		System.out.println(result7);
		
		// 연습
		// 변수 x를 1 증가시킨 뒤 x가 10 과 같으면 TRUE, 아니면 FALSE
		int x = 9;
		boolean result8 = (x+=1)==10 ;  /// (++x) 쌉가능
		System.out.println(x);
		System.out.println(result8);
		
		
		
		// short Circuit Evaluation
		// 논리 AND : false가 발생하면 더 이상 진행하지 않는다. 최종 결과는 false이라서 
		// 논리 or :  true가 발생하면 더이상 진행하지 않는다 . 촤종결과는 true 이라서 
		int i = 10;
		int j = 10;
		boolean result9 = (i == 20) && (++j == 11);   /// i==20에서 이미 false라서 뒷쪽 (++j == 11) 진행하지 않음
		
		System.out.println(result9);
		System.out.println(i);  // 11이 아니라 10
		
		boolean result10 = (i==10) || (++j==11);   /// 해당 경우에도 (++j==11) 계산 안함 . 이미 앞부분 ture라서 끝남
		
		
		
		
		
		
				

	}

}
