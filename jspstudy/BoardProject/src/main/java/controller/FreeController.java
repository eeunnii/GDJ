package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.FreeAddService;
import service.FreeDetailService;
import service.FreeListService;
import service.FreeModifyService;
import service.FreeRemoveService;
import service.FreeService;


@WebServlet("*.do")
public class FreeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FreeController() {
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		// 요청 / 응답 리코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");   // alert이런것때문에 이거 씀
		
		// 요청 확인
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length()+1);
				
		// StudentService 객체
		FreeService service = null;
		
		// ActionForward 객체
		ActionForward af = null;
		
		switch(urlMapping) {
		case "list.do" :
			service = new FreeListService();
			break;
		case "insert.do" : 
			service = new FreeAddService();
			break;
		case "delete.do" : 
			service = new FreeRemoveService();
			break;
		case "detail.do" :
			service = new FreeDetailService();
			break;
		case "modify.do" :
			service = new FreeModifyService();
			break;
			
			
			
		}
		
		try {
			if(service != null) {
				af = service.execute(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		// 어디로 어떻게 이동하는가?
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getView());
			} else {
				request.getRequestDispatcher(af.getView()).forward(request, response);
			}
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
