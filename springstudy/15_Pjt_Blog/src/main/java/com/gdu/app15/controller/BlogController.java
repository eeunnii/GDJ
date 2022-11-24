package com.gdu.app15.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app15.service.BlogService;

@Controller
public class BlogController {

	
	@Autowired
	private BlogService blogService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
	@GetMapping("/blog/list")
	public String list(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);  // model에 request를 저장하기
		blogService.getBlogList(model);          // model만 넘기지만 이미 model에는 request가 들어 있음
		return "blog/list";
	}
	
	// 서비스에서 모델에 있는 request 를 꺼내준다
	// 모든 서비스를 모델로 통일 할 수 ㅣㅇㅆ다..
	
	@GetMapping("/blog/write")
	public String write() {
		return "blog/write";
	}
	
	// 요청이 insert라 jsp에서 post로 처리된거임
	@PostMapping("/blog/add")
	public void add(HttpServletRequest request, HttpServletResponse response) {
		blogService.saveBlog(request, response);
	}
	
	// 이미지는 하드디스크에 저장하고, 경로를 적어서 관리하면 DB용량이 확 줄어듦
	// write.jsp를 수정해야함
	
	@ResponseBody
	@PostMapping(value="/blog/uploadImage", produces="application/json")
	public Map<String, Object> uploadImage(MultipartHttpServletRequest multipartRequest){
		//MultipartHttpServletRequest 이거 쓸려면  config클래스 들고 다녀야함
		return blogService.saveSummernoteImage(multipartRequest);
	}
	
	@GetMapping("/blog/increase/hit")
	public String increaseHit(@RequestParam(value="blogNo", required=false, defaultValue="0")int blogNo) {
			int result = blogService.increaseBlogHit(blogNo);
			if(result>0) { // 조회수 증가에 성공하면 상세보기로 이동
				return "redirect:/blog/detail?blogNo="+blogNo;
			}else {
				return "redirect:/blog/list";   // 조회수 증가에 실패하면 목록보기로 이동함
			}
	}
	
	@GetMapping("/blog/detail")
	public String detail(@RequestParam(value="blogNo", required=false, defaultValue="0")int blogNo, Model model) {
		//blogService.getBlogByNo(blogNo, model); 모델에다가 실어두면 코드 재활용이 안됨. 모
		model.addAttribute("blog", blogService.getBlogByNo(blogNo));
		return "blog/detail";
	}
	
	@PostMapping("/blog/edit")
	public String edit(int blogNo, Model model) {
		model.addAttribute("blog", blogService.getBlogByNo(blogNo));
		return "blog/edit";
	}
	
	@PostMapping("/blog/modify")
	public void modify(HttpServletRequest request, HttpServletResponse response) {
		blogService.modifyBlog(request, response);   // 수정 후 상세보기로
	}	
	
	
	
	
	@PostMapping("/blog/remove")
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		blogService.removeBlog(request, response);  // 삭제 후 목록보기로
	}

	
	
}
