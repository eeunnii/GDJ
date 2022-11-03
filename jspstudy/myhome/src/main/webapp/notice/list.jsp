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
		
			</td>
		</tr>
		
	</tfoot>
</table>

</body>
</html>