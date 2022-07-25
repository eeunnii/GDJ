package ex02_datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ex05_LocalDateTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		// java.time.LocalDateTime 클래스
	    //  * @since 1.8 --------> 자바 1.8 버전 이상부터 사용할 수 있음
		// 특정 날짜 요소 사용이 가능
		//날짜의 패턴 지정이 가능
		
		LocalDateTime now = LocalDateTime.now(); // 현재날짜와시간. 자동완성 기능 이용하기. 객체이름 now 
		
		//특정 날짜 요소 사용
		int year = now.getYear();
		int month = now.getMonthValue(); // 1~12
		int day = now.getDayOfMonth(); //1~31
		int hour=now.getHour();
		int minute = now.getMinute();
		int second = now.getSecond();
		
		
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		System.out.println(hour);
		System.out.println(minute);
		System.out.println(second);           /// 코드는손으로 적는거 X 복사해야됨. 변수 더블클릭 후 컨트롤씨, 다른 변수 더블클릭 후 컨트롤 브이
		
		
		
		
	}

}
