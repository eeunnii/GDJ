package com.gdu.app04.domain;

public class Board {
	
	private String title;
	private int hit;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}
	
	// constructor
	public Board(String title, int hit) {
		super();
		this.title = title;
		this.hit = hit;
	}
	
	// getter setter
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
	
}
