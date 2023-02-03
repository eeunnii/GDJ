package home;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Weekend02")


public class Weekend02 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Cookie[] cookies=request.getCookies();
		
		if(cookies != null) {
			for(Cookie c : cookies) {
				out.println("<h1>쿠키이름:"+c.getName()+"</h1>");
				out.println("<h1>쿠키값:"+URLDecoder.decode(c.getValue(),"UTF-8")+"</h1>");
				
			}
		}
		
		out.println("<a href=\"/01_Servlet/CookiesServlet3\">이동</a>");
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}