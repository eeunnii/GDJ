package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Board;

public class BoardDao {
	
	//SqlSesstionFactiory 빌드 (가장먼저 해주어야함)
	private SqlSessionFactory factory;
																																
	// Dao를 호출하면 factory도 만들어져서 메소드 호출 가능해짐
	// singleton pattern
	private static BoardDao dao = new BoardDao();
	private BoardDao() {
		
		try {
			// SqlSessionFactory 빌드	
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);				
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static BoardDao getInstance() {
		return dao;
	}
	
	// 모든 method는 SqlSessionFactory로부터 SqlSession을 얻어서 사용
	// method 
	
	// Tip. 메소드이름을 실행할 쿼리문의 id와 맞추자 
	
	// 1. 게시글 목록
	public List<Board> selectAllBoards(){
		//SqlSession 객체 : 실제 board.xml의 sql문을 호출하는데 사용됨
		SqlSession ss = factory.openSession();	// SELETE(커밋이 필요 없는 경우) // JDBC는 AUTO COMMIT이 기본임
		List<Board> boards = ss.selectList("mybatis.mapper.board.selectAllBoards");   // Mybatis.mapper.board 매퍼의 id="selectAllBoards"를 가진 쿼리문 실행
																					// mapper의 namsespace가 Mybatis.mapper.board 임
																					// mapper의 id가 selectAllBoards임
																					// mapper의 name과 mapper안에 있는 쿼리문의 id를 이어서 적어줌
		// pk를 조건으로 주면 selete가 한개 나올 수 있음 = selectOne() 메소드가 있음
		// 여러개 나오는거면 selectList 쓰면 됨
		
		ss.close(); // 메소드마다 닫아 주어야 한다.
		return boards;
		
	}
	
	// 2. 게시글 상세 보기 
	public Board selectBoardByNo(int boardNo) {
		SqlSession ss = factory.openSession();
		Board board = ss.selectOne("mybatis.mapper.board.selectBoardByNo", boardNo);  // boardNo를 파라미터로 전달
		ss.close();
		return board;
	}
	
	// 3. 게시글 추가
	public int insertBoard(Board board) {
		SqlSession ss = factory.openSession(false); // INSERT(커밋이 필요한 경우)
			     // int insert(query_id, Object obj)
		int result = ss.insert("mybatis.mapper.board.insertBoard", board); // board를 파라미터로 전달함
		if(result > 0) {
			ss.commit(); // 성공하면 커밋
		}
		ss.close();
		return result;
	}
	
	
	// 4. 게시글 삭제 
	public int deleteBoard(int boardNo) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete("mybatis.mapper.board.deleteBoard",boardNo); // 아이디는 해당 메소드 이름으로 
		if(result>0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// 5. 게시글 수정
	public int updatdBoard(Board board) {
		SqlSession ss = factory.openSession(false); // UPDATE(커밋이필요한 경우)
		int result = ss.update("mybatis.mapper.board.updatdBoard", board);
		if(result>0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
}
