package ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FormServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//요청 
			request.setCharacterEncoding("UTF-8");
			// 변수(파라미터)
			String id = request.getParameter("id");
			//if(id == "" ) //자바스크립트에서 ㄱㅊ은 코드. 근데 여긴 자바임 id.equls를써야함
			
			if(id==null||id.isEmpty()) {                //isEmpty버전이 1.6임. isBlank는 11이라 쓸려면 멀엇음 // 혹시 모르니까 둘다 체크
				id = "빈 아이디";
			}
			
			// id==null||id.isEmpty() 와 id.isEmpty()||id==null 는 다름
			// id가 널이 가능해서 널이 왓다고 치면 id.isEmpty()||id==null은 nullpointexception 뜸,
			// null.isEmpty()와 같기 때문임
			// id.isEmpty()||id==null에서 null값이 와도 id.isEmpty() 에서 true 나오고 아래 코드 실행됨.
			// 결론 : null 체크 먼저 해줘야함
		
			
			String pw = request.getParameter("pw");
			
			if(pw.isEmpty()) {
				pw = "빈 비밀번호";
			}
			
			String xy = request.getParameter("gender");
			// 체크박스와 라디오의 특징 : 아무것도 체크 안하고 보내면 null 뜸
			if(xy == null) {
				xy = "빈 성별";
			}
			
			String city = request.getParameter("city");
			if(city == null) {
				city = "빈 도시";
			}
			
			//배열(파라미터) -- 3개 다 빈문자열
			String[] phone = request.getParameterValues("phone");
			if(phone[0].isEmpty()) {
				phone[0]="빈 전화1";
			}
			if(phone[1].isEmpty()) {
				phone[1]="빈 전화2";
			}
			if(phone[2].isEmpty()) {
				phone[2]="빈 전화3";
			}
			
			String strPhone = phone[0] + "-" + phone[1] +"-"+phone[2];
			
			
			
			String[] agree = request.getParameterValues("agree");
			if(agree==null) {
				agree= new String[1];
				agree[0]="빈 동의";
			}
			
			
			//연습(이메일) -- 3칸 다 비어있는 문자열 
			String email_id = request.getParameter("email_id");
			String domain = request.getParameter("domain");
					
			
			
			//응답
			response.setContentType("text/html; charset=UTF-8") ;  //charset=UTF-8나중에 못 바꿈, 인코딩의 기본임.
			PrintWriter out = response.getWriter();
			out.println("<h3>아이디   :" + id + "</h3>");
			out.println("<h3>비밀번호 :" + pw + "</h3>");
			out.println("<h3>성별     :" + xy + "</h3>");
			out.println("<h3>도시     :" + city + "</h3>");
			out.println("<h3>연락처     :" + strPhone + "</h3>");
			out.print("<h3>동의여부     :" + Arrays.toString(agree) + "</h3>");   
			// Arrays.toString(agree) 자체가 null 이면 Arrays.toString(agree) 여기서 nullpointexception떨어짐
			out.print("<h3>이메일     :" +email_id+"@"+ domain + "</h3>");
			List<String> list = Arrays.asList(agree);
			if(list.contains("3")) {
				out.print("<h3>마케팅 동의한 회원</h3>");
			}
			out.close();
	}
	
//	null 택배가 안온거, 빈문자열은 택배가 왓는데 내용물이 없는거. form 전송은 데이터가 가는데 속이 비어있음


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
