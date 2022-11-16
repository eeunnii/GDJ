package com.gdu.staff.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.gdu.staff.domain.StaffDTO;

public interface StaffService {
	public List<StaffDTO> getStaffList();
	public ResponseEntity<String> addStaff(StaffDTO staff);  // ajax응답은.. String이다
	public StaffDTO reserchresult(int query);
}
