package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class SquareService implements MyService {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		//SquareService가 하는 일(넓이*높이)
		
		double width = Double.parseDouble(request.getParameter("width"));
		double height = Double.parseDouble(request.getParameter("height"));
		
		request.setAttribute("width", width);
		request.setAttribute("height", height);
		request.setAttribute("area", height*height);
		request.setAttribute("shape", "Square");
		
		ActionForward actionForward = new ActionForward();
		actionForward.setView("result.jsp");
		actionForward.setRedirect(false); // 포워드
		
		return actionForward;
		
		
	}
}
