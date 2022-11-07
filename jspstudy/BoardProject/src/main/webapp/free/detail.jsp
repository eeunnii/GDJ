<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>
	function fn1(){
		
		location.href = '${contextPath}/list.do';
		
	}
</script>
</head>
<body>

	
	<h1>자유게시판 게시글 상세보기 화면</h1>

	<form id="frm_detail" method="post" action="${contextPath}/modify.do">	

		<a >게시글번호 : ${free.freeNo}</a><br><br>
		<a>작성자 : ${free.writer}</a><br><br>
		<a>작성자IP : ${free.ip}</a><br><br>
		<a>조회수 : ${free.hit}</a><br><br>
		
		
		<label for="title">제목</label><br>
		<input type="text" name="title" id="title" value="${free.title}"><br><br>	
		<input type="hidden" name="freeNo" value="${free.freeNo}">
		
		<label for="content">내용</label><br>
		<input type="text" name="content" id="content" value="${free.content}"><br><br>		
		
		<button onclick="fn2();">수정</button>
		<input type="button" value="목록" id="btn_list" onclick="fn1();">
			
	</form>
	
	
	
</body>
</html>