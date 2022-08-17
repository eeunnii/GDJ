package prac2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import ex03_socket.ServerMian;





public class Server extends Thread{
	
	private Socket client;
	
	private BufferedReader in;
	
	private BufferedWriter out;

	public Server(Socket client) {
		try {
			this.client = client;
	
			
			in = new BufferedReader(new java.io.InputStreamReader(client.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message) throws IOException{
		out.write(message);
		out.flush();
	}
	
	@Override
	public void run() {
			
		InetSocketAddress address = null;
			
			
			try {
				
			
				
			while(true) {
				String message = in.readLine();
				if(message == null || message.equalsIgnoreCase("exit")) {  // 채팅창에 exit 입력하면 채팅 종료
					break;
				}
				// 모든 클라이언트에게 메시지 출력
				address = (InetSocketAddress)client.getRemoteSocketAddress();
				for(Server server : ServerMain.servers) {
					server.sendMessage(address.getHostName() + "의 메시지 : " + message);
			}
			}
			
			// List<server> servers에서 등록된 서버 제거 
			ServerMain.servers.remove(this);
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
