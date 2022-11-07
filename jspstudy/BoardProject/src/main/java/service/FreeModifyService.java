package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class FreeModifyService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		
		
		//요청 파라미터 
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int freeNo = Integer.parseInt(request.getParameter("freeNo"));	
		
		
		Free free = new Free();
		free.setTitle(title);
		free.setContent(content);
		free.setFreeNo((long)freeNo);

		//DB로 board 보내서 수정
		int result = FreeDAO.getInstance().updateFree(free);
		
		System.out.println(result);   // 완ㄹ.
		
		
		
		request.setAttribute("free", FreeDAO.getInstance().selectFreeByNo(freeNo));
		
		
		return new ActionForward("free/modifyResult.jsp?result=" + result+"&freeNo="+freeNo,true);  // ??

	}

}
