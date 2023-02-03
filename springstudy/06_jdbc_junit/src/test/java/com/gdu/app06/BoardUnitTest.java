package com.gdu.app06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gdu.app06.domain.BoardDTO;
import com.gdu.app06.repository.BoardDAO;

/*
 * 안녕. 난 테스트를 수행하는 클래스야
 * 
 * JUnit4를 이용한 테스트를 수행해.
 * 
 * pom.xml에 junit 버전 나와잇음
 */

@RunWith(SpringJUnit4ClassRunner.class)

/*
 * 깃허브~~~
 * 
 *  안녕 난 컨테이너에 저장된 bean이 어디에 있는지 알려주는 역할이야 
 * 1 .root-context.xml에 <bean> 태그를 작성한 경우 
 * @ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml/appServlet/servlet-context.xml"})
 * 2. com.gdu.app06.config.SpringBeanConfig.java에 @Bean을 작성한 경우 
 * @ContextConfiguration(classes={SpringBeanConfig.class})
 * 3. component-scan과 컴포넌트(@Component, @Service, @Repository
 */

@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})  // 배열 묶어주는 법 : [] 자바스크립트, {}자바.
public class BoardUnitTest {
	
	// DAO단위로 Unit테스트를 진행하기 때문에 
	// BoardDAO가 필요하다
	
	@Autowired
	private BoardDAO dao;
	
	@Test
	public void 삽입테스트() {
		BoardDTO board = new BoardDTO(0, "테스트제목", "테스트내용", "테스트작성자", "null", "null");
		assertEquals(1, dao.insertBoard(board));
		
				
	}
	
	@Test
	public void 조회테스트() {
		assertNotNull(dao.selectBoardByNo(1));
	}
	
	// test할 때 톰캣은 안들아감 , 그래서 jsp는 컨트롤러 단위로 test해도 소용없음
	// 근데 스프링은 상황을 대비에서 mvc처럼 돌아가는 클래스가 따로 있어서 가능함
	
	

}
