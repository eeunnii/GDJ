package com.gdu.app9.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdu.app9.domain.BoardDTO;

@Repository  // 컴포넌트로 등록
public class BoardDAO {

	// SqlSessionTemplate
	// Maybatis에서 지원하는 매퍼처리클래스 


   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;

	
	public List<BoardDTO> selectAllBoards() {
		return sqlSessionTemplate.selectList("mybatis.mapper.board.selectAllBoards");
	}
	
	public BoardDTO selectBoardByNo(int boardNo) {  // 예전에 매개변수 해킹 시도가 있었다. 그래서 final이 붙을 수 있다. 
		return sqlSessionTemplate.selectOne("mybatis.mapper.board.selectBoardByNo");
	}
	
	public int insertBoard(BoardDTO board) { 
		return sqlSessionTemplate.insert("mybatis.mapper.board.insertBoard", board);
	}
	
	public int updateBoard(BoardDTO board) {
		return sqlSessionTemplate.update("mybatis.mapper.board.updateBoard", board);
	}
	
	public int deleteBoard(int boardno) {
		return sqlSessionTemplate.delete("mybatis.mapper.board.deleteBoard", boardno);
	}
	
}
