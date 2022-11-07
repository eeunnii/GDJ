<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
function fn1(){
	location.href = '${contextPath}/list.do';
}

</script>
</head>
<body>
	
	<h1>자유게시판 게시글 작성 화면</h1>
	<div>
		<form method="post" action="${contextPath}/insert.do" id="frm_free">
			<div>
				<label for="writer">작성자</label>
				<input type="text" id="writer" name="writer">
			</div>
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title">
			</div>
			<div>
				<label for="content">내용</label>
				<textarea id="content" name="content" rows="5" cols="30"></textarea>
			</div>
			<div>
				<input type="submit" value="작성완료">
				<input type="reset" value="다시작성">
				<input type="button" value="목록" id="btn_list" onclick="fn1();">
				
			</div>
		</form>
</body>
</html>