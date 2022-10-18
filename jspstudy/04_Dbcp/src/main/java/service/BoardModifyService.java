package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDao;

public class BoardModifyService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		// <input type="text">,<textarea> 태그 요소 입력값이 없을 때 빈 문자열("")로 전달되,...깃허브
		// Optional은 사용할 수 없다
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		//DB로 보낼 board 생성
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setBoard_no(board_no);
		
		//DB로 board 보내서 수정
		int result = BoardDao.getInstance().updateBoard(board);
		
		// 수정결과는 콘솔에서 확인(원래 일케 하면 안됨)
		System.out.println("수정 결과 : " + result); // 1나오면 성공
		
		// 어디로 / 어떻게 
		ActionForward af = new ActionForward();
		af.setView(request.getContextPath()+"/board/detail.do?board_no="+board_no); // 리다이렉트할 땐 맵핑으로 이동함 // 상세보기할려면 board_no을 넘겨주어야함. 
																							//BoardDetailService에 보면 알 수 잇음
		af.setRedirect(true);  // UPDATE다음에는 리 다이렉트
		
		
		return af;
	}

}
