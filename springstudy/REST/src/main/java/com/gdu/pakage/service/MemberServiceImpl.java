package com.gdu.pakage.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.pakage.domain.MemberDTO;
import com.gdu.pakage.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberMapper memberMapper; 
	
	@Override
	public Map<String, Object> register(MemberDTO member, HttpServletResponse response){
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("insertResult", memberMapper.insertMember(member));
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName());
			return null;
		}
	}
}
