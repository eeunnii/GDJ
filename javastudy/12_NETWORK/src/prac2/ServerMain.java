package prac2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {
	
	//server : 스레드로 세팅 
	//client : 클라이언트로 셋팅
	//server 하나 당 client 하나 담당할거임
	
	
	// 생성된 servser 목록
	public static List<Server> servers = new ArrayList<>(); // <>안 생략 가능~~~~~~
	
	//모든 server에 메세지 전송
	public static void sendMessage(String message) throws IOException{
		for(Server server : servers ) {
			server.sendMessage(message);
		}
		
	}

	public static void main(String[] args) {
		
		
		ServerSocket server = null;
		Socket client = null;
		
		try {
			
			
			server = new ServerSocket();
			server.bind(new InetSocketAddress("localhost", 9090));
			System.out.println(" 채팅 서버 오픈 ");
			
			while(true) {
				
				client = server.accept();
				Server s = new Server(client);
				servers.add(s);
				
				s.start();
				
				System.out.println("현재 접속 중인 클라이언트" + servers.size()+"명");
				
			}

			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		

	}

}
