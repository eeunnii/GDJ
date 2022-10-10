package prac2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
	
	public static void m1() {
		
		// 파일 어쩌고 복사하기 
		// 복사할 파일명은 eclipse.zip
		
		File src = new File("c:\\GDJ\\installer", "Exam01.zip");
		File cpy = new File("c:\\GDJ\\installer", "Exam02.zip");
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			bis = new BufferedInputStream(new FileInputStream(src));
			bos = new BufferedOutputStream(new FileOutputStream(cpy));
			
			byte[] b = new byte[1024];
			int readByte = 0;
			
			while((readByte = bis.read(b)) != -1) {
				bos.write(b, 0, readByte);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	
	public static void m2() {
		
		File src = new File("c:\\GDJ\\installer", "Exam01.zip");
		File cpy = new File("c:\\GDJ\\installer", "Exam02.zip");
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			bis = new BufferedInputStream(new FileInputStream(src));
			bos = new BufferedOutputStream(new FileOutputStream(cpy));
			
			
			byte[] b = new byte[1024];
			int readByte = 0;
			
			while((readByte = bis.read(b)) != -1) {
				bos.write(b, 0, readByte);
			}
			
			src.deleteOnExit();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			m1();
	}

}
