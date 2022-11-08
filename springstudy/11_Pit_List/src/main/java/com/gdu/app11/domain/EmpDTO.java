package com.gdu.app11.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmpDTO {
	private int employeeID;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date hireDate;
	private String jobID;
	private int salary;
	private int commissionPct;
	private int managerID;
	private DeptDTO deptDTO;
}
