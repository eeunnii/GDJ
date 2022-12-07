package com.gdu.pakage.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.pakage.domain.MemberDTO;

@Mapper
public interface MemberService {
	
	
	
	public Map<String, Object> register(MemberDTO member, HttpServletResponse response);
}
