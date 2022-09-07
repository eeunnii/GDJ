package ex06_select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



//여러개의 행 읽기. 행 하나 = Board 하나, ArrayList 사용하기 

public class SelectOneMain2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			
			String sql = "SELECT COUNT(*) FROM BOARD";			//별명을 생략하면 벌어지는 일 . 함수식 그대로 출력됨. rs.getInt(함수식)으로 해야됨
			ps = con.prepareStatement(sql);
			//쿼리문 실행
			rs = ps.executeQuery();
			
			if(rs.next()) {
				/*
				 *  |총 개수|
				 *  |   3   |  <=rs.next() 호출로 인해 현재 rs 포인터의 위치 
				 *  
				 *  rs.getInt("총 개수")  >> 칼럼의 이름으로 접근하는 방법
				 *  
				 *  rs.getInt(1) 		  >> 칼럼의 열번호로 접근하는 방법 
				 *  
				 */
				
				int count = rs.getInt("COUNT(*)");
				System.out.println(count);
			}  // 집계 함수의 결과 : count 집계함수의 결과는 else 처리할 필요가 없음 
			
			
			
			
			
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
