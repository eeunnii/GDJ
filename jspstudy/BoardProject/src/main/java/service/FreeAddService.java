package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class FreeAddService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		System.out.println("도착!");
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String ip = request.getRemoteAddr();
		
		
		
		Free free = new Free();
		free.setWriter(writer);
		free.setTitle(title);
		free.setContent(content);
		free.setIp(ip);
		
		
		System.out.println("객체생성완료!");
		
		
		int result = FreeDAO.getInstance().insertFree(free);
		
		
		
		return new ActionForward("free/insertResult.jsp?result=" + result, true);
	}

}
