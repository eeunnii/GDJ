package com.gdu.app05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app05.service.MovieService;

@Controller
public class MyController3 {
	
	// 컨트롤러가 복잡해지면 프로그램이 무거워짐.. 왜냐면 컨트롤러가 자주 호출되니까 
	// 컨트롤러는 심플하게 
   
   @GetMapping("movie")
   public String movie() {
      return "movie";
   }
   
   @Autowired  // Container(root-context.xml)에 저장된 bean을 가져오시오
   private MovieService movieService;
   

   
   @GetMapping("moive/boxOfficeList")
   public String boxOfficeList(@RequestParam String targetDt) {
	   return movieService.getBoxOffice(targetDt);
   }
   

}
