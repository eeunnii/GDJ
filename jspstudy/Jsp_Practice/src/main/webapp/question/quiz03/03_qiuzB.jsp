<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% request.setCharacterEncoding("UTF-8");%>
	
	<form action="03_qiuzC.jsp">
	<h4>1. 좋아하는 운동선수는 누구인가요?</h4>
	<input type="text" name="sport">
	<input type="hidden" name="writer" value="<%=request.getParameter("writer")%>">
	<input type="hidden" name="sport" value="<%=request.getParameter("sport")%>">
	<button>결과보기</button>
	</form>
	

</body>
</html>