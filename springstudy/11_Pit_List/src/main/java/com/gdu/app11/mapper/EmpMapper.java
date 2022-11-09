package com.gdu.app11.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app11.domain.EmpDTO;

@Mapper

/*
@Mapper

안녕. 난 mybatis의 매퍼와 직접 연결되는 인터페이스야.
내가 가진 메소드를 호출하면 매퍼의 쿼리문이 바로 호출되지.
내 메소드 이름은 쿼리문의 id와 같아야 해.
날 찾으려면 @MapperScan이 필요해. (DBConfig에 추가할거야.)
*/

public interface EmpMapper {
	public int selectAllEmployeesCount();
	public List<EmpDTO> selectEmployeesByPage(Map<String, Object> map);  // 여태까지 ()안에 전달하는 값이 하나였음, 2개 전달할 때는! employee.xml 이동해서 확인하기
	public int selectFindEmployeesCount(Map<String,Object> map);
	public List<EmpDTO> selectFindEmployees(Map<String,Object> map);
}
