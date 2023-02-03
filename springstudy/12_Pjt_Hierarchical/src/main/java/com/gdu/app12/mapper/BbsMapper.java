package com.gdu.app12.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app12.domain.BbsDTO;

@Mapper
public interface BbsMapper {
	public int selectAllBbsCount();
	public List<BbsDTO> selectAllBbsList(Map<String, Object> map);
	// 매퍼는 db에서 가져옴. 
	// 이름은 all이지만 골라서 가져올거임
	// begin이랑 end값 넘겨야함 -- > map 사용함
	public int insertBbs(BbsDTO bbs); // 원글삽입
	public int insertReply(BbsDTO bbs); // 댓글 삽입
	// 댓글이 들어갈 때 해야할 일 :  
	// 나 말고 이미 들어간 애들의 order 값(상태)을 .. 업데이트를 해줘야함
	
	// insert 하면서 기존에 있던 애들을 update하니까 이건 하나의 트랜잭션임
	public int updatePreviousReply(BbsDTO bbs);   // 댓글 삽입 전 기존 답글의 GROUP_ORDER 업데이트
	// 기존애들이 숫자 하나씩 늘어나고 새로 들어간 애가 제알 작은 숫자를 가져서 정렬했을 때! 기존애들이 맨 위로가게 할거임
	public int deleteBbs(int bbsNo);
	
	
	// 인터페이스는 private가 없음 public만 있음 -- public 생략가능
	// 
	
	// public abstract int selectAllBbsCount(); 으로 적기도 하는데 public abstract 생략가능

}
