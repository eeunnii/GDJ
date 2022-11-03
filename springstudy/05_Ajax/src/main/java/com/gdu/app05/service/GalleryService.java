package com.gdu.app05.service;

import org.springframework.http.ResponseEntity;

public interface GalleryService {
	//이미정보를 주면 바이트 배열로 반환해주겠다 
	public ResponseEntity<byte[]> imagedisplay(String path, String filename);

}
