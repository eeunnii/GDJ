package com.gdu.app01.java01;

public class Singer {
	private String name;
	private Song son;
	
	//constructor
	public Singer() {
		
	}

	public Singer(String name, Song son) {
		super();
		this.name = name;
		this.son = son;
	}

	// getter/setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Song getSon() {
		return son;
	}

	public void setSon(Song son) {
		this.son = son;
	}
	
	
}
