package com.gdu.app13.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
	public Map<String, Object> isReduceId(String id);    //jackson이 반환해줌?
	public Map<String, Object> isReduceEmail(String email);    //jackson이 반환해줌?
	public Map<String, Object> sendAuthCode(String email);    //jackson이 반환해줌?
	public void join(HttpServletRequest request, HttpServletResponse response);
	public void retire(HttpServletRequest request, HttpServletResponse response);
	public void login(HttpServletRequest request, HttpServletResponse response);
}
