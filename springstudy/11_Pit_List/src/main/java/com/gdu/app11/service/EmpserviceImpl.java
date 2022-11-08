package com.gdu.app11.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app11.domain.EmpDTO;
import com.gdu.app11.mapper.EmpMapper;


@Service
public class EmpserviceImpl implements EmpService {
	
	@Autowired
	private EmpMapper empMapper;
	
	@Override 
	public void findAllEmployees(HttpServletRequest request, Model model) {  // request --> 파라미터 page를 받아옴, model-->리스트를 받아옴
		
		// request에서 page 파라미터 꺼내기 
		// page 파라미터는 전달이 안될 때는 page=1로 처리한다
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));   
		
		
		
		// 페이지별로 10개씩 뽑기 --  rownum기준으로 뽑는다 . 그냥 사원번호로 하면 나중에 사원이 퇴사했을 때 곤란한 상황발생함
		
		// 한 페이지에 몇 개의 목록을 표시할 것인가 ? -- 전체페이지수 
		int totalRecord = empMapper.selectAllEmployeesCount();
		
		int recordPerPage = 10;
		int begin = (page-1) * recordPerPage + 1;
		int end = begin+recordPerPage-1;
		if(end> totalRecord) {
			end = totalRecord;
		}else {
			end = totalRecord;
		}
		
		List<EmpDTO> employees = empMapper.selectEmployeesByPage(begin, end);
		System.out.println(employees);
		
		model.addAttribute("employees", employees); 
		
		
		
		
		
		
		
	}
}
