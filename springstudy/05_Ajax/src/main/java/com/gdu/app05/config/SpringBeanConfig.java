package com.gdu.app05.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.app05.service.BoardService;
import com.gdu.app05.service.BoardServiceImpl;

@Configuration
public class SpringBeanConfig {
	
	@Bean
	public BoardService boardservice() {
		
		return new BoardServiceImpl();
	}
	/// 이렇게 빈 만들면 appCtx에서 bean만든거랑 똑같음 차이없음

}
