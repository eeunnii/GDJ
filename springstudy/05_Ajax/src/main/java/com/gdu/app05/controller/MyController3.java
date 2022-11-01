package com.gdu.app05.controller;

import java.awt.PageAttributes.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.service.MovieService;

@Controller
public class MyController3 {
   
   @GetMapping("movie")
   public String movie() {
      return "movie";
   }
   
   @Autowired
   private MovieService movieService;
   
   @ResponseBody
   @GetMapping(value="movie/boxOfficeList"
		   ,produces="application/josn; charset=UTF-8")
   public String boxOfficeList(String targerDt) {
	   return movieService.getBoxOffice(targerDt);
   }
   

}
