package com.gdu.app01.java01;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.app01.java02.Student;

/*
 * 	@Congigration
 * 	안녕. 난 Bean을 만드는 Java파일이야.
 * 	Spring Bean Configuarion File하고 하는 일이 같지.
*/
@Configuration
public class SpringBeanConfig {
	
	//학생 한 명 요구한거라서 bean태그 하나만 잇음됨
	@Bean
	public Student stud() {  // <bean id="stud" class="Student"
		//List
		List<Integer> scores = new ArraryList<Integer>();
		for(int cnt=0; cnt<5; cnt++) {
			scores.add((int)(Math.random()*101+0)); // 깃허브
		}
		
		// Set
		Set<String> awards = new HashSet<String>();
		awards.add("개근상");
		awards.add("장려상");
		awards.add("우수상");
		
		// Map
		Map<String, String> contact = new HashMap<String, Sting>();
		contact.put("address", "서울");
		contact.put("tel", "02-123-4567");
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

	
}
