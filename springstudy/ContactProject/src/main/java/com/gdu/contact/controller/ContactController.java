package com.gdu.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.contact.domain.ContactDTO;
import com.gdu.contact.service.ContactService;

@Controller
public class ContactController {
	
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/")
	public String welcom() {
		return "redirect:/con/list";
	}
	
	
	@GetMapping("con/list")
	public String list(Model model) {
		model.addAttribute("Contacts",contactService.findAllContacts());
		return "welcom";
	}
	
	
	@GetMapping("con/add")
	public String add(ContactDTO contact) {
		
	return null;
	}
	
	@GetMapping("con/move")
	public String move(ContactDTO contact) {
		
	return "contact/insert";
	}
	

}
