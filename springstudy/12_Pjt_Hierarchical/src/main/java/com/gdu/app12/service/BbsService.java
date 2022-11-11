package com.gdu.app12.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface BbsService {
	
	public void findAllBbsList(HttpServletRequest request, Model modle);
	public int addBbs(HttpServletRequest request);
	public int addReply(HttpServletRequest requset);
	public int removeBbs(int bbsNo);
	
	// ip를 알아낼려면 request를 보내야함 

}
