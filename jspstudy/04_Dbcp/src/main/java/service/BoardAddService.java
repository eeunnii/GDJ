package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDao;

public class BoardAddService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터 
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// DB로 보낼 Board생성
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
				
		// DB로 Board보냄(삽입)
		int result = BoardDao.getInstance().insertBoard(board);  // 0이면 성공 1이면 실패
		
		// 삽입성공. 실패처리는 하지 않음
		System.out.println("삽입 결과 :" + result);
		
		// 어디로 / 어떻게 (INSERT, UPDATE, DELETE 이후에는 Redirect)
		// insert 이후에 forward하면 요청이 2번들어가서 삽입이 2번 이루어짐
		// redirect는 요청이 두 번 들어감. 
		ActionForward af = new ActionForward();
		af.setView(request.getContextPath()+"/board/list.do"); // .jsp : 삽입하고 그냥 가면 정보를 보여주나요? 아니요 DB를 들렸다가 와야함
									  // .jsp : 로 하면 DB를 안들림
		 							  // redirect할 때는 대부분 매핑으로 요청함
		af.setRedirect(true);  // 리다이렉트 . 첨나옴..이해안되는디
	
		return af;
	}

}
