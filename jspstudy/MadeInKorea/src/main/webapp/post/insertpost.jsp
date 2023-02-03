<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새글작성화면</title>
</head>
<body>	
	<form id="frm_write" action="${contextPath}/post/insert.do" method="POST">
	
	
	<table border="1">
<tbody>
	<tr>
		<td>작성자</td>
		<td width="300px"><input type="text" name="witer"></td>
	</tr>
	<tr>
		<td>제목</td>
		<td><input type="text" name="title"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td height="200px"><textarea id="content" name="content" rows="5" cols="30"></textarea></td>
	</tr>	
	<tr>
		<td colspan="2"><button>등록</button><input type="button" value="목록"></td>
	</tr>
	</tbody>
</table>
	
	</form>
	
	

</body>
</html>