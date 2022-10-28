package com.gdu.app01.java04;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

 // xml에 등록된 빈을 가져와라!

// java04/appCtx.xml에 등록된 Bean을 가져오시오
 @ImportResource(value="java04/appCtx.xml")


@Configuration
public class SpringBeanConfig {
	
}
