package ex07_collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
	
	public static void printMovies(List<String> list) {
		for(int i =0, size = list.size(); i<size; i++) {
			System.out.print(list.get(i));
			if(i<size -1) {// size - 1 마지막 요소의 인덱스
				System.out.println("->");
				
			}
			
		}
		
	}

	public static void main(String[] args) {
		
		
		List<String> movies = new ArrayList<String>();
		movies.add("쇼생크탈출");
		movies.add("명량");
		movies.add("에일리언");
		movies.add("여인의향기");
		
		
		printMovies(movies);
		
		
		// 오름차순 동일movies리스트를 오름차순으로 정렬시킴
		//Collection.<movies>
		
		
		
		
		
		
		
		

	}

}
