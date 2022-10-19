package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.Board;

public class BoardDao {
	
	// field
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// Connection Pool 관리 
	private DataSource dataSouce;
	// singleton - pattern (★연습많이해보기)
	private static BoardDao dao = new BoardDao();
	private BoardDao() {
		try {
			// DataSource 객체 생성
			// context.xml에서 name="jdbc/oracle11g"
			Context ctx = new InitialContext();
			Context envCtx = (Context)ctx.lookup("java:comp/env");   // "java:comp/env" 정해져있는 이름 - ppt에 내용 있음
			dataSouce = (DataSource)envCtx.lookup("jdbc/oracle11g");
			//dataSouce = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle11g");
			
		}catch(NamingException e) {  // 이름 잘못 써서 난 오류 --원하는 이름의 context를 못 찾았을 때.
			e.printStackTrace();
		}
		//dao가 만들어질 때 데이터소스도 만들어짐
	}
	public static BoardDao getInstance() {
		// 접근제어자 맞춰주기
		return dao;
		// static이 붙은 애들은 클래스 이름으로 호출하는 클래스메소드임
		// BoardDao.getinstance... 누구세요
	}
	
	// method
	
	// 1. 접속/자원 해제 
	public void close(Connection con,PreparedStatement ps, ResultSet rs) {
		try {
			if(rs!=null) {rs.close();}
			if(ps!=null) {ps.close();}
			if(con!=null) {con.close();} // Connection Pool의 close()는 Connection종료가 아닌 Connection 반환으로 동작한다 다른 기능이 래핑되어잇는 클래스??
		}catch (Exception e) {
			e.printStackTrace();  // 
		}
	}
	
	// 2. 목록 반환하기 
	public List<Board> selectAllBoards(){
		List<Board> boards = new ArrayList<Board>();
		try {
			con = dataSouce.getConnection(); // 커넥션풀로부터 connection 대여 .. max..8개 설정했던거 중에서 1개 내놔 이런 느낌
			sql = "SELECT BOARD_NO, TITLE, CONTENT, CREATE_DATE FROM BOARD ORDER BY BOARD_NO DESC";// "SELECT *" 절대안됨
			ps = con.prepareStatement(sql);  // Statement는 쿼리문임. 메소드뜻=쿼리문을 준비해라.  해당메소드는 sql injection(보안이슈)으로부터 안전함 , Statement 안전하지않음
			rs = ps.executeQuery(); // select문 돌릴 때 쓰는 메소드 (딴건안됨)
			//rs는 포인터인데 아무것도 가리키고 있지 않아서 rs.next()해줘야함, 다음 행 가져올려면 rs.next()해야함. 
			//최초의 rs는 아무것도 가리키고 있지 않다.
			//rs.getint("board_no")====== rs.getint(칼럼번호)  rs.next()해주고 둘 중에 하나 써서 데이터 가져와야함
			while(rs.next()) {//목록보기는 while 문으로 처리한다
				// Board board는 한 개의 게시글을 의미함
				Board board = new Board();
				board.setBoard_no(rs.getInt(1));  // rs.getInt("BOARD_NO")
				board.setTitle(rs.getString(2));  //깃허브
				board.setContent(rs.getString(3));
				board.setCreate_date(rs.getDate(4)); // 4열. 깃허브 
				// 가져온 게시글을 리스트에 담아야함
				boards.add(board);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally { // 예외와 상관없이 무조건 실행
			close(con, ps, rs);
		}
		return boards;
	}
	
	
	
	//요청 컨트롤러 서비스 dao 
	// 코드 구현의 생산성은.. dao부터 가야 빠름...
	
	// 3. 상세보기
	public Board selectBoardByNo(int board_no) {
		Board board = null;  // null값이 넘어갈 수 있을 수도 있으니까 이를 대비해 처리해두는 코드
		try {
			con = dataSouce.getConnection();
			sql = "SELECT BOARD_NO, TITLE,CONTENT, CREATE_DATE FROM BOARD WHERE BOARD_NO=?"; // 변수는 물음표처리할 것. 
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no); // 1번째 물음표(?)의 board_no 전달하기
			rs = ps.executeQuery(); // select문은 executeQuery()사용
			if(rs.next()) { // 있을 수도 없을 수도 있으니까  , 결과가 1개 아니면 없어서 if사용
				board=new Board();
				board.setBoard_no(rs.getInt(1));  // rs.getInt("BOARD_NO")
				board.setTitle(rs.getString(2));  //깃허브
				board.setContent(rs.getString(3));
				board.setCreate_date(rs.getDate(4)); // 4열. 깃허브 
			}
		
		
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		close(con, ps, rs);
	}
		return board;
	
}
	
		//4. 게시글 삽입
	public int insertBoard(Board board) {
		int result = 0;
		try {
			con = dataSouce.getConnection();
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle()); //첫번째 ? 에 titile넣음
			ps.setString(2, board.getContent()); //첫번째 ? 에 tContent넣음
			result = ps.executeUpdate(); // INSERT문은 executeUpdate() 메소드 사용 // 리절트는 정수
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps,null);
		}		//리절트 문은 셀렉트 문에서만 사용가능
		return result;
	}
	
	public int updateBoard(Board board) {
		int result = 0;
		try {
			con = dataSouce.getConnection();
			sql = "UPDATE BOARD SET TITLE=?, CONTENT=? WHERE BOARD_NO=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getBoard_no());
			result = ps.executeUpdate();  // INSERT문은 executeUpdate() 메소드 사용
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, null);
		}
		return result;
	}
	
	
	// 6. 게시글 삭제 
	public int deleteBoard(int board_no) {
		int result = 0;
		try {
			con = dataSouce.getConnection();
			sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			result = ps.executeUpdate(); // delete문은 executeUpdate()메소드 사용
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, null);
			
		}
		return result;
	}
	
	
	
	
	
	
}
