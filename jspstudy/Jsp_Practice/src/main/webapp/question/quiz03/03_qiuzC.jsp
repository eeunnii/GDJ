<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% 
request.setCharacterEncoding("UTF-8");

%>

<h3>${writer}님의 선호도 조사 결과</h3>
 <ul>
 	<li>좋아하는 연예인 : ${talent}</li>
 	<li>좋아하는 운동 선수 : ${sport}</li>
 </ul>


</body>
</html>