package com.gdu.app13.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
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

		}
		

	

}
