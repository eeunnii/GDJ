package ex06_exceptionclass;


//사용자 정의 예외 클래스 
//Exception클래스를 상속받는다 

//serializable 인터페이스 : 이 인터페이스를 구현하면 직렬하기 가능함. 

//serialVersionUID 값을 가져야 함(추천)
// 	↑
//	Throwable 클래스 : serialVersionUID 값이 필요함
//	↑
// 	Exception 클래스 : serialVersionUID 값이 필요함
//  ↑
// 	MyException 클래스 : serialVersionUID 값이 필요함



public class MyException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5762491937801652422L;
	
	private int errorCode;

	public MyException(String message ,int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	

}