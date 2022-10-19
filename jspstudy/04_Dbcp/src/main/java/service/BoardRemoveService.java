package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.ActionForward;
import repository.BoardDao;

public class BoardRemoveService implements BoardService {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터 
		Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
		//int board_no = Integer.parseInt(request.getParameter("board_no")); 위험한 코드 : 널값이 들어올 수 있어서. 그럼 nullponit익셉션뜸
		int board_no = Integer.parseInt(opt.orElse("0"));  // 게시글 번호가 0이라는 뜻. 0인 게시판은 없으니까 삭제 불가능, DAO에서 result=0반환함
		
		// DB로 board_no 보내서 삭제
		int result = BoardDao.getInstance().deleteBoard(board_no);
		
		// 삭제 성공/실패 여부 콘솔에 출력
		System.out.println("삭제 여부"+result);
		
		// 어디로 , 어떻게 
		ActionForward af = new ActionForward();
		af.setView(request.getContextPath()+"/board/list.do");
		af.setRedirect(true);		// delete이후에는 redirect를 해주는게 기본이고 redirect를 할 때는 맵핑으로 이동하는게 기본임 
		return af;
	}
	

}
