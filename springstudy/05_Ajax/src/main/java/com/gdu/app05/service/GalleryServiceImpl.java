package com.gdu.app05.service;

import java.io.File;
import java.nio.file.Files;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

public class GalleryServiceImpl implements GalleryService {

	@Override
	public ResponseEntity<byte[]> imagedisplay(String path, String filename) {
		
		File file = new File(path, filename); // 경로와 파일이름
		ResponseEntity<byte[]> entity = null;
		
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", contentType);   // 이미지의 mine type는 image이다 그리고 뒤에 확장자명 옴 image.jpg.. 등.. // Files는 1.7 이후로 나옴.. 
			// probeContentType 파일을 관리하는 경로 객체 // file.toPath() 파일의 경로 반환 
			
			// 파일을 바이트 배열로 바꿔주기
 			entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file),header, HttpStatus.OK);
 					
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return entity;
		
		//jpeg 제이팩이라고 부름

	}

}
