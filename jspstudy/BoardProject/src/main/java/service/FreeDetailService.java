package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class FreeDetailService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		
		
		
		
		
		
		// 요청 파라미터 
		Optional<String> otp = Optional.ofNullable(request.getParameter("freeNo"));
		int freeNo =Integer.parseInt(otp.orElse("0"));
		
		
		
		
		

		
		// DB로 boardNo 보내고 해당 Board 받아오기
		Free free = FreeDAO.getInstance().selectFreeByNo(freeNo);
		
		
		
		
		// request에 Board board 저장하기
		request.setAttribute("free", free);
		
		// detail.jsp로 포워딩
		return new ActionForward("/free/detail.jsp",false);		
	}

}
