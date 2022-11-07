package com.gdu.app07;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.junit.runners.model.InitializationError;  // ★
import org.springframework.jndi.JndiTemplate;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// SpringUnit4ClassRunner를 대신하는 클래스!
// SpringUnit4ClassRunner를 상속받아서 만들어야 한다.

// 톰캣돌려서 쓰는거 안되니까 이 클래스 사용함

public class MySpringJunitClassRunner extends SpringJUnit4ClassRunner{
	
	public MySpringJunitClassRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
		jndi();
	}
	
	private void jndi() {
		// JNDI 방식으로 "java:comp/env/jdbc/oracle11g"이름의 Resource를 호출하면
		// 톰캣이 확인하는 context.xml을 참조하는 대신
		// 여기서 만든 BasicDataSource를 사용하시오.
		
		try {
			
			// SimpleNamingContextBuilder는 현재 사용을 하지 않는 것이 좋다 
			SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
			builder.activate();
			
			BasicDataSource dataSource = new BasicDataSource(); //  tomcat-dbcp 디펜더시가 필요하다.
			dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
			dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			dataSource.setUsername("SCOTT");
			dataSource.setPassword("TIGER");
			
			JndiTemplate jndiTemplate = new JndiTemplate();
			jndiTemplate.bind("java:comp/env/jdbc/oracle11g", dataSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
