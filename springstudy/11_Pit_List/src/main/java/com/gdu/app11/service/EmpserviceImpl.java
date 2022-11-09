package com.gdu.app11.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app11.domain.EmpDTO;
import com.gdu.app11.mapper.EmpMapper;
import com.gdu.app11.util.PageUtil;


@Service
public class EmpserviceImpl implements EmpService {
	
	@Autowired
	private EmpMapper empMapper;
	
	@Autowired
	private PageUtil pageUtil;
	
	@Override 
	public void findAllEmployees(HttpServletRequest request, Model model) {  // request --> 파라미터 page를 받아옴, model-->리스트를 받아옴
		
		// request에서 page 파라미터 꺼내기 
		// page 파라미터는 전달이 안될 때는 page=1로 처리한다
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));   
		
		// 전체 레코드(직원) 개수 구하기 
		int totalRecord = empMapper.selectAllEmployeesCount();
		
		// PageUtil 계산하기
		pageUtil.setPageUtil(page, totalRecord);
		
		// Map 만들기(begin, end)
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		// 페이지별로 10개씩 뽑기 --  rownum기준으로 뽑는다 . 그냥 사원번호로 하면 나중에 사원이 퇴사했을 때 곤란한 상황발생함
		pageUtil.setPageUtil(page,  totalRecord);

		
		// begin ~ end 목록 가져오기 
		List<EmpDTO> employees = empMapper.selectEmployeesByPage(map);		

		
		model.addAttribute("employees", employees); 
		model.addAttribute("pageUtil", pageUtil); 
		
		
		
		
		
		
		
	}
}
