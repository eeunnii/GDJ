<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function(){
		$('#btn_write').click(function(event){
			location.href = 'insertpost.jsp';
		})
	});

</script>
</head>
<body>

	<h1>총 게시글 : ${count}개</h1>
	<table border="1">
		<thead>
			<tr>
				<td>순번</td>
				<td>작성자</td>
				<td>제목</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody>
		
			<c:if test="${empty posts}">
				<tr>
					<td colspan="4">게시물이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty posts}">
				<c:forEach items="${posts}" var="post">
					<tr>
						<td>${post.no}</td>
						<td>${post.witer}</td>
						<td><a href="${contextPath}/detail.do?no=${post.no}">${post.title}</a></td>
						<td>${post.createdate}</td>
					</tr>
				</c:forEach>
			</c:if>
			
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4"><input type="button" id="btn_write" value="새글작성"></td>
			</tr>
		</tfoot>
	</table>
</body>
</html>