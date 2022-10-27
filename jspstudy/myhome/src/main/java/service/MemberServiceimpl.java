package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ActionForward;
import domain.Member;
import repository.MemberDao;

public class MemberServiceimpl implements MemberService {

	@Override
	public ActionForward login(HttpServletRequest requset, HttpServletResponse response) {
		 // System.out.println("로그인"); 이거 안나옴
		
		String id = requset.getParameter("id");
		String pw = requset.getParameter("pw");
		
		Member member = Member.builder()
				.id(id)
				.pw(pw)
				.build();
			
				
		Member login = MemberDao.getInstance().login(member);
		if(login!=null) {
			HttpSession session = requset.getSession();
			session.setAttribute("login", login);
			return new ActionForward(requset.getContextPath(), true);
			

		}else {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 실패');"); // 메세지 만들었으면 이동해야함
				out.println("history.back()");
				out.println("<script>");   // response
				out.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
		

	}

	@Override
	public ActionForward logout(HttpServletRequest requset, HttpServletResponse response) {
		HttpSession session = requset.getSession();
		session.invalidate();
		return new ActionForward(requset.getContextPath(), true);
	

	}

	@Override
	public void register(HttpServletRequest requset, HttpServletResponse response) {
		String id = requset.getParameter("id");
		String pw = requset.getParameter("pw");
		String name = requset.getParameter("name");
		String email = requset.getParameter("email");
		
		Member member = Member.builder()
							.id(id)
							.pw(pw)
							.name(name)
							.email(email)
							.build();
		
		int result = MemberDao.getInstance().insertMember(member);
		
		try {
			PrintWriter out = response.getWriter();
			out.print("<script>");
			if(result>0) {
				// 회원가입하면 로그인 처리해줄거임
				// 회원가입한 회원의 정보를 DB에서 가져온 뒤 session에 login이라는 이름으로 올리면 됨
				HttpSession session = requset.getSession();
				session.setAttribute("login", MemberDao.getInstance().login(member)); 
								//MemberDao.getInstance().login(member) 대신 member로 사용하면 위에있는 memeber사용하면 일부 칼럼이 빠짐. 가입일.
				out.print("alert('환영합니다');");
				out.print("location.href = '" + requset.getContextPath()+"';");
				
		}else {
			out.println("alert('회원 가입에 실패했습니다.');");
			out.println("history.back();");
		}
			out.println("</script>");
			out.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	
	
	
	
	@Override
	public void cancel(HttpServletRequest requset, HttpServletResponse response) {
		// session은 지워지지 않는 정보, session에 아이디, 비밀번호 저장되어있음. 
		// 창을 닫거나 30분이 지나거나 로그아웃을 하면 session이 없어짐
		// 고로 requset가 필요없다. 그럼 애초에 요청넘길 때 파라미터가 필요없음
		
		// session에 저장된 login 정보에서 탈퇴할 회원의 정보를 추출
		HttpSession session = requset.getSession();
		Member login = (Member)session.getAttribute("login"); // (Member) member로 캐스팅
		int memberNo = login.getMemberNo();
		
		int result = MemberDao.getInstance().deleteMember(memberNo);
		
		try {
			PrintWriter out = response.getWriter();
			out.print("<script>");
			if(result>0) {
				// 탈퇴 성공하면 session 초기화
				session.invalidate();
				out.print("alert('이용해주셔서감사합니다');");
				out.print("location.href = '" + requset.getContextPath()+"';");
				
		}else {
			out.println("alert('탈퇴에 실패했습니다.');");
			out.println("history.back();");
		}
			out.println("</script>");
			out.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
		
		// 탈퇴하면 탈퇴자테이블에 데이터를 옮겨둠.
		// 정보삭제와 탈퇴를 트랜잭션으로 묶어서 처리하ㄴ기 미래에 
		//삭제한 아이디보관해서 아이디 중복체크

	}


}
