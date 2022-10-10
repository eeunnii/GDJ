package ex03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AnchorServlet")
public class AnchorSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AnchorSevlet() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		// 요청
		request.setCharacterEncoding("UTF-8"); // 한글의 유무와 상관없이 그냥 넣어주기 
		
		// 요청 파라미터 a,b
		int a=0, b=0;   // int 자료형은 null로 초기화 불가능, 0으로 초기화해야함
		String strA=request.getParameter("a");
		if(strA != null) {
			a = Integer.parseInt(strA);
		}
		String strB = request.getParameter("b");
		if(strB != null) {
			b= Integer.parseInt(strB);
		}
		
		
		
		
		//응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter(); // PrintWriter밖에 반환안해서 이것만 씀
		out.println("<h1>Hello World</h1>");
		out.println("<h1>"+a+"+"+"b"+"="+(a+b)+"</h1>");
		out.flush();
		out.close();
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
