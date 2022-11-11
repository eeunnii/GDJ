package com.gdu.app11.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface EmpService {
	public void findAllEmployees(HttpServletRequest request,Model model);// page라는 파라미터가 필요해서 request를 넣음
	public void findEmployees(HttpServletRequest request,Model model);
	public Map<String, Object> findAutoCompleteList(HttpServletRequest request); // param 이라는 검색어가 들어가 있음
	
	// 보통 서비스가 컨트롤러로 목록을 넘겨주고 서비스에서 model을 저장해서 포워딩했었다.
	// 근데 여기서는 서비스가 컨트롤러로 목록을 넘겨주지 않고 void를 처리함으로써 서비스가 model 처리를 한다. - model.addAttribute()
	// request, response, session, model은 컨트롤러에서 최초선언해야하며,, service에서 받아서 쓰는거임
}
