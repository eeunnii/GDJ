package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDao;

public class BoardDetailService implements BoardService{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		// 요청 파라미터 
		Optional<String> otp = Optional.ofNullable(request.getParameter("boardNo"));
		int boardNo =Integer.parseInt(otp.orElse("0"));
		
		// DB로 boardNo 보내고 해당 Board 받아오기
		Board board = BoardDao.getInstance().selectBoardByNo(boardNo);
		
		// 보드 파라미터로 boardW가 전달되지 않았거나 존재하지 않는 경우 
		// 요청 파라미터로 boardNo가 전달되지 않았거나 존재하지 않는 boardNo가 전달된 경우 목록보기로 이동
		if(board == null) {
			PrintWriter out = response.getWriter();
			out.println("<scrpit>");
			out.println("alert('"+boardNo+"번 게시글의 정보가 없습니다')"); 
			out.println("location.href='" + request.getContextPath() + "/board/list.do'");
			out.println("</script>");
			out.close();
		
		}else {
			// request에 Board board 저장하기
			request.setAttribute("board", board);
			// detail.jsp로 포워딩
			ActionForward af = new ActionForward();
			af.setView("/board/detail.jsp");
			af.setRedirect(false); // boolean 초기값이 false라서 안적어도 되지만 명시적으로 남김
			return af;		
		}
		return null; // Java 문법 때문에 남겨둔 코드(실행되지 않는 코드)
	}
}
