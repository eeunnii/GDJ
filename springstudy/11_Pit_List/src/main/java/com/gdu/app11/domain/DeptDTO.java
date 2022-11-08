package com.gdu.app11.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeptDTO {
	private int departmentID;
	private String departmentName;
	private String managerID;
	private int locationID;
}
