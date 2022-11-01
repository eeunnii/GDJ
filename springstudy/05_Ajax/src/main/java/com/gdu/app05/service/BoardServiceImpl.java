package com.gdu.app05.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.gdu.app05.domain.Board;

public class BoardServiceImpl implements BoardService {
	/*
	 * ResponseEntity<T> 클래스 
	 * 안녕 난 Ajax 응답 데이터를 만들기 위한 전용 클래스야.
	 * 
	 * new ResponseEntity<T>(T body, HttpHeaders header, HttpStatus status)
	 * 1) T body : 실제 응답할 데이터
	 * 2) HttpHeaders header : 응답 헤더
	 * 3) HttpStatus status : 응답 코드(20,,404,500등)
	 */
	
	

	@Override
	public ResponseEntity<Board> execute1(HttpServletRequest requset) {
		
		String title = requset.getParameter("title");
		String content = requset.getParameter("content");
		
		Board board = new Board(title, content);
		
		ResponseEntity<Board> entity = null;
		if(title.isEmpty()) {
			entity = new ResponseEntity<Board>(board, HttpStatus.INTERNAL_SERVER_ERROR);   // 주로 매개변수 2개 아니면 3개인거 사용  //HttpStatus.INTERNAL_SERVER_ERROR " 자바 코드가 잘못되었을때, 500번대임
																						//$.ajax()의 error에서 처리
		}else {
			entity = new ResponseEntity<Board>(board,HttpStatus.OK); // $.ajax()의 success에서 처리
		}
		
		//성공했을 때 만들어서 날렸으니까 제목을 입력하는 경우는 성공으로 보는거임
		
		return null;
	}

	@Override
	public ResponseEntity<Board> execute2(String title, String content) {
		
		
		ResponseEntity<Board> entity = null;
		
		org.springframework.http.HttpHeaders header = new HttpHeaders();
		header.add("content-Type", MediaType.APPLICATION_JSON_VALUE);
		
		if(title.isEmpty()) {
			entity = new ResponseEntity<Board>(HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			entity = new ResponseEntity<Board>(new Board(title,content),header,HttpStatus.OK);
		}
		return entity;
	}

	@Override
	public ResponseEntity<Board> execute3(Board board) {
		
		return board;
	}

}
