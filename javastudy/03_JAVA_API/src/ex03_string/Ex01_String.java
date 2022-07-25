package ex03_string;



public class Ex01_String {

	public static void main(String[] args) {
		// TODO Auto-generated method stub\

		
		
		// ★★★★★인덱스 중요 ★★★

		String str1 = "hello";
		String str2 = "hello";
		
		System.out.println(str1==str2);
		
		String str3 = new String("hi");
		String str4 = new String("hi");
		
		System.out.println(str3==str4);   //"문자열"이 같은지 확인하는 의도로 만든거면 안됨. 주소값이 달라서 
		
		
		// 1. 문자열 동등 비교 
		boolean result1= str1.equals(str2);   // "문자열이 같은지 확인할려면 메소드를 호출해야한다
		boolean result2 = str3.equals(str4);
		System.out.println(result1);
		System.out.println(result2);
		
		if(str1.equals(str2)) {
			System.out.println("같다");
		} else {
			System.out.println("다르다");
		}
		
		if(!str3.equals(str4)) {
			System.out.println("str3과 str4는 다르다");
		}else {
			System.out.println("str3와 str4는 같다 ");
		}
		
		
		//대소문자를 무시한 문자열 등등 비교 
		String str5 = "hellow world";
		String str6 = "hELLO wORLD";
		
		System.out.println(str5.equals(str6));
		
		boolean result3 = str5.equalsIgnoreCase(str6);   // 대소문자 무시 
		System.out.println(result3);
		
		
		//핑크색 클래스 주황색 메소드
		
		
		// 3. 문자열 길이 변환
		String name = "정은지";
		int length = name.length();
		System.out.println("글자수: "+length);
		
		
		
		//특정 위치의 문자(char)만 반환 
		//특정 위치 
		//인덱스(index)라고 함     (별로 안중요 잘안씀
		//글자마다 부여된 정수값   (별로 안중요 잘안씀
		//0으로 시작               (별로 안중요 잘안씀
		System.out.println(name.charAt(0));
		System.out.println(name.charAt(1));
		System.out.println(name.charAt(2));
		
		
		
		//5. 문자열의 일부 문자열(반환자체가 String)
		//1)substring(begin) : 인덱스 begin부터 끝까지 반환 (★★★★★)
		//2)subString(begin,end) : 인덱스 begin(포함)부터 인덱스 end(불포함)까지 반환
		
		System.out.println(name.substring(0,1));
		System.out.println(name.substring(1));
		
		
		
		// 6. 특정 문자열을 찾아서 해당 인덱스(int)를 반환
		//     ㄱ.indexOf
		//        1) 발견된 첫 번째 문자열의 인덱스를 반환
		//		  2) 발견된 문자열이 없는 경우 -1을 반환
		//	   ㄴ.lastIndexOf
		//        1) 발견된 첫 번째 문자열의 인덱스를 반환
		//		  2) 발견된 문자열이 없는 경우 -1을 반환
	
		
		int idx1 = name.indexOf("정");          //변수이름에 i가 있다 = index라는 뜻.. 줄여서 idx 도 씀
		int idx2 = name.indexOf("ruds지");        //이름 우영우 일 때 : 우를 찾는다 --> 제일 첫번째값 반환됨 		
												//					  없는 글자를 찾는다 --> -1나옴 
		
		
		System.out.println(idx1);
		System.out.println(idx2);
		
		
		
		//7. 문자열이 특정 패턴으로 시작하는지 
		//    starWith(문자열)
		if(name.startsWith("민")) {
			System.out.println("민씨입니다.");
		} else  {
			System.out.println("민씨가아님");
		}
		
		//8. 문자열이 특정 패턴으로 끝나는지 여부를 boolean(true,false) 반환
		// endWith(문자열)
		String filename = "image.jpg";	// jpg, png로  끝나면 이미지로 가정 
		if (filename.endsWith("jpg")||filename.endsWith("png")) {
			System.out.println("이미지입니다.");
		}else {
			System.out.println("이미지가 아닙니다.");
		}
		
		
		// 9.문자열이 특정 패턴을 포함하는지 여부를 boolean(true,false) 반환
		String email = "owo667@naver.com";
		if(email.contains("@")&& email.contains(".")) {
			System.out.println("이메일입니다 "); 
			} else { 
				System.out.println("이메일이 아닙니다.");
		}
		
		
		
		//10. 불필요한 공백 제거 (좌우 공백)
		String message = "  안녕 하세요 " ;
		System.out.println(message.trim());
		System.out.println(message.trim().length());
		
		
		
		// 11. 대소문자 변환하기 
		// toLowerCase() : 대문자로 변환하기 
		// to UpperCase() : 소문자로 변화하기 
		
		String source = "best of best";
		System.out.println(source.toUpperCase());
		System.out.println(source.toLowerCase());
		
		// 12. 찾아 바꾸기 
		// replace(old, new) : old를 new로 변환하기 
		source.replace("best","worst");
		String replaced = source.replace("best", "worst");
		System.out.println(source);
		System.out.println(replaced);
		
		
		//주의 replaceALL() 메소드는 특정 문자열을 찾아서 변환하는 것이 아님
		String ip = "192.168.101" ;
		String replacedIp = ip.replaceAll(".", "_");   //  "." --> 있는 자리는 문자열을 의미하는게 아님
		System.out.println(replacedIp);
		// ------------>모든 문자가 ___으로 출력됨
		
		
		
		// 빈 문자열인지 여부를 검사한 뒤 boolean (true,false)변환
		String id = " ";
		if(id.isEmpty()) {
			System.out.println("빈 문자열");
		}else {
			System.out.println("빈 문자열 아님");
		}
		
//		if(id.trim().id.isBlank()) {
//			System.out.println("빈문자열");
//		}else {
//			System.out.println("빈 문자열아님");
//		}                                             아직 쓰지말라고 하싐 isEmpty만 쓰기
		
		
		
		
	}

}
