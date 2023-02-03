package com.gdu.pakage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.pakage.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	
	public int insertMember(MemberDTO member);
	public int selectMemberCount();
	public List<MemberDTO> selectMemberDTO(Map<String, Object> map);
	public MemberDTO selectMemberDTO(int memberNo);
	public int updateMember(Map<String, Object> map);
	public int deleteMemberList(List<String> memberNOList);
	
	
	
	
}
