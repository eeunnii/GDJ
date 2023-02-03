<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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
		int age = request.getParameter("age").isEmpty()? 0 : Integer.parseInt(request.getParameter("age"));
		
		Map <String,String> agelist = new HashMap<>();
		
		if(age <20) {
			agelist.put("result1", "미성년자");
			agelist.put("result2", "불가능");
		}else{
			agelist.put("result1", "성인");
			agelist.put("result2", "가능");
		}
		
		request.setAttribute("map", agelist);
	
		request.getRequestDispatcher("02qiuzC.jsp").forward(request,response);
	%>
	


</body>
</html>