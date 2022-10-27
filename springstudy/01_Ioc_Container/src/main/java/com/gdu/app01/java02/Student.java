package com.gdu.app01.java02;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gdu.app01.java01.ArraryList;
import com.gdu.app01.java01.HashMap;
import com.gdu.app01.java01.Map;
import com.gdu.app01.java01.Sting;

public class Student {
	private Scores;
	private Awards
	private 
	
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
