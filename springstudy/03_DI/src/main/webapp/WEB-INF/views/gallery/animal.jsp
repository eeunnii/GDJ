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

		<h1>여기 예쁜 동물 좀 보세요!!</h1>
		<img alt="오류" src="${contextPath}/resources/images/animal1.jpg" width="200px">
		
		<h1>저도 봐주세요 </h1>
		<img src="assets/images/animal2.jpg" width="200px">
		<!-- 동일한 경로인데 누군되고 누군 왜 안될까 .. ?  -->
		<!-- servlet-context.xml 에서 Handles HTTP GET requests for /resources/** by efficiently serving up static resources in the ${webappRoot}/resources directory 
			여기보면 정적자원으로 명시되어있음.
		
		 -->

</body>
</html>