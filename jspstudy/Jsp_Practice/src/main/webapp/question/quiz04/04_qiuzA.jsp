<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%!
	int ran1 = (int)(Math.random()*9)+1;
	int ran2 = (int)(Math.random()*9)+1;
	
%>
	

	
	<h4>랜덤 계산기</h4>
	<form action="04_qiuzB">
	<%=ran1%> + <%=ran2%> = 
	<input type="text" name="sum">
	<input type="hidden" name="ran1"  value="<%=ran1%>">
	<input type="hidden" name="ran2"  value="<%=ran2%>">
	<button>제출</button>
	</form>
</body>
</html>