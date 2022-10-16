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


@WebServlet("/BindingServlet1")
public class BindingSerclet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BindingSerclet1() {
        super();
    }

    	//request 데이터를 저장하는 용도로 사용가능
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// stateless ★
		// 1. 상태 없음
		// 2. 웹 페이지 간 이동을 stateless로 이동
		// 3. 현재 페이지는 이전 페이지의 정보가 전혀 없음
		// 약관페이지에서 새 페이지로 넘어갈 때 - 새 페이지에서 어떤걸 동의했는지 머름. 
		
		// Binding ★
		// 1. 속성(Attribute)에 정보를 저장하고 사용하는 것(일반 자바 변수를 말하는거 아님) -- 어디에 저장하느냐에 따라서 사용가능한 스코프가 달라짐
		// 2. 3개 영역을 사용
		//		1) ServletContext          : 애플리케이션(프로젝트) 종료 전까지 접근해서 사용 가능 // ex)방문자수 // 애플리케이션 종료 : 서비스 종료
		// 									  ServletContext이 jsp에선 application
		// 		2) HttpSesstion            : 브라우저 종료 전까지 접근해서 사용 가능 // ex)로그인
		//      3) HttpServletRequest      : 하나의 요청.응답 사이클 내에서 접근해서 사용 가능
		// 3. 사용 메소드  (request.~ 3개 사용가능)
		// 		1) getAttribute('속성')     : 해당 속성 값 가져오기 (캐스팅이 필요할 수 있음)
		// 		2) setAttribute('속성', 값) : 속성에 값 저장하기 // 값에 int, byte, char, double, String 저장가능 ===> Object 타입으로 저장 ==> 꺼내서 사용할 때 castiong해야됨 
		// 														    setAttribute('속성', 값) : "무슨변수를 만들어서 무슨 값을 쓰겠다"라고 이해하기
		//      3) removeAttribute('속성')  : 해당 속성 삭제하기   
		
		// HttpServlet 모든 서블릿의 부모임
		
		// ServletContext
		ServletContext ctx = getServletContext(); // request.getServletContext();    //HttpServlet부모의 메소드
		ctx.setAttribute("a", 1);
		
		// HttpSession 
		HttpSession session = request.getSession();   // ★ 중요  // 변수명은 session을 주로 씀
		session.setAttribute("b", 2);  // session 데이터 저장 가능
		
		// HttpServletRequest (선언이 되어있음)
		request.setAttribute("c", 3);
		
		// 응답
		/*
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<a href=\"/01_Servlet/BindingServlet2\">이동</a>");  // 이동을 눌러주는 건 새로운 요청 사이클이 생기는거임 기존의 요청에 저장한 C는 유효하지 않음. 그래서 BindingServlet2에서 C 접근이 불가능함
		out.close();
		*/
		
		// 포워드(컨텍스트패스 내부 이동)
		// 1. request를 전달함
		// 2. ※서버 내부 이동※이므로 경로 작성 시 컨텍스트패스는 작성하지 않음
		// request.getRequestDispatcher("/BindingServlet2").forward(request, response);
		// mvc할 때 써서 잊어버리면 안됨
		
		// 리다이렉트
		// 1. request를 전달함
		// 2. 클라이언트 -> 서버로 이동하므로 컨텍스트 패스를 작성해야함
		response.sendRedirect("/01_Servlet/BindingServlet2"); // 페이지 바뀌면 요청응답이 새로 나온다는 거임
		
		// a링크, location은 기본적으로 redirect로 이동하는거임
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
