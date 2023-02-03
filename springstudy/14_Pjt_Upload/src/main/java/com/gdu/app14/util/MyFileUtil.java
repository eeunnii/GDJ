package com.gdu.app14.util;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Matcher;

import org.springframework.stereotype.Component;



@Component
public class MyFileUtil {
	
	//FileUtil는 자바에 있는 클래스 
	
	
	
	// 파일명 : UUID값을 사용 (자바에서 지원해줌)
	// 경로명 : 현재 날짜를 디렉터리로 생성해서 사용 -- 매일매일 폴더가 새로 생길거임
	
	
	public String getFilename(String filename) {
		
		// 전달받는 매개변수 filename에 확장자까지 포함되어있으니까 파라미터로 받음
		
		// 파라미터로 전달된 filename의 확장자만 살려서 UUID.확장자 방식으로 반환
		
		// 중요한 이유1. 유니크처리를 위해서 // 
		// 디렉토리 하나에 똑같은 이름 2개 못들어감. 근데 여러명의 유저가 함께 올리면 중복가능성이 있으니
		
		// 원래 올렸던 이름은 DB에 저장하고, 디렉토리에 저장할 때는 UUID이름을 적용한 파일 저장
		
		// 유니크ID값을 쓰면 인코딩이 필요 없어짐. 한글이 포함되지 않기 때문이다. 숫자+알파벳으로만 이루어짐
		
		
		
		// 확장자 예외 처리 
		String extension = null;
		if(filename.endsWith("tar.gz")) {
			
			extension="tar.gz";
		}else {
			String[] arr = filename.split("\\.");
			
			// split() 에서 () 안은 정규식으로 인식함 , split()은 정규식을 사용함 컨트롤 누른 상태에서 split클릭해보면 알 수 있삼
			// 정규식에서 마침표는 모든 문자라는 뜻을 가지고 있음. 
			// \\. 마침표를 모든 문자로 인식하지 말고 마침표를 문자로 인식해라 라는 뜻 
			// 정규식에서 .(마침표) 인식 : \. 또는 [.]
			
			extension = arr[arr.length-1];
			
		}
		
		// UUID.확장자(하이픈 없애는 코드)
		return UUID.randomUUID().toString().replaceAll("\\-", "")+"."+extension;
		
	}
		
		// 오늘 경로 
		public String getTodayPath() {
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH)+1; // month는 0부터 11까지 출력
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			String sep = Matcher.quoteReplacement(File.separator);
			return "storage" + sep + year + sep + makeZero(month) + sep + makeZero(day);
			
			// 리눅스나 유닉스 는 / 를 사용 윈도우는 \임
		}
		 
		
		// 어제경로
		public String getYesterda() {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -1);  // 1일 전
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH)+1; // month는 0부터 11까지 출력
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			String sep = Matcher.quoteReplacement(File.separator);
			return "storage" + sep + year + sep + makeZero(month) + sep + makeZero(day);
			
			// 리눅스나 유닉스 는 / 를 사용 윈도우는 \임
		}
		
		// 1~9 => 01~09
		public String makeZero(int n) {
			return (n < 10) ? "0" + n : "" + n;
		}
	

}
