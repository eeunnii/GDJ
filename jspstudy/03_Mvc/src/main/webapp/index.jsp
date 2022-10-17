<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>


<%--  <%=request.getContextPath%> 와같음, /03_Mvc를 ${contextPath}로 부르기로 한거임  --%> 


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 압축파일로 만들었을 때 그 파일을 contextPath처럼 인식하는 경우도 있어서 contextPath는 변수처리함 -->
	<h3><a href="${contextPath}/today.do">오늘은 며칠입니까?</a></h3>  <!-- 요청마다 서블릿 하나 -->
	<h3><a href="${contextPath}/now.do">오늘은 몇시입니까?</a></h3>
	<h3><a href="${contextPath}/input.do">입력화면으로 이동하기</a></h3>

</body>
</html>