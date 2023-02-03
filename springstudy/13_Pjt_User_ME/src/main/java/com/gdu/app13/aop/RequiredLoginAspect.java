package com.gdu.app13.aop;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Component
@Aspect
@EnableAspectJAutoProxy
public class RequiredLoginAspect {
	
		//controller에 정의한 메소드 중 이름에 requeried 들어간 메소드에 실행시킴
		// 인터셉터같은 기능임
	
		// 조인포인트(대상 메소드), 포인트컷(실행시킬 메소드)
	
		// 로그인안된사람이 게시글 작성하러 가면 안되니까 required 붙여주면 좋음 // 로그인해야 게시글 작성가능
		//  그 래 서 UserController   -> *Controller로 수정했음

		@Pointcut("execution(* com.gdu.app13.controller.*Controller.requiredLogin_*(..))")
		public void requiredLogin() { }
		
		@Before("requiredLogin()") // 포인트컷 실행 전에 requiredLogin() 메소드 수행
		public void requiredLoginHandler(JoinPoint joinpoint) throws Throwable {
			// 로그인이 되어 있는지 확인하기 위해서 session이 필요하므로, 
			// request가 필요하다.
			// 응답을 만들기 위해서 response도 필요하다.
			 ServletRequestAttributes servletwebrequest = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
			 HttpServletRequest request = servletwebrequest.getRequest();
			 HttpServletResponse response = servletwebrequest.getResponse();
			 
			 // 세션
			 HttpSession session = request.getSession();
			 
			 // 로그인 여부 확인
			 if(session.getAttribute("loginUser")==null) {
				 response.setContentType("text/html; charset=UTF-8");
				 PrintWriter out = response.getWriter();
				 
				 out.println("<script>");
				 out.println("if(confirm('로그인이 필요한 기능입니다. 로그인 하시겠습니까?)){");
				 out.println("locaation.href='"+request.getContextPath()+"/user/login/form';");
				 out.println("} else {");
				 out.println("history.back();");
				 out.println("}");
				 out.println("</script>");
				 out.close();
				 
			 }
		}
}
