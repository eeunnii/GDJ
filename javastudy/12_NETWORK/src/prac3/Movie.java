package prac3;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString

public class Movie {
	
	
	
	private String audiAcc; // 누적관객수
	
	
	private String movieCd;	// 영화 코드
	private String movieNm;	//영화명
	private String openDt;	//개봉일
	private String salesAcc; // 누적매출액 
	
	

}
