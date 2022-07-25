package ex03_string;

public class Ex02_StringBuilder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		
		// Java.lang.StringBuilder 클래스 
		
		StringBuilder sb = new StringBuilder();  ////////import 안되는거 자동완성 써도 됨
	    sb.append(1);
	    sb.append(true);
	    sb.append('T');
	    sb.append(3.14);
	    sb.append("hello");
	    
	    System.out.println(sb);
	    sb.charAt(1);
	    
	    //동등비교 
	    System.out.println(sb.equals("1trueT3.14hello"));
	    
	    //StringBuiler로 만든 문자열은 반드시 마지막에 String으로 변환되어야 한다 
	    String result = sb.toString();
	    
	    
	    
	    
	    
	    
	    //String과  StringBuilder의 성능 테스트 
	    //abcd~~ (성능테스트라고 그냥 보라구 하심)
	    //스트링빌더가 더 빠르다.
	    
//	    String alphabe1 = "";
//	    long begin1 = System.nanoTime();
//	    for(char ch = 'a'; ch<='z'; ch++) {
//	    	alphabe1 +=ch;
//	    	
//	    }
//	    long end1 = System.nanoTime()	;
//	    System.out.println((end1-begin1)+ alphabt1);
//	    
//	    
//	    String alphbet2 ="" ;
//	    stringBuiler sb2 =new StringBuilder();
//	    for(char ch = 'a'; ch<='z'; ch++) {
//	    	sb2.append(ch);
//	    }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
