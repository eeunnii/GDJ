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
			2) session     : HttpSession과 같은 영역
			3) request     : HttpServletRequest와 같은 영역
			4) pageContext : 하나의 Jsp 페이지에서 접근 가능한 영역
	--%>

	<%-- 표현식으로 속성 접근 --%>
	<%
		application.setAttribute("a", 1); // 애플리케이션 종료 전까지 유지
		session.setAttribute("b",2); // 브라우저 닫기 전까지 유지
		request.setAttribute("c", 3);	// 하나의 요청&응답 사이클 내에서 유지
		pageContext.setAttribute("d", 4);	// 현재 페이지에서만 유지
	%>


	<%-- 포워드 : request 정보를 전달하는 이동 방식, 리다이렉트로 하면 a랑 b만 보임 --%>
	<%--
		request.getRequestDispatcher("02_scope2.jsp").forward(request, response);	
	--%>
	
	<%-- 포워드 (액션) 태그--%>
	<jsp:forward page="02_scope2.jsp"></jsp:forward>
	 
	 
	<%-- 리다이렉트 : request 정보를 전달하지 않는 이동 방식 --%>
	<%--
		response.sendRedirect("/02_Jsp/ex03_binding/02_scope2.jsp");
	--%>
	







</body>
</html>