<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 
		1. Jsp의 Binding 영역 4개 
			1) application : ServletContext와 같은 영역
			2) session : HttpSesstion과 같은 영역
			3) request : HttpServletRequest와 같은 영역
			4) pageContext : 하나의 Jsp 페이지에서 접근 가능한 영역
	--%>
	
	<%
		application.setAttribute("a", 1);
		session.setAttribute("b",2);
		request.setAttribute("c", 3);
		pageContext.setAttribute("d", 4);
	%>
	
	<%-- 표현식으로 속성 접근 --%>
	<div>a : <%=application.getAttribute("a") %></div>
	<div>b : <%=session.getAttribute("b") %></div>
	<div>c : <%=request.getAttribute("c") %></div>
	<div>d : <%=pageContext.getAttribute("d") %></div>
	
	<%-- 표현언어(EL)를 이용해 속성 접근 
		(표현식보다 표현언어쓰는게 좋음), 디자이너들은 표현식 기겁함 --%>
	<div>a : ${a}</div>
	<div>b : ${b}</div>
	<div>c : ${c}</div>
	
	<%--
		2. 우선 순위 
			1) 같은 이름의 속성이 서로 다른 영역에 저장된 경우 먼저 사용되는
			2) pageContext > request > session > application 순으로 우선 순위가 높음
			3) 각 영역을 지정하는 표현언어(EL)의 내장 객체가 존재함
				(1) pageScope
				(2) requestScope
				(3) sessionScope
				(4) applicationScope
	 --%>
	 
	 <%
	 	application.setAttribute("movie", "기생충");	// 우선순위 낮음
	 	session.setAttribute("movie", "터미네이터");
	 	request.setAttribute("movie", "아바타");
	 	pageContext.setAttribute("movie", "미나리"); // 우선순위가 높은게 pageContext임
	 %>
	 
	 <div>application's movie : ${movie}</div>
	 <div>session's movie : ${movie}</div>
	 <div>request's movie : ${movie}</div>
	 <div>pageContext's movie : ${movie}</div>
	 <%-- 위 값 출력해보면 동일한 값 4개 나옴, 덮어쓰기 개념아님. 원하는 값 꺼내쓰기 --%>
	
	<hr>
	
	<div>application's movie : ${applicationScope.movie}</div>
	<div>application's movie : ${sessionScope.movie}</div>
	<div>application's movie : ${requestScope.movie}</div>
	<div>application's movie : ${movie}</div>
	<%-- 4개 영역에 존재하지 않는건 EL을 사용할 수 없음, 
		일반 자바 변수를 EL로 표기할 수 없음, 그냥 표현식 써야함 --%>
</body>
</html>