package ex02_casting;

public class Ex03 { //첫글자 대문자면 무조건 클라스 

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//			"100" -문자 
//			100 - 숫자
		
		String strScore="100";
		String strMoney="10000000000";
		String strGrade="4.5";
		
		//문자열을 숫자데이터로 변환하기
		// 아래 변환 중요 
		int score = Integer.parseInt(strScore); //문자를 숫자로 
		
		long money = Long.parseLong(strMoney);
		
		double grade = Double.parseDouble(strGrade);
		
		
	   System.out.println(score);
	   System.out.println(money);
	   System.out.println(grade);
	   
	   
	   //100(숫자)-->"100"(문자)
	   //숫자데이터를 문자열로 변환하기 
	   int age = 100;
	   String strAge = String.valueOf(age); /////////공식적인 방법 강사쌤은 잘 안쓴다고 함
		
	   System.out.println(strAge);
		
		
	//casting(라고 잘 안함) = 변환 = promotion	
		
	}

}
