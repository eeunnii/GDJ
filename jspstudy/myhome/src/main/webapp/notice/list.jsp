<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--		 https://jquery.com/ 접속 후 다운로드 제이쿼리 주확생 버튼 누름
				스크롤 내려서 Using jQuery with a CDN 로 옴
				하이퍼링크  https://code.jquery.com  클릭
					minified 클릭. 복사 여기다가 붙여넣기 하면 js파일 없어도 쿼리 사용 가능
					min.jsp가 min이 mini 엿음  -->
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<td>공지번호</td>
				<td>제목</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${notices}" var="notice">
				<tr>
					<td>${notice.noticeNo}</td>  <!-- notice.getNoticeNo도 되는데 마이바티스가 알아서 해주니까 get할 필ㅇ없음. -->
					<td>${notice.title}</td>
					<td>${notice.createDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	<tfoot>
		<tr>
			<td colspan="3">
				<!-- 이전 블락 : 1,2,3 page는 이전 블록이 없다. -->
				<c:if test="${page>pagePerBlock}">
					<a href="${contextPath}/notice/list.no?page=${beginPage-1}">&lt;이전블록</a>
				</c:if>
				
				
				
				<!-- 이전 페이지 : 1페이지는 이전 페이지가 없다.-->
				<c:if test="${page != 1}">
					<a href="${contextPath}/notice/list.no?page=${page-1}">&lt;이전</a>
				</c:if>
				
				
				<!-- 1 2 3 -->
				<c:forEach begin="${beginPage}" end="${endPage}" step="1" var="p">
				<!-- 현재 페이지는 링크가 걸리지 않는다 -->
				<c:if test="${page == p}">
					${p}
				</c:if>
				
				<!-- 현재 페이지가 아니면 링크를 건다. -->
				<c:if test="${page != p}">
					<a href="${contextPath}/notice/list.no?page=${p}">${p}</a>
				</c:if>
				</c:forEach>
				<!-- 다음페이지 : 마지막 페이지는 다음 페이지가 없다.-->
				<c:if test="${page != totalPageCnt}">
					<a href="${contextPath}/notice/list.no?page=${page+1}">다음&gt;</a>
				</c:if>
				<!-- 다음 블록 : 마지막 블록(endPage가 totalPageCnt인 경우)은 다음 블록이 없다. -->
				<c:if test="${endPage != totalPageCnt}">
					<a href="${contextPath}/notice/list.no?page=${endPage+1}">다음블록&gt;</a>
				</c:if>
			</td>
		</tr>
	</tfoot>
</table>

</body>
</html>