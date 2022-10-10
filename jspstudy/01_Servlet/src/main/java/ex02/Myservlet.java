package ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






@WebServlet("/My")   //이거 수정하면 url mapping 값 바귐  //@WebServlet({"/my","/me"})처럼 2개 이상의 URL Mapping 지정가능

public class Myservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Myservlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청
		// 		1) 클라이언트 -> 서버로 보내는 요청 또는 데이터
		// 		2) HttpServletRequest request 객체가 처리(Tomcat이 있어야 사용 가능)
		
		// 1) 요청에 포함된 한글처리(문자셋 : UTF-8)
		request.setCharacterEncoding("UTF-8"); // 소문자대문자 상관없음 // 나중에 필터 사용해서 추가해줄거임
		
		// 2) 요청 파라미터(Parameter) 확인
		//   (1)URL?파라미터=값&파라미터=값
		//   (2)모든 파라미터는 String 타입이다(숫자로 보내도 문자로 인식)
		String name = request.getParameter("name");
		String strAge = request.getParameter("age"); // 파라미터이름은 변수랑 똑같을 필요X
		
		// ? 뒷부분을 완성해주지 않아서 name age는 null값나옴. null값갖는건상관없는데 int age = Integer.parseInt(strAge);여기서 오류남
		// null 값 자체에 값 넣는건 상관없는데 null값이 들어간 변수를 이용할려면 null처리를 해주어야함
		// 만약 strAge가 null일 때 Integer.parseInt(strAge); 여기에 들어가면 오류
		
		
		
		//null처리
		int age =0;
		if(strAge !=null) {
			age = Integer.parseInt(strAge);
		}
		
		
		// 2.응답
		//    1) 서버 -> 클라이언트로 보내는 응답
		//    2) HttpServletResponse response 객체가 처리(Tomcat이 있어야 사용 가능)
		
		// 1) 사용자에게 전달할 데이터의 형식을 HTML 문서로 가정해서 해볼거임  mine-type 문서의 형식을 말하는거임
		// MINE-TYPE을 활용 -- 정해진 키워드처럼 생각하기
		// (1) HTML : text/html
		// (2) CSS  : text/css
		// (3) JS   : text/javascript
		// (4) XML  : application/xml
		// (5) JSON : application/json
		
		response.setContentType("text/html");
		
		// 2) 응답에 포함되는 한글 처리
		response.setCharacterEncoding("UTF-8");
		
		// 1) + 2) MINE-TYPE + 문자셋
		response.setContentType("text/html; charset=UTF-8");
		
		// 3) 응답 스트림 생성
		//	(1) 문자 출력 스트림(*Writer)
		//  (2) response 객체로부터 PrintWriter 객체를 얻을 수 있음
		// 		--IOException 예외 처리가 필요한데 이미 처리되어있음 
		//      --write() 메소드보다는 print()/println()메소드를 사용하는 것을 권장
		PrintWriter out = response.getWriter();   // 예외처리 안해줘도 됨
		
		// (4) 응답 만들기 (HTML 문서 만들기)
		out.println("<html lang=\"ko\">");   // 역슬래쉬는 특수문자 구분을 위해 사용
		out.println("<head>"); 		 // 복사 : ctrl + alt + 방향키
		out.println("<meta charset=\"UTF-8\">"); 
		out.println("<title>"); 
		out.println("<나의 첫 번째 응답>"); 
		out.println("</title>"); 
		out.println("<body>"); 
		out.println("<h1>안녕하세요."+name+"님 반갑습니다.</h1>"); 
		if(age >= 20) {
			out.println("<h1>귀하는 "+age+"살 이므로 입장이 가능합니다.</h1>"); 
		} else {
			out.println("<h1>"+age+"살? 애들은 다음에</h1>");
		}
		out.println("</body>");
		out.println("</html>");
		
		out.flush(); // 출력 스트림에 남아 있는 모든 데이터 내보내기 (만약을 위해서)
		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
