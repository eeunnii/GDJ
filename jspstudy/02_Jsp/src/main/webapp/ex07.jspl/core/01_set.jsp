<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- Core Library ▼1.2버전만.. 태그 이름이 c로 시작 / prefix:앞의내용--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		속성(Attribute) 만들기 태그
		
		1. <c:set var="속성명" value="값" scope="영역">
		2. 영역 : page(디폴트), request, session, application
	 --%>
	 
	 <c:set var="name" value="정은지" scope="page" />
	 <c:set var="age" value="24" scope="page"/>
	 <c:set var="isAdult" value="${age>=20}" scope="page"/>
	 <c:set var="height" value="180.5" scope="page"/>
	 <c:set var="weight" value="73.5" scope="page"/>
	 <c:set var="bmi" value="${weight div (height*height*0.0001)}" scope="page"/>
	 <c:set var="health" value="${bmi ge 25? '비만':'정상'}" scope="page"/>
	 
	 <%--
	 	위와 같음
	 	pageContext.setAttribute("name", "정은지");
	 	pageContext.setAttribute("age", 24);
	 	
	 	value로 el 쓰는게 문제 없구나!를 알아야함
	 --%>
	 
	 <h1>이름 : ${name}</h1>
	 <h1>나이 : ${age}</h1>
	 <h1>${isAdult? '성인':'미성년자'}</h1>
	 <h1>키 : ${height}</h1>
	 <h1>몸무게 : ${weight}</h1>
	 <h1>${bmi}</h1>
	 <h1>건강상태 : ${health}</h1>
	 
		

</body>
</html>