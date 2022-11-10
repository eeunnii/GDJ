package com.gdu.app12.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app12.service.BbsService;

@Controller
public class BBSController {
	
	
	// 원래 이렇게 해야함
	// 로그인하면 세션에 정보가 올라감. 
	// 로그인 한 사람의 정보와. 게시글의 작성자 정보가 일치하면
	// 삭제버튼이 뜰 수 있게 구현...

	@Autowired
	private BbsService bbsService;
	
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	@GetMapping("/bbs/list")
	public String list(HttpServletRequest request, Model model) {
		bbsService.findAllBbsList(request, model);
		return "bbs/list";
	}
	
	@GetMapping("/bbs/write")
	public String write() {
		return "bbs/write";	
	}
	
	@PostMapping("/bbs/add")
	public String add(HttpServletRequest request) {
		bbsService.addBbs(request);
		return "redirect:/bbs/list";
	}
	
	@PostMapping("/bbs/remove")
	public String remove(@RequestParam("bbsNo") int bbsNo) {
		//System.out.println(bbsNo);
		
		return "redirect:/bbs/list";
	}
	
}
