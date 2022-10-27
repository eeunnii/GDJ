package com.gdu.app01.xml05;

import java.sql.Connection;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {
	
	// 프로젝트의 Build Path에 ojdbc6.jar 등록하고 실행합니다.

	public static void main(String[] args) throws Exception{
		
		// main에서 예외를 던지면 자바버추얼머신 jdbc가 받음 아무도 예외를 처리하지 않는거임
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml05/appCtx.xml");
		MyConnection mycon = ctx.getBean("conn", MyConnection.class);
		Connection con = mycon.getConnection();
		
		if(con!=null) {
			con.close();
			System.out.println("Connection 성공!");
		}
		
		ctx.close();
	}

}
