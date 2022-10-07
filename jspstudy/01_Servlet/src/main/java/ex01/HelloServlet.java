package ex01;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Servlet
 * 
 * 1. 웹 화면을 만드는 자바 클래스 
 * 2. JSP/Servlet Container(Tomcat)에 의해서 실행
 * 3. 서블릿을 실행하면 웹 브라우저가 실행됨
 * 4. URL
 * 		★★★★
 * 		프로토콜://호스트:포트번호/컨텍스트패스/URLMapping
 * 		http://localhost:9090/01_Servlet/HelloServlet
 * 		
 * 		주소창에 어떤 서블릿이 실행됏는지 나옴 
 *      2개를 열었는데 컨텍스트패스가 같으면 같은 프로젝트에 있다는 뜻
 *      URLMapping은 서블릿이라고 생각하기. 
 *      서로 다른 서블릿이 동일한 맵핑값을 가질 수 없음. 
 *      컨테이너 안에 a라는 매핑 2개 있음 실행이 안됨
 *      URLMapping = 서블릿 네임
 *      
 *      서블릿은 새로고침이 안됨. 크롭에서 새로고침하지말고 재시작해야됨. 
 */
@WebServlet("/HelloServlet") //  URL Mapping
public class HelloServlet extends HttpServlet { // Servlet은 HttpServlet을 상속 받으면 된다.
       
    /*
     * 1. 생성자
     * 			1)가장 먼저 호출
     * 			2) 생성자 호출 뒤 init()메소드가 호출됨 - 없으면 생략
     */
    public HelloServlet() {
        super();
        System.out.println("생성자");
    }

	/*
	 * 2. 초기화
	 * 	1)각종 초기화 정보 실행
	 *  2) 서블릿 환경 설정 처리
	 *  3) init() (메소드 호출 뒤 service() 메소드 호출)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//원하면 여기서 config.를 열어서 하고싶은거할수잇다 
		System.out.println("init");
	}

	/*   
		3. service - 클라이언트 요청을 직접 ㅐㅔ걀히ㅣ니 두 포스트잇 사용해서설명 몉여줌,
		메소드		post면 dopost열고, 성곳이면 get여ㄹ기
			1) 클라이언트 요청 마다 매번 호출
			2) Get 방식의 요청은 doGet() 메소드 호출, Post 방식의 요청은 doPost() 메소드 호출  // 앞으론 do
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("service");
		
		// service 메소드가 존재하는 경우 doGET()/doPost()가 자동으로 호출되지 않기 때문에 
		// 코드를 직접 작성
		
		// HttpServletRequest request 사용자가 요청한 요청 관련정보는 여기 안에잇음 
		// HttpServletResponse response - 어떤사람한테 응답해줘야하는지에 대한 정보 들어잇음
		// service가 처리하지 않고 doget, dopost 메소드로 넘김 // 코드 직접 작성
		
		//요청 메소드 확인 : request.getMethod() - get과 포스트. get이라고 되어잇으면 doget열고~post면똑같ㅇㅁ
		switch(request.getMethod()) {
		case "GET" : 
			doGet(request, response); // doGet() 메소드 호출
			break;
		case "POST" :
			doPost(request, response); // doPost() 메소드 호출
		}
		
		
	}

	/*
	 * 4. doGet 
	 * 		1) GET 방식의 요청을 처리
	 * 		2) 요청 메소드가 GET인 경우
	 * 				(1) $.ajax({
	 * 							'type' : 'GET'
	 *					 		'url' : 'http://localhost:9090/01_Servlet/HelloServlet'
	 * 					});
	 * 				(2) <a href="http://localhost:9090/01_Servlet/HelloServlet">링크</a>
	 * 				(3) <form method="GET" action="http://localhost:9090/01_Servlet/HelloServlet"> 또는 <form>
	 * 				(4) location.href='http://localhost:9090/01_Servlet/HelloServlet'
	 * 					open('http://localhost:9090/01_Servlet/HelloServlet')
	 * 
	 * 					주소창을 이용한 이동방법은 GET 방식임
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response : 서버가 사용자에게 전달할 때 사용
		//request : 사용자가 서버한테
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//getWriter<-PrintWriter<-문자출력스트림
	}

	/*	
	 * 5. doPost
	 * 		1) POST 방식의 요청을 처리
	 * 		2)요청 메소드가 POST인 경우 
	 * 			(1)($.ajax({
	 * 				'type' : 'POST',
	 * 				'url' : 'http://localhost:9090/01_Servlet/HelloServlet'
	 * 				});
	 * 			(2) <form method="POST" action="http://localhost:9090/01_Servlet/HelloServlet">
 	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		doGet(request, response); // doGet() 메소드 호출 -- 일은 doGet가 함 -- 코드가 2개면 수정할때불편해서 일하는 애 한명으로 몰기
		// 요청과 응답 정보를 doGet() 메소드로 넘겨 버림
		//현재는 doget이 일을 다 하고 있는데 메소드를 하나 추가해서 일하는애 만들 수 잇음 보통 doHandle()이라고함
		
		
		//톰캣 켜져있으면 주소창에 직 접 타이핑해서 실행하는거 가능
	}

}
