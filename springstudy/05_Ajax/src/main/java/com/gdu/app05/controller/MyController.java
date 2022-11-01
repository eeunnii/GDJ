package com.gdu.app05.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.domain.Member;
import com.gdu.app05.service.MemberService;

@Controller
public class MyController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("member")
	public String member() {
		return "member";		//member.jsp로 포워드한다는 뜻
	}
	
	
	//field
	@Autowired  // Container(root=context.xml)에서 타입(class)이 일치하는 bean을 가져오세요
	
//	<bean id="service" class="com.gdu.app05.service.MemberServiceimpl"></bean> 에서 bean id="service"이랑 memberService랑 이름 꼭 안똑같아도 됨
	
	private MemberService memberService;
	
	
	/* 
	 * @ResponseBody
	 * 안녕, 난 ajax 처리하는 메소드야,
	 * 내가 반환하는 값은 뷰(JSP) 이름이 아니고, 어떤 데이터(text, json, xml등) 야.
	 */
	
	@ResponseBody
	@GetMapping(value ="member/detail1" 
				,produces="text/plain; charset=UTF-8") // produces 뜻 : 이메소드가 무엇을 만드는가?? produces 응답 데이터 타입(MINE-TYPE)	
	public String detail1(HttpServletRequest request,  HttpServletResponse response) {
		String str = memberService.execute1(request,response);
		return str; // 
		//에이작스 통신은 데이터를 주고 받는 것 jsp의이동의어가없음
		// 만약 @ResponseBody가 없고 @GetMapping만 되어있다면 (str에들어가있는데이터).jsp라고 인식
	}
	
	@ResponseBody
	@GetMapping(value="member/detail2"
				,produces = "application/josn; charset=UTF-8")
	public Member detail2(@RequestParam(value="id") String id,@RequestParam(value="pw") String pw) {
		Member member = memberService.execute2(id,pw);
		return member;  // jackson이 member객체를 {"id" : 아이디, "pw" : 패스워드}형식의 JSON으로 바꿔서 전달합니다
		
		//jackson 관련된 코드가 없음!! jackson이 알아서 해줌!!
		
		// 깃허브
	}
	
	@ResponseBody
	@GetMapping(value="member/detail3"
				,produces=MediaType.APPLICATION_JSON_VALUE)  // MediaType 데이터mine타입 불러주는 애  APPLICATION_JSON_UTF8_VALU하면 가운데줄 생김
	public Map<String, Object> detail3(Member member){
		Map<String, Object> map = memberService.execute3(member);
		return map; // return memberService.execute3(member);와 같음
		
	}
	
	
	/*
	 * @RequestBody
	 * 안녕... 난 ... 요청 데이터가 body에 포함되어있다고 알려주는 일을 해..
	 * 요청 파라미터에서는 사용할 수 없고, 
	 * post 방식으로 파라미터 없이 데이터가 전달 될 때 사용할 수 잇음
	 */
	
	@ResponseBody
	@PostMapping(value="member/detail4"
					,produces=MediaType.APPLICATION_JSON_VALUE)			
	public Member detail4(@RequestBody Member member) { // Memeber member 이름없이 와서 이렇게 동작이 안됨?
		
		return member;
		
		//깃허브깃허브깃허브깃허브
	}
	
	
}
