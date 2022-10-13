package ex08_binding;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/BindingServlet2")
public class BindingServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	application.setAttribute
	//request.setAttribute
       

    public BindingServlet2() {
        super();
 
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ServletContext 
		ServletContext ctx = getServletContext();
		int a = (int)ctx.getAttribute("a");
		
		// HttpSession
		HttpSession session = request.getSession();
		int b = (int)session.getAttribute("b");
		
		// HttpServletRequest
		Object c = request.getAttribute("c"); // int로 했을 때 nullpointexception나오는데 object로 수정하니까 됨
		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h1>a="+a+", b="+b+" , c="+c+"</h1>");
		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
