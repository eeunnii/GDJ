package com.gdu.app05.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MyController4 {
	
	@GetMapping("index")
	public String index() {
		return "index";
	}
	
	// post 자바...  bin이랑 map으로 받을 수 있음.
	// post방식으로 넘어온 제이슨은 bin이랑 map으로 받아서 처리할 수 있다.
	
	

}
