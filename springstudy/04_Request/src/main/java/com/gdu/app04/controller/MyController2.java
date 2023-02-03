package com.gdu.app04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app04.domain.Board;


@RequestMapping("board") // URL Mapping이 board로 시작하는 모든 요청을 처리하는 컨트롤러
@Controller
public class MyController2 {
	
	/*
	 * 까먹으면 죽음뿐..
	 * 
	 *  1. forward. --  
	 *  return "board/detail";
	 *  board폴더 아래 detail.jsp로 forward하시오.
	 *  
	 *  2. redirect
	 *  return "redirect:/board/detail";
	 *  URL Mapping값이 /board/detail 인 새로운 요청으로 redirect하시오!!
	 *
	 *
	 *	포워드는 jsp로 하고 redirect는 url mapping으로 함
	 */
	
	
	/* 
	 * 속성(Attribute) 전달 방식
	 * 
	 * 구분				forward				redirect
	 * ---------------------------------------------
	 * 인터페이스		Model				RedirectAttributes
	 * 메소드			addAttribute()		addFlashAttribute()
	 * 										addAttribute처럼 동작하므로 사용하지 말 것
	 */
	
	
	// <a href="${contextPath}/board/detail1?title=공지사항&hit=10">전송</a>
	@GetMapping("detail1")
	public String detail1(HttpServletRequest request) {				// 부득이하게 하나의 방법만 쓸 수 있는 상황이면 이거 쓰는게 조음(?) 2번 3번은 파라미터 통일이 안되서 쓰기 불편한 기능들이 있음
		String title = request.getParameter("title");
		String hit = request.getParameter("hit");
		
		request.setAttribute("title", request.getParameter("title"));
		request.setAttribute("hit", request.getParameter("hit"));
		
		
		
		
		return "redirect:/board/detail2"+title+"&hit="+hit; // 새로운 요청 /board/detail2?title=공지사항&hit=10 처리하시오
		
		
		// /board/detail2 이건.. 새로운 요청임 즉 ! 이건 url mapping값이다
		// 리다이렉트는 요청이 안감 그래서 파라미터를 붙여줘야함
		// 새로운 요청이라 type상관없음 == hit 스트링이엿는데 int로 감 
	}
	
	// redirect:/board/detail2 에서 detail2가 ▼ 로 감
	
	@GetMapping("detail2")										
	public String detail2(String title, int hit,Model model) {   // 애너테이션(리퀘스트.파람) 생략하고 변수로 요청받은거임  @리퀘스트파람이라는 애너테이션 이용
		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		return "board/detail";
	}
	
	
	// <a href="${contextPath}/board/detail3?title=공지사항&hit=10">전송</a>
	@GetMapping("detail3")
	public String detail3(Board board 	//객체에 settitle, sethit이 있으면 객체 받을 수 있음  
						,RedirectAttributes redirectAttributes) {	// RedirectAttributes(인터페이스) :Redirect할 때 속성을 보내주겠다는 뜻
			
		redirectAttributes.addFlashAttribute("board",board);
		
		return "redirect:/board/detail4"; // 새로운 요청에 파라미터를 추가하지 않았음.
		
	}
	
	@GetMapping("detail4")
	public String detail4() {
		return "board.detail";    //@GetMapping("detail3")에서 전달받은 속성을 사용할 수 있음. 
	}
	
}
