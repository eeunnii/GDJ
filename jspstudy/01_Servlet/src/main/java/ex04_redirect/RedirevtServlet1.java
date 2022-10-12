package ex04_redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RedirevtServlet1")
public class RedirevtServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RedirevtServlet1() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// redirect
		response.sendRedirect("/01_Servlet/RedirectServlet2");  // 이동경로가 새로 잡힌거임
		// html의 a가 request로 들어옴. (요청)  response응답 이용.  a는 없다 봐도 무방
		// a는 최종 목적지인 RedirectServlet2까지 안감. RedirectServlet2에서 파라미터 a출력해보면 null값나옴

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
