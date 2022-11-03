package com.gdu.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.contact.domain.ContactDTO;

@Controller
public class ContactController {
	
	
	@Autowired
	private ContactDTO contactDTO;
	
	@GetMapping("/")
	public String welcom() {
		return "welcom";
	}
	
	@GetMapping("con/add")
	public int 
	
	

}
