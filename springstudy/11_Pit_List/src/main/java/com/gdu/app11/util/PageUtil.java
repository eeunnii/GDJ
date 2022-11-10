package com.gdu.app11.util;

import org.springframework.stereotype.Component;

import lombok.Getter;


@Component // @configuation이랑 web.xml 만드는 방법 2개 더해서 컨테이너에 bean만드는 방법 3가지
@Getter
public class PageUtil {

	private int page; // 현재 페이지(파라미터로 받아온다)
	private int totalRecord; // 전체 레코드 개수(DB에서 구해온다)
	private int begin; // 가져올 목록의 시작 번호(계산한다)
	private int end; // 가져올 목록의 끝 번호(계산한다)
	private int recordPerPage = 10; // 페이지에 표시할 레코드 개수(임의로 정한다)

	private int totalPage; // 전체페이지 개수
	private int pagePerBlock = 5;
	private int beginPage; // 블록의 시작페이지 번호(계산필요)
	private int endPage; // 블록의 끝 페이지 번호(계산필요)

	public void setPageUtil(int page, int totalRecord) {
		// page, totalRecord 필드 저장
		this.page = page;
		this.totalRecord = totalRecord;

		// begin, end 구하기
		begin = (page - 1) * recordPerPage + 1;
		end = begin + recordPerPage - 1;
		if (end > totalRecord) {
			end = totalRecord;
		}

		// totalpage페이지 계산
		totalPage = totalRecord * recordPerPage;
		if ((totalRecord % recordPerPage) != 0) {
			totalPage++;
		}

		// beginPage, endPage 계산
		beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		endPage = beginPage + pagePerBlock - 1;

		if (endPage > totalPage) {
			endPage = totalPage;
		}

	}

	// 경로는 변수처리 하는게 좋음.
	// <a href="path?page=beginPage-1"></a>

	/*
	 * public String getPaging(String path) { StringBuilder sb = new
	 * StringBuilder(); // 이전 블록 : 1 block이 아니면 이전 블록이 있다. if(beginPage!=1) {
	 * sb.append("<a href=\""+path+"?page="+(beginPage-1)+"\"◀</a>"); }
	 * 
	 * // 페이지 번호 : 마지막 블록이 아니면 다음 블록이 있다. for(int i=beginPage; i<=endPage; i++) {
	 * if(i==page) { sb.append(i); }else {
	 * sb.append("<a href=\""+path+"?page="+i+"\">"+i+"</a>"); }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // 다음 블록 : 마지막 블록이 아니면 다음블록이 있다. if(endPage != totalPage) {
	 * sb.append("<a href=\""+path+"?page=" + (endPage+1)+"\"?▶></a>"); } return
	 * sb.toString(); }
	 */

	
	  public String getPaging(String path) { 			//이 path자리에 request.getContextPath() + "/emp/search?colum="+column+"&query="+query; 가 넘어올 수 있음
		  												// 근데 아래 식에 의해서 request.getContextPath() + "/emp/search?colum="+column+"&query="+query+?~~~~ 이렇게 됨 
		  												// 물음표가 또 온다는 뜻 이를 &로 올 수 있게 처리해줘야함
	  
	 StringBuilder sb = new StringBuilder();
	 // 1. path에 파라미터가 없는 경우 
	 // /emp/list
	 // /emp/list?page=1 (page앞에 ?를 사용)
	 
	 // 2. path에 파라미터가 있는 경우 
	 // /emp/search?colum=EMPLOYEE_ID&query=150
	 // /emp/search?colum=EMPLOYEE_ID&query=150?page=1 (page앞에 &를 사용)
	 
	 if(path.contains("?")) {
		 path +="&";
	 }else {
		 path +="?";
	 }
	 
	 
	  
	  sb.append("<div class=\"paging\">");
	  
	  // 이전블록 : 1block이 아니면 이전블록이 있다 
	  if(beginPage != 1) {
	 sb.append("<a class=\"lnk\" href=\"" + path + "page=" + (beginPage-1) +
	  "\">◀</a>"); } else { sb.append("<span class=\"hidden\">◀</span>"); }
	  
	  // 페이지번호 : 현재 페이지는 링크가 없다 
		int endPage = beginPage + pagePerBlock - 1;
		for(int p = beginPage; p <= endPage; p++) {
			if(p <= totalPage) {
				if(p == page) {
					sb.append("<span class=\"now_page\">" + p + "</span>");
				} else {
					sb.append("<a class=\"lnk\" href=\"" + path + "page=" + p + "\">" + p + "</a>");
				}				
			} else {
				sb.append("<span class=\"hidden\">" + p + "</span>");
			}
		}
		
		// 다음블록 : 마지막 블록이 아니면 다음블록이 있다
		if(endPage < totalPage) {
			sb.append("<a class=\"lnk\" href=\"" + path + "page=" + (endPage + 1) + "\">▶</a>");
		} else {
			sb.append("<span class=\"hidden\">▶</span>");
		}
		
		sb.append("</div>");
		
		return sb.toString();
	  
	  }
	 

}
