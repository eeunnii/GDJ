package loop;

public class Ex04_continue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// continue문
		// 반복문의 시작 지점으로 이동한다.
		// 실행에서 제외할 코드가 있는 경우에 사용한다. 
		// 꼭 사용해야되는 코드는 아님 대체코드잇음
		
		
//		while() {
//			a;
//			b;
//			c;
//			dl
//			continue // 여기까지만 실행됨 아래 코드는 데드코드
//			d
//			d
//			fd
//			f
//		}
		
		
		

		
		
		// 1~100 중에서 3의 배수를 제외하고 모두 더하기 
		
		int total = 0;
		int n = 0;
		
		while (n<100) {
			
			n++;
			if(n%3==0) {
				continue;
			}
			total +=n;
		}
	

		System.out.println(total);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
