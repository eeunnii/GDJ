package com.gdu.app14.domain;

import java.sql.Timestamp;

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
	private Timestamp createDate;
	private Timestamp modifyDate;
	
//	 첨부갯수가 몇개이다~~ 라는 정보가 포함되어있지 않음
	// sql의 테이블과 항상 똑같을 필요 없음
	
	private int attachCnt;  // 첨부파일 갯수
}
