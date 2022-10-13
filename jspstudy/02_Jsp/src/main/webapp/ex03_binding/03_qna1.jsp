<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- type="text/css" 이거 없어두댐 -->
<style type="text/css">
label {
display : block;
}
label span {
display : inline-block;
width : 100px;
}
</style>
</head>
<body>
<%--주소 : 컨텍스트까지 적어주면 webapp까지 옴 --%>
	
	<%
		Date date = new Date();
		String today = new SimpleDateFormat("yyyy-MM-dd").format(date);
		
		//today의 EL 사용을 위해서 속성으로 binding 시킴(자바변수는 EL 안됨)
		pageContext.setAttribute("today", today);
	%>
	
	<%-- 실무에선 contextpath 변수처리함 <%=request.getContextPath()%>--%> 
	
	<div>
		<form method="POST" action="/02_Jsp/ex03_binding/03_qna2.jsp">
			<label for="created_date">
				<span>작성일</span>
				<input type="text" name="created_date" id="created_date" value="${today}">
			</label>
			<label for="writer">
				<span>작성자</span>
				<input type="text" name="writer" id="writer">
			</label>
			<label for="title">
				<span>제목</span>
				<input type="text" name="title" id="title">
			</label>
			<label for="content">
				<span>내용</span>
				<input type="text" name="content" id="content">
			</label>
			<button>문의 남기기</button>
			<input type="reset" value="다시작성">
	</form>
	</div>
</body>
</html>