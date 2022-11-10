package com.gdu.app12.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class BbsDTO {
	private int bbsNo;
	private String writer;   // 원래 작성자 이름을 적지 않음. 로그인된상태로 글쓰면 자동으로 작성자 들어가니까
	private String title;
	private String ip;
	private Date createDate;
	private int state;
	private int depth;
	private int groupNo;
	private int groupOrder;
}
