package ex04;

public class Ex03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		// 연습. 대문자 6자리로 구성된 인증코드 작성하기
	      StringBuilder sbCode = new StringBuilder();
	      
	      
	      for(int i=0; i<6; i++) {
	      sbCode.append((char)((int)(Math.random() * 26) + 'A'));
	      }
	      
	      
	      String code=sbCode.toString();
	      System.out.println("인증코드:"+code);

		
	      // 연습. 1 2 3 4 5 6 7 8 9 10 만들기
	      StringBuilder sbPaging=new StringBuilder();
		
		
		
		
		
		
		
	}

}
