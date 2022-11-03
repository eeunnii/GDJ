package com.gdu.app06.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.gdu.app06.domain.BoardDTO;


/*
 * @Repository
 * 안녕 난 DAO에 추가하는 Component야. 
 * servlet-context.xml에 등록된 <context: component-scan> 태그에 의해서 Bean으로 검색되지
 * root-context.xml이나 @configuration에 @Bean으로 등록하지 않아도 컨테이너에 만들어짐
 */


@Component   // 이걸 bean으로 만들어 두란 뜻! // root content, config작업 안함.
			// servlet-context.xml 에 context:component-scan가 있어서 가능함!


@Repository // DAO가 사용하는 @Component로 트랜잭션 기능이 추가되어 있어.
public class BoardDAO {         // DAO객체는 싱글톤 객체!! 컨테이너에 저장됨!!!! 그래서 다른 class에서 @Autowired(bean가져오는애)로 부를 수 잇음!!
	
	// 객체로 만드는 방법 :  이 BoardDAO 가 component라고 알려준다!
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	
	// private메소드는 BoardDAO에서만 쓴다.
	private Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","TIGER");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	private void close() {
		try {
			if(rs != null) {rs.close();}
			if(ps != null) {ps.close();}
			if(con != null) {con.close();}
		}catch(Exception e) { 
			e.printStackTrace();
		}
	}
	
	
	// 레파지로리 계층의 이름은 "DB친화적으로"
	public List<BoardDTO> selectAllBoards(){
		List<BoardDTO> boards = new ArrayList<BoardDTO>();
		try {
			con=getConnection();
			sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD ORDER BY BOARD_NO DESC";
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			// next 한 행마다 한번씩 10줄이면 10번 . 위에서부터아래로 순서대로 검색함
			// 순서는 쿼리문에서 지정한대로
			while(rs.next()) {
				BoardDTO board = new BoardDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				boards.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(); // con안끊고 db에 계속 접속된 상태로 냄겨둘 수 있는데 별로 안좋음
		}
		return boards;
	}
	
	
	public BoardDTO selectBoardByNo(int board_no) {
		BoardDTO board = null;
		try {
			con = getConnection();
			sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD WHERE BOARD_NO=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			rs = ps.executeQuery();
			if(rs.next()) {
				board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return board;
	}
	
	
	public int insertBoard(BoardDTO board) {
		int result =0;
		try {
			con= getConnection();
			sql="INSERT INTO BOARD(BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE)"
					+ " VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'),TO_CHAR(SYSDATE, 'YYYY-MM-DD'))" ; // 물음표는 변수 받는 자리 // 매개변수 board에 저장되어있는 변수
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getWriter());
			result = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}
	


	public int updateBoard(BoardDTO board) {
		int result =0;
		try {
			con = getConnection();
			sql = "UPDATE BOARD SET TITLE=?, CONTENT=?, MODIFY_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD') WHERE BOARD_NO=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getBoard_no());
			result = ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}
		
	public int deleteBoard(int board_no) {
		int result =0;
		try {
			con = getConnection();
			sql = "DELETE FROM BOARD WHERE BOARD_NO=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			result = ps.executeUpdate();
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		close();
	}
	return result;
		
}
}
