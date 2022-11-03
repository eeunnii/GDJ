package com.gdu.app05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.service.GalleryService;

@Controller
public class Mycontroller5 {

	@GetMapping("gallery")// 반환값이 없으면 mapping을 반환값으로 인식함 반환이 void인 경우 mapping을 jsp로 인식한다.
	public void gallery() {  // 응답을 직접 만들 때 viod처리함..
		
	}
	
	@Autowired
	private GalleryService galleryService;
	
	
	@ResponseBody
	@GetMapping("image/display")
	public ResponseEntity<byte[]> display(@RequestParam String path, @RequestParam String filename) {
		return galleryService.imagedisplay(path, filename);
		
	}
	
	/*
	@GetMapping("image/display")
	public void display(String path, String filename) {
		//System.out.println(path);
		//System.out.println(filename);
	
	
	}
	
	*/	

	
	
}
