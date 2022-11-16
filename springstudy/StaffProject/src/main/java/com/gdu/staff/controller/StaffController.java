package com.gdu.staff.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.service.StaffService;

@Controller
public class StaffController {
	
	
	@Autowired
	private StaffService staffService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@ResponseBody  // 이컨트롤러가 반환하는건json데이터다. 라는뜻
	@GetMapping(value="/list.json", produces = "application/json; charset=UTF-8")
	public List<StaffDTO> getStaffList(){
		return staffService.getStaffList();
	}
	
	
	 // 3가지 방법(request -- staff객체를 만들어야함)
	 //		       @RequestParam 이용함(request -- staff객체를 만들어야함)
	 // 		   아예 StaffDTO로 받음
	
		// 컨트롤러는 최대한 심플하게 구현한다!!
	
	@ResponseBody
	@PostMapping(value="/add", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> add(@RequestParam(value="sno") String sno  
									,@RequestParam(value="name", required=false) String name   // required 디폴트값 true
									,@RequestParam(value="dept") String dept ){   // T자리에 String이라고 해주면 스트링 반환함. 에이작스 전용 애너테이션
		StaffDTO staff = new StaffDTO(sno, name, dept, 0);
		return staffService.addStaff(staff);
	}
	
	@ResponseBody
	@GetMapping(value="/query.json", produces="application/json; charset=UTF-8")
	public StaffDTO add(@RequestParam(value="query") int query){   // T자리에 String이라고 해주면 스트링 반환함. 에이작스 전용 애너테이션
		return staffService.reserchresult(query);
	}

}
