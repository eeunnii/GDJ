package com.gdu.app11.util;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter

@Component    // @configuation이랑 web.xml 만드는 방법 2개 더해서 컨테이너에 bean만드는 방법 3가지
public class PageUtil {
	
	private int page; 			      // 현재 페이지(파라미터로 받아온다)
	private int totalRecord ;         // 전체 레코드 개수(DB에서 구해온다)
	private int begin; 				  // 가져올 목록의 시작 번호(계산한다)
	private int end;          		  // 가져올 목록의 끝 번호(계산한다)
	private int recordPerPage = 10;   // 페이지에 표시할 레코드 개수(임의로 정한다)
	
	private int totalPage;            // 전체페이지 개수 
	private int pagePerBlock = 5;
	private int beginPage;            // 블록의 시작페이지 번호(계산필요)
	private int endPage;              // 블록의 끝 페이지  번호(계산필요)
	
	public void setPageUtil(int page, int totalRecord) {
		// page, totalRecord 필드 저장
		this.page = page;
		this.totalRecord = totalRecord;
		
		
		// begin, end 구하기
		begin = (page-1) * recordPerPage + 1;
		end = begin+recordPerPage-1;
		if(end> totalRecord) {
			end = totalRecord;
		}
		
		// totalpage페이지 계산
		totalPage = totalRecord*recordPerPage;
		if ((totalRecord%recordPerPage)!=0) {
			totalPage++;
		}
		
		// beginPage, endPage 계산
		beginPage = ((page-1)/pagePerBlock)*pagePerBlock + 1;
		endPage = beginPage + pagePerBlock -1;
		
		if(endPage>totalPage) {
			endPage =totalPage;
		}

	}
	
	
	
	
	
	
	
}
