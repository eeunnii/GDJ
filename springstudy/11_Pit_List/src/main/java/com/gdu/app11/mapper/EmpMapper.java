package com.gdu.app11.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app11.domain.EmpDTO;

@Mapper
public interface EmpMapper {
	public int selectAllEmployeesCount();
	public List<EmpDTO> selectEmployeesByPage(int begin, int end);  // 여태까지 ()안에 전달하는 값이 하나였음, 2개 전달할 때는! employee.xml 이동해보기

	
}
