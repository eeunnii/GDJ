<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>보고 싶은 동물 ${param.filename}</h1>  <!-- 응답이 파라미터로 와서 . 파라미터로 꺼내기. -->
	<img src="${contextPath}/resources/images/${param.filename}" width="200px">
	
</body>
</html>