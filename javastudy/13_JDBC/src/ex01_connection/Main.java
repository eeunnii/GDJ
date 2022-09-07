package ex01_connection;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		// 1. oracle.jdbc.OracleDriver
		// 2. oracle.jdbc.driver.OracleDriver 
		// 둘 중에 하나 사용함 
		
		
		
		 
		
		
		//oracleDriver 열기(메모리에 로드하기)
		try {
			Class.forName("oracle.jdbc.OracleDriver");    //○외우기
		} catch(ClassNotFoundException e) { 
			System.out.println("OrcleDriver로드 실패");
		}
		
		 
		Connection con = null;
		
		try {
			String url = "jdbc:oracle:Thin:@localhost:1521:xe";   //  url은 달라짐 (oracle xe 버전 기준))  -- 라잍트 버전이라는 뜻
			String user = "scoot";
			String password = "TIGER";		//	계정을 만들 때 사용한 대소문자를 지켜야함
			con =  DriverManager.getConnection(url, user, password);
			System.out.println("db접속 성공");
			
		}catch(SQLException e) {
			System.out.println("DB접속정보 오류");
		}
		
		//DriverManager로부터 Connection 받아오기 드라이버 매니저가 디비접속을 해서 객체를 반환해줌.
		
	
	
	//connection 종료 
	//jdbc에서는 connectrion을 닫는 것이 굉장히 중요함
     
		try {
			if(con != null)
				con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
}
	}
	
