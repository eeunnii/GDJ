<%@page import="java.util.HashMap"%>
<%@page import="domain.Board"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 1. 1~5 --%>
	<c:forEach var="n" begin="1" end="5" step="1">
		<div>${n}</div>
	</c:forEach>
	
	<hr>
	
	<%--2. 5~1 --%>
	<c:forEach var="n" begin="1" end="5" step="1" >
	<div>${6-n}</div>
	</c:forEach>
	
	<hr>
	
	<%--3. <select> 1월~12월 --%>
	
	
	
	<%--4. 배열 // varStatues 배열의 인덱스를 꺼내서 선언하고 싶으면 써야함--%>
	<%
		String[] menus = {"튀김","떡볶이","순대"};
		pageContext.setAttribute("menus", menus);
	%>
	<c:forEach var="menu" items="${menues}" varStatues="vs">
		인덱스 : ${vs.index},  순번 : ${vs.count}, 배열요소 : ${menu}<br>
	</c:forEach>
	
	<hr>
	
	
	<%-- 5. 리스트 // jsp, 배열과 list를 동일하게 취급--%>
	<%
		List<String> seasons = Arrays.asList("봄","여름","가을","겨울");
		pageContext.setAttribute("seasons", seasons);
	%>
	
	<c:forEach var="season" items="${seasons }" varStatus="k">
		인덱스 : ${k.index }, 순번 : ${k.count }, 리스트요소 : ${season }<br>
	</c:forEach>
	
	<hr>
	
	<%-- 6. Map(반복이 필요한건아님) , int라고 적으면 안됨. 왜..지 < >꺽새안은 제너릭타입이라서--%>
	<%
		Map<String, Integer> map = new HashMap<>();
		map.put("begin",1);
		map.put("end",10);
		pageContext.setAttribute("map", map);
	%>
	${map.begin}~${map.end}<br>
	
	<%-- 7. 객체(반복이 필요한 건 아님) --%>
	<%
		Board board = new Board();
		board.setBoardNo(1);
		board.setTitle("언제");
		board.getHit(100);
		pageContext.setAttribute("board", board);
	%>
	${board.boardNo}, ${board.title}, ${board.hit}<hr>
	
	<%--${board.title}은 ${board.getTitle()}을 자동으로 호출한다 그래서 게터세터없으면 오류남 // bin 만들때 게터세터 기본적으로 만들어야함--%>
	
	<%--
		문제. 임의의 Board 객체를 3개 저장한 리스트 
	 --%>
	 <table border="1">
	 	<thead>
	 		<tr>
	 			<td>순번</td>
	 			<td>게시글번호</td>
	 			<td>제목</td>
	 			<td>조회수</td>
	 		</tr>	
		</thead>
		<tbody>
			<c:forEach var="board" items="${boards}" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td>${board.boardNo}</td>
					<td>${board.title}</td>
					<td>${board.hit}</td>
				</tr>
			 </c:forEach>
		
		</tbody>
	 
	 </table>
	
	
</body>
</html>