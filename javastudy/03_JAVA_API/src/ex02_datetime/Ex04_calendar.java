package ex02_datetime;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Ex04_calendar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		// java.util.Calendar 클래스
		// 현재 날짜 또는 특정 날짜를 나타낼 때  사용
		// 날짜의 특정 요소 (년, 월, 일, 시, 분, 초 ..)를 쉽게 사용
		
		Calendar cal = Calendar.getInstance()  ; ///Int에서 컨트롤+스페이스 해서 자동완성 기능 쓰기  직접입력하면 인식이 안됨 
		// 객체 cal은 현재 
		// Calendar = 클래스, cal=객체
		
		
		
		//년, 월, 일 , 요일
		int year=cal.get(Calendar.YEAR); //int year = cal.get(1);   // 캘린더 클래스에 있는 year 상수값을 가지고 오라는 표기법 /////컨트롤 누른 상태로 캘린더 누르면 클래스 열림
		int month = cal.get(Calendar.MONTH)+1; //월은 반환되는값이 1~11(주의요망)
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int weekNo = cal.get(Calendar.DAY_OF_WEEK); //요일번호 : 일요일 1번 월요일 2번....
		
		System.out.println(year);
		System.out.println(month);          ///월은 하나 밀려서 나옴
		System.out.println(day);
		
		
		switch(weekNo) {
			case 1: System.out.println("일요일") ; break;
			case 2 : System.out.println("월요일"); break;
			case 3 : System.out.println("화요일"); break;
			case 4 : System.out.println("수요일"); break;
			case 5 : System.out.println("목요일"); break;
			case 6 : System.out.println("금요일"); break;
			default : System.out.println("토요일");
		}
		
		System.out.println(weekNo);      
		
		
		

		
		
		

		//오전/오후, 시, 분, 초 
		
		int ampm = cal.get(Calendar.AM_PM);  // 오전은 (0) 오후는 (1)
		int hour12 = cal.get(Calendar.HOUR); // 시 (1~12)
		int hour24 = cal.get(Calendar.HOUR_OF_DAY);	//시 (0~23)
		int minute = cal.get(Calendar.MINUTE); // 분(0~59)
		int second = cal.get(Calendar.SECOND); // 초 (0~59)
		
		
		
		
		//timestamp
		long timestamp = cal.getTimeInMillis();
		System.out.println(timestamp);
		
		
		//패턴 
		//패턴의 적용 결과는 String
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("a h:00 yyy-mm-dd");
		//String date = dtf.toFormat(now);
		//System.out.println(date);
		//		

	}

}
