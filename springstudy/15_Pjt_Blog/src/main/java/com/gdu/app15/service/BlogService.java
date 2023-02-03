package com.gdu.app15.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app15.domain.BlogDTO;

public interface BlogService {
	
	public void getBlogList(Model moeld);
	public void saveBlog(HttpServletRequest request, HttpServletResponse response);
	// model에다가  request, response  실어서 나를 수 있다~! 그래서 매개변수 모델만 선언해도 됨
	public Map<String, Object> saveSummernoteImage(MultipartHttpServletRequest multipartRequest);
	public int increaseBlogHit(int blogNo);
	public BlogDTO getBlogByNo(int blogNo);
	// 상세보기할 때 편집화면에서 상세보기를 뿌림??
	public void modifyBlog(HttpServletRequest request, HttpServletResponse response);
	public void removeBlog(HttpServletRequest request, HttpServletResponse response);
	
	
	
	
}
