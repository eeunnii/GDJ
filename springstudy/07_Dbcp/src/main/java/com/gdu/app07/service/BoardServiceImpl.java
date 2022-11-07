package com.gdu.app07.service;

import java.util.List;

import com.gdu.app07.domain.BoardDTO;
import com.gdu.app07.repository.BoardDAO;


	public class BoardServiceImpl implements BoardService {
		
	//Service DAO를 사용합니다. 
		//@Autowired 없이!
	//	AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BoardAppContext.class);
		// ▼ 여기다가 선언해두면 오류남 .  아래의 각 메소드 안에 넣어야 실행됨 . 
	//	private BoardDAO dao = ctx.getBean("dao", BoardDAO.class);  // dao : 메소드명 
		
		
		//@Autowired // 컨테이너에 생성된 bean중에서 BoardDAO 타입의 bean을 가져오시오~~~~!!!!
		private BoardDAO dao;
		
		// 생성자의 매개변수 BoardDao dao로 new BoardDAO()가 주입되고 있다.
		// BoardAppContext.java 를 참고

		public BoardServiceImpl(BoardDAO dao) {
			super();
			this.dao = dao;
		}
		
		
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
