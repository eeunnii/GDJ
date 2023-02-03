package ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrateTableName {

	public static void main(String[] args) {

		//connection 생성
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");		//이거 맨윗줄에
			String url = "jdbc:oracle:thin:@localhost:1521:xe";  //Thin 이 아니고 thin임
			String user = "scott";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			
		}catch(ClassNotFoundException e) {
			System.out.println("OracleDriver 로드 실패");
		}catch(SQLException e) {
			System.out.println("DB접속정보 오류");
		}
		
		// CREATE TABLE 실행
		
		
		PreparedStatement ps = null;
		try {
			//String 타입의 쿼리문 작성
			//쿼리문의 마지막 세미콜론(;)은 jdbc에서 사용하지 않는다
			StringBuilder sb = new StringBuilder();
			sb.append("CREATE TABLE BOARD(");						// create 철자 조심
			sb.append("BOARD_NO NUMBER NOT NULL CONSTRAINT PK_BOARD PRIMARY KEY, ");		//변수명에 밑줄
			sb.append("TITLE VARCHAR2(100 BYTE) NOT NULL, ");
			sb.append("CONTENT VARCHAR2(100 BYTE) NULL, ");
			sb.append("HIT NUMBER NOT NULL, ");
			sb.append("CREATE_DATE DATE NOT NULL)");
			//컨트롤 쉬프트 엑스. 대문자로 변환
			//컨트롤 쉬프트 와이. 소문자로 변환

			String sql = sb.toString();

			//쿼리문을 실행하는 PreparedStatement 객체 생성
			//PreparedStatement쓰기 위해선 쿼리문이 이미 나와있어야된다 
			//prepared : 미리 준비하라 , statment : 쿼리문
			//역할 : 쿼리문 실행을 담당함
			ps = con.prepareStatement(sql);
			
			// 쿼리문 실행 
			// statment는 다 거른다 
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}

		// CONNECTION 닫기 , preparedStatment 닫기
		try {
			if(ps != null) ps.close();
			if(con !=null) con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}

		
	}

}
