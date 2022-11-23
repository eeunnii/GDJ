<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Optional<String> opt = Optional.ofNullable(request.getParameter("title"));
	String title = opt.orElse("Welcome");
	pageContext.setAttribute("title", title);   // EL 사용을 위함(${title}) -- 일반 자바 변수는 EL로 사용할 수 없다.
 	pageContext.setAttribute("contextPath", request.getContextPath());  // C:SET이 하던 일을 자바로 옮겨왔음
%>    
    <!-- moment-with-locales.js 날짜 시간을 표시해주는 라이브러리 -->
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script src="${contextPath}/resources/js/moment-with-locales.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.css">

</head>
<body>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!-- 여기서 정의한 contextPath는 scope=page 임 -->
	
	<div>
		<h1>Welcom to my BLOG</h1>
	
	 
	
	</div>
	  
	
	
