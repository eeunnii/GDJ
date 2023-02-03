package com.gdu.app06.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data 

public class BoardDTO {
	private int board_no;
	private String title, content, writer, create_date, modify_date;
	
	public BoardDTO(int board_no, String title, String content, String writer, String create_date, String modify_date) {
		super();
		this.board_no = board_no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.create_date = create_date;
		this.modify_date = modify_date;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getModify_date() {
		return modify_date;
	}

	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
	
	
	
	
}
