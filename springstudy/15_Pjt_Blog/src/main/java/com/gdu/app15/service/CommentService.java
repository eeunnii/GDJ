package com.gdu.app15.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface CommentService {
	
	public Map<String, Object> getCommentCountCount(int blogNo);
	public Map<String, Object> getCommentCount(int commnet);
	public Map<String, Object> getCommentList(HttpServletRequest request);
	

}
