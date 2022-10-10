package ex02_datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

//같은 이름의 클래스를 불러올 순 없다 . date 2개 호출할거면 하나는 import하고 나머지 하나는 클래스명을 표기해줘야한다

public class Ex06_SimpleDateFormat {

	public static void main(String[] args) {
		//java.text.SimpleDateFormat 클래스 
		//패턴이 적용된 String 타입의 날짜 반환
		
		Date dete1 = new Date();
		java.sql.Date date2 = new java.sql.Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String result1 = sdf.format(dete1);
		String result2 = sdf.format(date2);
		
		System.out.println(result1);
		System.out.println(result2);
	}

}
