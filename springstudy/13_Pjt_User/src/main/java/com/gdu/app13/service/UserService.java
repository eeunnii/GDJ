package com.gdu.app13.service;

import java.util.Map;

public interface UserService {
	public Map<String, Object> isReduceId(String id);    //jackson이 반환해줌?
	public Map<String, Object> isReduceEmail(String email);    //jackson이 반환해줌?
	public Map<String, Object> sendAuthCode(String email);    //jackson이 반환해줌?
	

}
