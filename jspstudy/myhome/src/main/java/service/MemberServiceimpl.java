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
		// TODO Auto-generated method stub

	}

	@Override
	public void cancel(HttpServletRequest requset, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

}
