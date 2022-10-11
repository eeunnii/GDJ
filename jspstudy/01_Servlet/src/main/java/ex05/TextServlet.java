package ex05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TextServlet")
public class TextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public TextServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			// 요청
			request.setCharacterEncoding("UTF-8");
			
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age")); // age가 null이거나, int가 아닐시 NumberFormatException이 발생할 수 있는 코드, age에 null이 들어가면 Integer.parseInt(null)
			
			if(0>age || age>100) {
				throw new RuntimeException(age+"살은 불가능한 나이입니다.");
			}
			
			// 응답 
			response.setContentType("text/plain; charset=UTF-8");  // text(단순텍스트)의 mine 타입 : text/plain
			
			PrintWriter out = response.getWriter();
			out.println("이름은"+name+"이고, 나이는"+age+"살입니다");  // html의 resData로 넘어갈 예정
			out.close();
			
		}catch(NumberFormatException e) {
			
			// 예외 처리 응답
			response.setContentType("text/plain; charset=UTF-8");
			
			response.setStatus(1000); //개발자가 임의로 작성한 stauts(응답코드)
			
			PrintWriter out = response.getWriter();
			out.println("예외발생! 파라미터 age는 정수입니다."); // 개발자가 임의로 작성한 responseText
			out.close();
			
		}catch(RuntimeException e) {
			response.setContentType("text/plain; charset=UTF-8");
			
			response.setStatus(2000); //개발자가 임의로 작성한 stauts(응답코드)
			
			PrintWriter out = response.getWriter();
			out.println(e.getMessage()); // RuntimeException 예외 객체에 저장된 예외 메세지를 responseText로 처리
			out.close();
		}
		
		// 실무에선 예외마다 다른 코드를 줌
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
