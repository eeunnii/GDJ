package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDao;

public class ModifyService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터  optional사용 불가. name이 정해져있으면 null이 아닌 빈 문자열로 들어감
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		// DB로 보낸 Board board 생성
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setBoardNo(boardNo);
		
		//DB로 Board board 보내기(수정)
		int result = BoardDao.getInstance().updatdBoard(board);
		
		// 수정 성공, 실패
		PrintWriter out = response.getWriter();
		if(result>0) {
			out.println("<script>");
			out.println("alert('게시글이 수정되었습니다.')");
			out.println("location.href='"+request.getContextPath()+"/board/detail.do?boardNo="+boardNo+"'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('게시글이 실패되었습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		out.close();
		return null; // 컨트롤러로 null을 반환하면 컨트롤러는 리다이렉트&포워드 모두 수행하지 않음
	}

}
