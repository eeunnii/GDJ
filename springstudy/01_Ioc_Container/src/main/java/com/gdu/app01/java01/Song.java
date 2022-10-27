package com.gdu.app01.java01;

public class Song {
	private String Genre;
	private String Title;
	public String getGenre() {
		return Genre;
	}
	public void setGenre(String genre) {
		Genre = genre;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	
	public Song() {
		// TODO Auto-generated constructor stub
	}
	public Song(String genre, String title) {
		super();
		Genre = genre;
		Title = title;
	}
	
}
