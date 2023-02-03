package com.gdu.pakage.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.pakage.domain.MemberDTO;

@Mapper
public interface MemberService {
	
	
	
	public Map<String, Object> register(MemberDTO member, HttpServletResponse response);
	
	
	
	
	public Map<String, Object> getMemberList(int page);
	
	
	public Map<String, Object> getMemberByNo(int memberNo);
	
	public Map<String, Object> modifyMember(Map<String,Object> map, HttpServletResponse response);
	
	public Map<String, Object> removeMemberList(String memberNoList);
}
