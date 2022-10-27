package com.gdu.app01.xml08;

public class SpringMian {

	public static void main(String[] args) {
		Member member = ctx.getBean("member", Member.class);
		member.info(); 
		

	}

}
