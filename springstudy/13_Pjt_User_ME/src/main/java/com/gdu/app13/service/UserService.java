package com.gdu.app13.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app13.domain.SleepUserDTO;
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
	public void sleepUserHandle(); // SleepUserScheduler에서 호출
	public SleepUserDTO getSleepUserById(String id);
	public void restoreUser(HttpServletRequest request, HttpServletResponse response);
	
	public String getNaverLoginApiURL(HttpServletRequest request);			 // 네이버로그인-1
	public UserDTO getNaverLoginTokenNProfile(HttpServletRequest request);   // 네이버로그인-2
	public UserDTO getNaverLoginProfile(String access_token);                // 네이버로그인-3
	public UserDTO getNaverUserById(String id);
	public void naverLogin(HttpServletRequest request, UserDTO naverUser);
	public void naverJoin(HttpServletRequest request, HttpServletResponse response);
}


