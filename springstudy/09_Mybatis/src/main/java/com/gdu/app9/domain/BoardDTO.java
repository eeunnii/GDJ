package com.gdu.app9.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {
	private int boardno;
	private String title, content, writer, createdate, modifydate;
}
