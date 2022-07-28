package ex11_static;

public class MyMath {
	
	
	
	
	
	//static
	// 1. 정적요소 
	// 2. 객체 생선 이전에 메모리에 미리 만들어 지는 공동 요소
	// 3. 클래스에서 1개만 만들어짐
	// 4. 클래스를 이용해서 호출하기 때문에 클래스 변수, 클래스 메소드라고도 부른다.
	
	//필드 
	public static final double PI = 3.141592;   // 클래스 변수 // public으로 하는 이유 : 값이 고정되어있어서.
	
	
	//메소드 
		public static int abs(int n) {
			return(n>0) ? n : -n; 	
		}

	public static int pow(int n, int n1) {
	//a의 b 제곱 반환
	//for문 구현
		int result = 1;

	for (int i=0; i<n1; i++) {
		result *= n;
	}
		
	return result;	
		
	}	
}
