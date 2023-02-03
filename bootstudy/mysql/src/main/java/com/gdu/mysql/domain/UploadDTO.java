package com.gdu.mysql.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UploadDTO {
	private int uploadNo;
	private String title;
	private String content;
	private Date createDate; // MYSQL에서 DATETIME이였던 데이터 타입을 자바에선  date가 받아올 수 있음
	private Date modifyDate;
	private int attachCnt;
}
