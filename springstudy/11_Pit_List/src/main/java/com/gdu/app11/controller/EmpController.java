package com.gdu.app11.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.app11.service.EmpService;

@Controller
public class EmpController {
	
	@Autowired
	private EmpService empService;
	
	
	@GetMapping("/")
	public String welcom() {
		return "index";
	}
	
	@GetMapping("emp/list") // 맨 앞의 / 는 있어도 그만 없어도 그만
	public String list(HttpServletRequest request, Model model) {
		empService.findAllEmployees(request, model);
		return "employee/list";
	}
	
	@GetMapping("/emp/search")
	public String search(HttpServletRequest request, Model model) {
		empService.findEmployeesCount(request, model);
		return "employee/list";
	}
	

}
