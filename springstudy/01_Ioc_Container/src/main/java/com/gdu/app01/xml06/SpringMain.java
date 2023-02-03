package com.gdu.app01.xml06;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		
		//ApplicationContext을 만들면 xml에 설정한 bean이 모두 '일단' '생성'된다.
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml06/appCtx.xml");
		Person p = ctx.getBean("human", Person.class); // context.getBean(Class<T> , id값 (BeanName) )
		
		
		p.info();
		ctx.close();

	}

}
