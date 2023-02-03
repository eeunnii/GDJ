<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
 <%-- <%@ page 페이지 지시어라고 함 

		동적 레이아웃 
			1) 포함할 페이지에 변경되는 부분이 있음
			2) <jsp:include> jsp라고 시작하는 태그를 액션태그라고 함
			3) <jsp:param> 액션 태그를 이용해 파라미터 전달함	
--%>
	
<% request.setCharacterEncoding("UTF-8"); %>	
<jsp:include page="header.jsp">
	<jsp:param value="경제" name="title" />
</jsp:include>

<!-- 바디에서 헤더를 포함시키기 -->


<section>
	<div>경제1</div>
	<div>경제2</div>
	<div>경제3</div>
	<div>경제4</div>
</section>


<%--
	2. 정적 레이아웃
		1) 포함할 페이지에 변경되는 부분이 없음
		2) <%@ include%> 지시어를 사용
 --%>

<%@ include file="footer.jsp"%>