package com.gdu.app14.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app14.service.UploadService;

@Controller
public class UploadController {

	@Autowired
	private UploadService uploadService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/upload/list")
	public String list(Model model) {
		model.addAttribute("uploadlist", uploadService.getUploadList());
		return "upload/list";
	}
	
	@GetMapping("/upload/write")
	public String write() {
		return "upload/write";
	}
	
	@PostMapping("/upload/add")
	public void add(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		uploadService.save(multipartRequest, response);
	}
	
	// 업로드의 fk.
	// 게시글 번호가 들어갈 때 서비스는 알 수 없음
	// 서비스는 시퀀스가 얼마나 들어갔는지 알 수 없음
	// 게시글 pk를 서비스로 넘겨주는 방법 배울거임
	
	@GetMapping("/upload/detail")
	public String detail(@RequestParam(value="uploadNo", required=false, defaultValue = "0") int uploadNo, Model model) {
		uploadService.getUploadByNo(uploadNo, model);
		return "upload/detail";	
	}
	
	@ResponseBody
	@GetMapping("/upload/download")
	public ResponseEntity<org.springframework.core.io.Resource> download(@RequestHeader("User-Agent") String userAgent, int attachNo) { // @RequestHeader 요청헤더를 뒤지는 아이
																										// int attachNo @RequestHeader 생략가능
																										// @RequestHeader int attachNo 도 가능
		return uploadService.download(userAgent, attachNo);
	}
	
	
	@GetMapping("/upload/attach/remove")
	public String attach10(@RequestHeader("uploadNo") int uploadNo ,@RequestHeader("attachNo") int attachNo) {
		uploadService.removeAttachByAttachNo(attachNo);
		return "redirect:/upload/detail";
	}
	
}
