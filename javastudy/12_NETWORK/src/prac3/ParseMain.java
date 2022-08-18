package prac3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ParseMain {

	public static void main(String[] args) {
		
		
		// 영화 한 편 : movie 객체 
		// 영화 전체 : List<Movie> 리스트
		
		
		File file = new File("C:\\storage", "boxoffice.xml");
		List<Movie> movies = new ArrayList<>();
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			NodeList boxOfficeList = doc.getElementsByTagName("dailyBoxOffice");
			
			
			for(int i =0; i<boxOfficeList.getLength(); i++) {
				Element boxOffice = (Element)boxOfficeList.item(i);
				
				String movieCd = boxOffice.getElementsByTagName("movieCd").item(0).getTextContent();
				String movieNm = boxOffice.getElementsByTagName("movieNm").item(0).getTextContent();
				String openDt = boxOffice.getElementsByTagName("openDt").item(0).getTextContent();
				String salesAcc = boxOffice.getElementsByTagName("salesAcc").item(0).getTextContent();
				String audiAcc = boxOffice.getElementsByTagName("audiAcc").item(0).getTextContent();
				
				
				Movie movie = Movie.builder()
						.movieCd(movieCd)
						.movieNm(movieNm)
						.openDt(openDt)
						.salesAcc(salesAcc)
						.audiAcc(audiAcc)
						.build();				//xml에 필요한 객체가 자바가 됐음 // 이런 자바 객체를 POJO(데이터만 담고 있는 순수한 객체?)라고 함
				
				//메소드의 이름을 똑같이 만들어서 
				//메소드 체인을 위한???				
				movies.add(movie);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		for(Movie movie : movies) {
			System.out.println( movie);
		}
		
		
		
		
		
		
		

	}

}
