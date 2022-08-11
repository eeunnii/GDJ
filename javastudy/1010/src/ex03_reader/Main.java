package ex03_reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	
	
	
	public static void m1() {
		
		//FileReader 클래스 생성 //writer의 반대 
		File file = new File("C:\\storge", "m2.txt");
		FileReader fr = null;
		
		
				
		try {
		
		
		// fileReader클래스 생성
		// file 객체에 등록된 파일이 없으면 FileNotFoundException 발생
		
			fr = new FileReader(file);
			
			//입력 데이터 
			//1. 1글자 : int
			//2. 여러 글자 : char[]
			
			
			//1글자를 저장할 변수 
			int c;
			
			//read() 메소드
			//1.읽은 문자를 반환
			//2.모두 읽어서 읽은 문자가 없으면 -1 반환
			
			//String str에 파일내용 저장하기 // 스트링 + 많이하면 성능 떨어져서 안좋음 
//			String str = "";
//			StringBuilder sb = new StringBuilder();
//			
//			while(true) {
//				c = fr.read();		//한글자 읽어옴
//				if (c== -1) {			// 없으면 -1 반환
//					break;
//				}
//				sb.append((char)c);		//
//			}
			
			StringBuilder sb = new StringBuilder();
			while(true) {
				c = fr.read();
				if(c == -1) {
					break;
				}
				sb.append((char)c);
			}
			String str = sb.toString();
			System.out.println(str);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
		
	}
	
	public static void m2() {
		// 글자수를 지정해서 읽어들이는 메소드
		
		File file = new File("C:\\storage", "m3.txt");
		FileReader fr =null;
		try {
			fr = new FileReader(file);
			
			// 3글자를 저장할 배열
			char[] cbuf = new char[5];
			
//			read(char[]) 메소드
			// 1. 읽은 글자는 cbuf 배열에 저장
			// 2. 실제로 읽은 글자수를 반환
			// 3. 읽은 글자가 없으면 -1 반환
			// 글자를 읽어왔으면 반환값도 5임
			// 만약 글씨가 13글자인데 5글자씩 읽어오라고 했을 때 : 5,5,3,-1 동작으로 정상 실행됨
			
			//m3.txt 파일 읽는 과정
			// readCnt cbuf
			// 1     5 			a  p  p  l  e
			// 2     5         \n  m  a  n  g
			// 3     2   		o \n  a	 n  g		//	남은 3칸은 위에서 읽어드렸던 3개가 그대로 남아있음. 덮어씌우기 개념으로 데이터를 불러오기 때문임
			// 4    -1
			
			while(true) {
				
				int readCnt = fr.read(cbuf);		//"나는 아이"가 cbuf에 들어가고 5가 readCnt에 들어간다 
				
				if(readCnt == -1) {
					break;
				}
				
				for(int i=0; i<readCnt; i++) {		// for 조건문 안을 cbuf.length 로 하면 cbuf.length의 길이는 5기 때문에 
														// 마지막 출력값이 3이 되었을 때 
					System.out.print(cbuf[i]);
				}
				
			}
			
			
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (fr !=null) {
					fr.close();
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void m3() {

		
		
		// m3.txt에 읽어서 String str에 저장하기
		
		File file = new File("C:\\storage", "m3.txt");
		FileReader fr = null;
		try {
			
			fr = new FileReader(file);
			
			/*
			char[] cbuf = new char[5];
			StringBuilder sb = new StringBuilder();
			while(true) {
				int readCnt = fr.read(cbuf);
				if(readCnt == -1) {
					break;
				}
				sb.append(cbuf, 0, readCnt);  // cbuf 배열의 인덱스 0부터 readCnt개만 추가
			}
			String str = sb.toString();
			System.out.println(str);
			*/
			
			char[] cbuf = new char[5];
			StringBuilder sb = new StringBuilder();
			int readCnt;
			while((readCnt = fr.read(cbuf)) != -1) {
				sb.append(cbuf, 0, readCnt);
			}
			String str = sb.toString();
			System.out.println(str);
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null) {
					fr.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	public static void m4() {
		
		// FileReader는 느리기 때문에 
		// BufferedReader를 추가해서 속도를 향상시킨다.
		
		// BufferedReader는 readLine() 메소드를 지원한다.
		// readLine() 메소드는 한 줄씩 읽어서 String에 저장한다. 
		// 읽은 내용이 없으면 null를 반환한다.
		
		File file = new File("C:\\storage", "m3.txt");
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			String str = sb.toString();
			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		
	}
		
		
	public static void m5() {
		// try-catch-resources문으로 m4() 다시 풀기
		// fr 선언없이 해보기
		
				try (BufferedReader br = new BufferedReader(new FileReader("C:\\storage\\m3.txt"))) {
					StringBuilder sb = new StringBuilder();
					String line = null;
					while((line = br.readLine()) != null) {
						sb.append(line + "\n");
					}
					String str = sb.toString();
					System.out.println(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
		
	}
	
	public static void main(String[] args) {

		m4();
	
	}

}
