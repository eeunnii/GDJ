package com.gdu.app01.java02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.gdu.app01.java01.SpringBeanConfig;

public class SpringMain {

	public static void main(String[] args) {
		
		
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(SpringBeanConfig.class);
		Student student = ctx.getBean("stud", null);
		
		System.out.println(student.getScores());
		System.out.println(student.getAwards());
		System.out.println(student.getContact());
		
		ctx.close();
	}

}
