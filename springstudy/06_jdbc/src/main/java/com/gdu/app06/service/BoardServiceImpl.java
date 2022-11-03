package com.gdu.app06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app06.domain.BoardDTO;
import com.gdu.app06.repository.BoardDAO;


		
/*
 * @Service
 * 안녕 난 DAO에 추가하는 Component야. 
 * servlet-context.xml에 등록된 <context: component-scan> 태그에 의해서 Bean으로 검색되지
 * root-context.xml이나 @configuration에 @Bean으로 등록하지 않아도 컨테이너에 만들어짐
 */

	@Service   // 서비스가 사용하는 @Component // Sercice가 사용하는 $
	public class BoardServiceImpl implements BoardService {
		
	//Service DAO를 사용합니다. 
	@Autowired // 컨테이너에 생성된 bean중에서 BoardDAO 타입의 bean을 가져오시오~~~~!!!!
	private BoardDAO dao;

	@Override
	public List<BoardDTO> findAllBoards() {
		
		return dao.selectAllBoards();
	}

	@Override
	public BoardDTO findBoardByNo(int board_no) {

		return dao.selectBoardByNo(board_no);
	}

	@Override
	public int saveBoard(BoardDTO board) {  // save할 내용은 board 매개변수에 들어 있음.
		System.out.println(board);
		return dao.insertBoard(board);
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		// TODO Auto-generated method stub
		return dao.updateBoard(board);
	}

	@Override
	public int removeBoard(int board_no) {
		// TODO Auto-generated method stub
		return dao.deleteBoard(board_no);
	}

}
