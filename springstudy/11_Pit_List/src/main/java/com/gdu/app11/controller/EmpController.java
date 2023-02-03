package com.gdu.app11.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		empService.findEmployees(request, model);
		return "employee/list";
	}
	
	@ResponseBody  // ajax 처리할 때 이거 붙여줘야함
	@GetMapping(value="/emp/autoComplete", produces="application/json") // 응답데이터가 map으로 되어있는데 json으로 알아서 바뀜 -- jackson에 의해서
	public Map<String, Object> autoComplete(HttpServletRequest request) {
		return empService.findAutoCompleteList(request);
	}

}
