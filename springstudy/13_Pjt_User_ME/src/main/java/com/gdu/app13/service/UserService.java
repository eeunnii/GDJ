package com.gdu.app13.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app13.domain.UserDTO;

public interface UserService {
	public Map<String, Object> isReduceId(String id);    //jackson이 반환해줌?
	public Map<String, Object> isReduceEmail(String email);    //jackson이 반환해줌?
	public Map<String, Object> sendAuthCode(String email);    //jackson이 반환해줌?
	public void join(HttpServletRequest request, HttpServletResponse response);
	public void retire(HttpServletRequest request, HttpServletResponse response);
	public void login(HttpServletRequest request, HttpServletResponse response);
	public void keeplogin(HttpServletRequest request, HttpServletResponse response);
	public void logout(HttpServletRequest request, HttpServletResponse response);
	public UserDTO getUserBySessionId(Map<String, Object> map); // KeepLoginInterceptor에서 호출
	public Map<String, Object> confirmPassWord(HttpServletRequest request);
	public int modifyPassword(HttpServletRequest request, HttpServletResponse response);
	
	
}


