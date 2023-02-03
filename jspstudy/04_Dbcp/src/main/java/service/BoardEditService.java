package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDao;

public class BoardEditService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		// 요청 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));  // 포장하는게 null도 가능할 수도 있어서 nullable들어감. ofNullable(요청파라미터)
		int board_no = Integer.parseInt(opt.orElse("0"));   // null이면 0 반환. 
		
		// DB에서 board_no에 해당하는 Board 가져오기 
		Board board = BoardDao.getInstance().selectBoardByNo(board_no);
		
		// 게시글 정보를 jsp로 보내기 위해서 request에 저자함
		request.setAttribute("board", board);
		
		// 어디로 / 어떻게 
		ActionForward af = new ActionForward();
		af.setView("/board/edit.jsp");
		af.setRedirect(false);
		
		
		return af;
	}

}
