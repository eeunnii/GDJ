package ex05_InPutSteam;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.List;

import ex04_outputstream.User;

public class Main {
	
	
	
	public static void m1() {
		
		File file = new File("c:\\stoage", "b1.bin");
		FileInputStream fis = null;
		
		
		try {
			
			//바이트 입력 스트림
			fis = new FileInputStream(file);
			
			//입력 데이터 단위
			// 1. 1개 : int
			// 2. 여래개 : byte[]
			
			// 모든 정보를 SringBuilder에 저장한 뒤 확인
			StringBuilder sb = new StringBuilder();
			byte [] b = new byte[5];	//5바이트씩 읽어올 예정
			int readByte = 0;
			
			//int read(byte[]b)
			//읽은 내용은 byte 배열 b에 저장함.
			//  읽은 바이트 수를 반환
			// 읽은 내용이 없으면 -1 반환
			
			
			
			while((readByte = fis.read(b)) != -1)  {
				sb.append(new String(b,0,readByte));
				//
			}
			
			//문자를 byteStream으로 읽었기 때문에 문제가 발생
			System.out.println(sb.toString());
			
			
			
			// 한글은 byteStream으로 처리하면 안됨
			// 문자라고 하면 byte로 처리하는게 일반적
			// 원래 이렇게 하면 안된다는게 무슨...?
			
			
			
			
		}catch (IOException e ) {
			e.printStackTrace();
		}finally {
			try {
			if (fis != null) fis.close(); 
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void m2() {
		
		//바이트 입력 스트림을 문자 입력 스트림으로 변환하는 InputStreamReader
		
		File file = new File("c:\\storage", "b2.bin");
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		try {
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			
			StringBuffer sb = new StringBuffer();
			char [] cbuf = new char[5]; // 5글자씩 읽기 위해서
			int readCnt = 0;
			
			while((readCnt = isr.read(cbuf)) != -1) {
				sb.append(cbuf, 0, readCnt);
			}
			System.out.println(sb.toString());
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
			if (isr != null) isr.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	public static void simsim() {
		try {
			
			
			//system.in 은 컴퓨터와 연결된 byte 스트림 
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("입력 >>> ");
			String str = br.readLine();
			System.out.println(str);
			
			br.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void m3() {
		
		
		//변수를 그대로 입력 받는 DataInputStream
		
		//byte 스트림은 ....
		
		File file = new File("C:\\storage", "b3.dat");		//readobject할 때 2개의 
		
		FileInputStream fis = null;
		DataInputStream dis = null;
		
		try {
			
			fis = new FileInputStream(file);
			dis = new DataInputStream(dis);
			
			String name = dis.readUTF();
			
			int age = dis.readInt();
			double height = dis.readDouble();
					System.out.println(name + ","+age+","+height);
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(dis != null) dis.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	public static void m4() {

		// 객체를 그대로 입력 받는 ObjectInputStream
		
		File file = new File("C:\\storage", "b4.dat");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			
			List<User> users = (List<User>)ois.readObject();  // 오류나도 괜찮다는 메소드
			User user = (User)ois.readObject();
			
			for(User u : users) {
				System.out.println(u);
			}
			System.out.println(user);
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ois != null) ois.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) {
	
		

		
		m3();
		
		
		
		
		
	}

}
