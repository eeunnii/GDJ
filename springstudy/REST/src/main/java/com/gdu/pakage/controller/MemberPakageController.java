package com.gdu.pakage.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gdu.pakage.domain.MemberDTO;
import com.gdu.pakage.service.MemberService;


/*
 * REST : REpresentational State Transfer
 * 
 * 1. 자원을 정의하고 자원의 주소를 지정하는 방식에 대한 하나의 형식이다 
 * 2. REST 방식을 따르는 시스템을 "RESTful하다"	라고 표현한다.
 * 3. 동작의 결정을 URL + Method 조합으로 결정한다.
 * 4. 파라미터가 URL에 경로처럼 포함된다. (?를 사용하지 않는다, 슬래쉬로 연결됨)
 * 5. CRUD 처리 방식
 * 			 		URL         Method
 * 		1)  삽입	/members	post
 * 		2)  목록 	/members	GET
 * 		3)  상세 	/members/1  GET
 * 		4)  수정    /members	PUT  			// post 매핑이랑 똑같음. 파라미터 못 붙임. 바디에 포함시킴
 * 		5)  삭제    /members/1  DELETE			// 1 은 콤마로 구분되서 여러개로 갈 예정
 */

@RestController		//이 컨트롤러는 모든 메소드에 @ResponseBody에너테이션을 추가한다
public class MemberPakageController {
	
	
	@Autowired
	private MemberService memberService;

	// 삽입
	@PostMapping(value="/members", produces="application/json")
	public Map<String, Object> addMember(@RequestBody MemberDTO member, HttpServletResponse response) {
		return memberService.register(member, response);
	}
	
	// 목록
	@GetMapping(value="/members/page/{page}", produces="application/json") // 경로에 들어있는 변수는 중괄호를 써서 가져옴
	public Map<String, Object> getmemberList(@PathVariable(value="page", required=false) Optional<String> opt){  // @PathVariable : 경로에 포함된 변수
		int page = Integer.parseInt(opt.orElse("1"));
		return memberService.getMemberList(page);
	}
	
	
	//상세
	@GetMapping(value="/members/{memberNo}", produces="application/json")
	public Map<String, Object> getMember(@PathVariable(value="memberNo", required = false) Optional<String> opt){
		int memberNo = Integer.parseInt(opt.orElse("0"));
      return memberService.getMemberByNo(memberNo);
	}
	
	// 수정
	@PutMapping(value="/members", produces="application/json")
	public Map<String, Object> modifyMember(@RequestBody Map<String, Object> map, HttpServletResponse response ){  // @RequestBody 는 DTO 또는  Map받아옴
		return memberService.modifyMember(map, response);
	}
	
	// 삭제
	@DeleteMapping(value="/members/{memberNoList}", produces = "application/json")
	public Map<String, Object> deleteMemberList(@PathVariable String memberNoList){
		return memberService.removeMemberList(memberNoList);
	}
}
