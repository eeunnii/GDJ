package com.gdu.staff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.mapper.StaffMapper;


@Service
public class StaffServiceImpl implements StaffService {
	
	@Autowired
	private StaffMapper staffMapper;
	
	
	@Override
		public List<StaffDTO> getStaffList() {
			return staffMapper.selectStaffList();
		}
	
	@Override
	public ResponseEntity<String> addStaff(StaffDTO staff) {
		if(staff.getSalary() == 0){
			switch(staff.getDept()) {
			case "기획부" : staff.setSalary(5000); break;
			case "개발부" : staff.setSalary(6000); break;
			case "영업부" : staff.setSalary(7000); break;
			default : staff.setSalary(4000);
			}
		}
		
		try {
			staffMapper.insertStaff(staff);   // ResponseEntity 에서 T가 String이라는 뜻임 템플릿. T body는 응답할 String 자체라는뜻
			return new ResponseEntity<String>("사원등록이 성공했습니다.", HttpStatus.OK);  // 인덱스에서 text로 받는다고 되어있음.   // 숫자 200 반환
			
		}catch(Exception e) {
			return new ResponseEntity<String>("사원등록이 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	

	
	@Override
	public StaffDTO reserchresult(int query) {
		
		
		
		
			return staffMapper.serchStaff(query);
	
	}
	
	
}
