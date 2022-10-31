package com.gdu.app04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("board") // URL Mapping이 board로 시작하는 모든 요청을 처리하는 컨트롤러
@Controller
public class MyController2 {
	
	/*
	 * 까먹으면 죽음뿐..
	 * 
	 * 1. forward. --  
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
	
	
	
	// <a href="${contextPath}/board/detail1?title=공지사항&hit=10">전송</a>
	@GetMapping("detail1")
	public String detail1(HttpServletRequest request) {
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
	public String detail2(String title, int hit,Model model) {
		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		return "board/detail";
	}
	
	
}
