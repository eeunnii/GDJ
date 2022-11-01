<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	회원 상세 보기 
	
	<div>제목 ${title}, ${board.title}</div> <%-- ${member.id}는 member.getId()를 호출한다.  ${member.id}는 자바랑 연결되서 html의 주석으로 --%>
	<div>조회수 ${hit}, ${board.hit}</div> <%--  ${member.pw}는 member.getPw()를 호출한다.  --%>
	
	
</body>
</html>