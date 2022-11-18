package com.gdu.app13.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app13.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// post 요청으로 처리하면 url에 post로 된 매핑 입력해도 처리가 안됨.
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/user/agree")
	public String agree() {
		return "user/agree";
	}
	
	@GetMapping("/user/join/write")
	public String joinwirte(@RequestParam(required = false) String location
							,@RequestParam(required = false) String promotion 
							, Model model) {
		model.addAttribute("location", location);
		model.addAttribute("promotion", promotion);  // 스프링오면서 model로 바뀜
		return "user/join";
	}

	@ResponseBody
	@GetMapping(value="/user/checkReduceId", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> checkReduceId(String id){
		return userService.isReduceId(id);
	}
	
	@ResponseBody
	@GetMapping(value="/user/checkReduceEmail", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> checkReduceEmail(String email){
		return userService.isReduceEmail(email);
	}
	
	@ResponseBody
	@GetMapping(value="/user/sendAuthCode", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> sendAuthCode(String email){
		return userService.sendAuthCode(email);
	}
	
	@PostMapping("/user/join")
	public void join(HttpServletRequest request, HttpServletResponse response) {
		userService.join(request, response);
	}
	
	@GetMapping("/user/retire") // 자바매핑에 _붙이는거 안좋은데 훼손없이(?)가기위해
	public void retire(HttpServletRequest request, HttpServletResponse response) {
		userService.retire(request, response);
	}
	
	@GetMapping("user/login/form")
	public String loginFrom(HttpServletRequest request, Model model) {
		//요청헤더 referer 이전 페이지가 저장
		model.addAttribute("url", request.getHeader("referer")); // 로그인 후 되돌아 갈 주소 url
		
		// 네이버 로그인
		model.addAttribute("apiURL",userService.getNaverLoginApiURL(request));
		
		
		return "user/login";
	}
	
	@PostMapping("user/login")  // 왜  post일까
	public void login(HttpServletRequest request, HttpServletResponse response) {
		userService.login(request,response);
	}
	
	
	// 네이버 화면에서 네이버로그인클릭
	/*
	(response_type, client_id, redirect_urk, state 전송)
	->
	네이버로그인 동의화면(제공 정보 선택)
	이전 화면에서 보낸 redirect_uri 주소로 code, state 값을 전송
	redirect_urk가 /user/naver/login이므로
	관련 매핑을 컨트롤러에 만들고, code, state 처리하는 서비스 구현 
	
	callback url
	*/
	
	@GetMapping("/user/naver/login")
	public void naverLogin(HttpServletRequest request) {
		userService.getNaverLoginTokenNProfile(request);
	}
	
	@GetMapping("user/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		userService.logout(request, response);
		return "redirect:/";
	}
	
	/*
	@GetMapping("user/logout")    
	public String logout(HttpSession session) {  -- session여기서 선언가능함
		session.invalidate();  // 로그아웃은 이게 다여
		return "redirect:/";
	}
	*/
	@GetMapping("/user/check/form")
	public String requiredLogin_checkFrom() {
		return "user/check";
	}
	
	@ResponseBody
	@PostMapping(value="/user/check/pw", produces="application/json")
	public Map<String, Object> requiredLogin_checkPw(HttpServletRequest request) {
		return userService.confirmPassWord(request);
	}
	
	@GetMapping("/user/mypage")
	public String requiredLogin_mypage(){ // requiredLogin_ : 로그인되어있어야할 수 있는 동작
		return "user/mypage";
	}
	
	@PostMapping("/user/modify/pw")
	public void requiredLogin_modifyPw(HttpServletRequest request, HttpServletResponse response) {
		userService.modifyPassword(request, response);
	}
	
	@GetMapping("/user/sleep/dsiplay")
	public String sleepDisplay() {
		return "user/sleep";
	}
	
	@PostMapping("/user/restore")
	public void restore(HttpServletRequest request, HttpServletResponse response) {
		userService.restore(request, response);
	}
	
	
	

	
}
