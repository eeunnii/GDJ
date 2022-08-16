package ex03_socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

//클라이언트 없으면 f11 눌러서 실행안됨

public class ServerMian {
	
	// ServerSocket
	// 클라이언트와 통신할 때 사용하는 클래스 
	
	// InetSocketAddress 
	// Sochek 사용 시 호스트 이름과 포트번호를 관리하는 클래스
	
	// Socket
	// 클라이언트가 서버와 통신할 때 사용하는 클래스
	
	
	//client는 socket을 가지고 통신할려고 함 

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ServerSocket serverSocket = null;
		
		try {
			
			
			// Serversocket 생성
			
			serverSocket = new ServerSocket();
			
			// ServersocketAddress 생성
			InetSocketAddress address = new InetSocketAddress("localhost", 9090);    /// http://localhost:9090
			
			//Serversocket에 InetsocketAddress연결
			serverSocket.bind(address);
			
			//serverSocket는 무한루프로 구현
			//콘솔창에서 중지버튼 눌러줘야함
			while(true) {
				System.out.println("[서버] 클라이언트 접속을 기다리는 중");
				Socket client = serverSocket.accept();				//	accept 허용 한다는 뜻
				InetSocketAddress clientAddress = (InetSocketAddress)client.getRemoteSocketAddress();
				System.out.println("접속이 허용된 클라이언트 : "+clientAddress.getHostName());
				
				
				
				
				//127.0.0.1 같은자리라는 뜻
				
				
				
			}
			
			
			
			
			
			
			
			
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
