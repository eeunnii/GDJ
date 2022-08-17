package ex04_socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Socket clientSocket = null;
		
		try {
			
			// Socket 생성 및 바인딩 
			clientSocket = new Socket();
			
			//서버 접속
			clientSocket.connect(new InetSocketAddress("localhost",9099));
			
			//서버에 접속되면 welcome 메시지가 넘어옴
			//서버가 dataoutputstream의 writeUTF()로 메시지를 전송하므로 
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			String message = in.readUTF();
			System.out.println("[클라이언트]" + message);
			in.close();
			
			//Scanner 클래스를 이용해 입력 받은 데이터를 서버로 보내기
			java.util.Scanner sc = new java.util.Scanner(System.in);
			System.out.println("서버로 전송할 메세지 >>> ");
			String send = sc.next();
			OutputStream out = clientSocket.getOutputStream();
			out.write(send.getBytes("UTF-8"));
			
			//입출력 스트림 종료 
			out.close();
			in.close();
			sc.close();
		
			
			
			
			
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(clientSocket.isClosed() == false) {
					System.out.println("[클라이언트] 클라이언트 종료");
					clientSocket.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}

	}

}
