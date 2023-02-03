package com.gdu.app05.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.gdu.app05.domain.Board;

public interface BoardService {
	public ResponseEntity<Board> execute1(HttpServletRequest requset);// ajax 응답 전용
	public ResponseEntity<Board> execute2(String title, String content);// ajax 응답 전용
	public ResponseEntity<Board> execute3(Board board);// ajax 응답 전용
	
	
	
}
