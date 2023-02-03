package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public interface MemberService {
	public ActionForward login(HttpServletRequest requset, HttpServletResponse response);
	public ActionForward logout(HttpServletRequest requset, HttpServletResponse response);
	public void register(HttpServletRequest requset, HttpServletResponse response);
	public void cancel(HttpServletRequest requset, HttpServletResponse response);
	//아이디 중복 체크 
}
