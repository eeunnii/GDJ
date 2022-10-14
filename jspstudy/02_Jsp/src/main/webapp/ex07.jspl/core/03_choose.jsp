<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- <c:choose>태그는 if-else 구조를 가진다 --%>

<c:set var="age" value="19"/>

<c:choose>
	<c:when test="${age<=7}">미취학아동</c:when>
	<c:when test="${age<=13}">초등학생</c:when>
	<c:when test="${age<=16}">초등학생</c:when>
	<c:when test="${age<=19}">고등학생</c:when>
	<c:when test="${age<=00}">성인</c:when>
	<c:otherwise>인간이아님(100살초과)</c:otherwise>
	
</c:choose>

</body>
</html>