package com.gdu.pakage.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.pakage.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	
	public int insertMember(MemberDTO member);
	
	
	
}
