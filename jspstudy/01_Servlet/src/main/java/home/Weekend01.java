package home;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Weekend01")


public class Weekend01 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie cookie1 = new Cookie("name", "정은지");
		Cookie cookie2 = new Cookie("adress", URLEncoder.encode("경기도 금천구","UFT-8"));
		
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
		response.sendRedirect("/01_Servlet/Weekend02");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}