package repository;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class ContactDAO {
	
	
	
	
	// DAO는 하나의 객체만 생성할 수 있도록
	// Singleton Pattern으로 생성
	
	// Singleton 패턴 - 1
	// ContactDAO 객체를 하나 만들어 둔다.
	private static ContactDAO contactDAO = new ContactDAO();
	
	// Singleton 패턴 - 2
	// 외부에서는 ContactDAO 객체를 못 만들도록 제한한다.
	// private 생성자를 이용한다.
	private ContactDAO() {
		
	}
	
	// Singleton 패턴 - 3
	// 만들어 둔 ContactDAO 객체를 외부에 반환한다.
	static public ContactDAO getInstance() {
		return contactDAO;
	}
	
	//static 메소드는 static끼리만 놀 수 있음
	
	// 패턴 1,2 에 왜 static 붙는지 알기
	
	
	//패턴2 때문에 객체를 이용한 메소드 호출안됨
	
	
	
	
//	
//	public static void main(String[] args) {
//		//ContacDAO 객체 만들기  -- 만들어져있는거 받아오기임.
//		ContactDAO dao1 = ContactDAO.getInstance();
//		ContactDAO dao2 = ContactDAO.getInstance();
//		
//		System.out.println(dao1 == dao2);
//		// 왜 같은거임??
//		
//	}
//	
	
	
	
	
	
	/******************Field*********************/
	//데이터베이스에 접근할 때 사용하는 공통요소
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/******************Method*********************/
	//모든 데이터베이스 작업(CRUD : CREATE/READ/UPDATE/DELETE)의 공통 작업은 2가지 
	//1. 커넥션 CONNECTION 객체 생성.
	//2. CLOSE
	
	// 공통 메소드 -1 
	public Connection getConnection() throws Exception{
		
		//OracleDriver 클래스 로드 
		Class.forName("oracle.jdbc.OracleDriver");
		
		//Connection 객체 반환
		//db.properties 파일의 접속 정보 읽기 
		Properties p = new Properties();
		p.load(new FileReader("db.properties"));   //경로없는 파일은 프로젝트 디렉터리에 있다는 듯
		String url = p.getProperty("url");
		
		String user = p.getProperty("user");
		String password = p.getProperty("password");
		return DriverManager.getConnection(url, user, password);
	}
	
	
	
	//공통 메소드 -2 
	public void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		try {
			ContactDAO dao = ContactDAO.getInstance();
			Connection con = dao.getConnection();
			
			System.out.println("접속성공");
		}catch(Exception e) {
			System.out.println("오류");
		}
	}
	
	

}
