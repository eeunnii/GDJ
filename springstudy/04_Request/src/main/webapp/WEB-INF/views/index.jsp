<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src=""></script>
<script src="resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
	<img src="${contextPath}/resources/images/hooray.jpg" width="200px">
	<hr>
	
	<h3>Member 관련 요청</h3>
	
	<div>
	<a href="${contextPath}/member/detail1?id=admin&pw=1234">전송</a> <!-- 물음표뒤는 파라미터, ${contextPath}/member/detail1은 url매핑 -->
	</div>
	
	
	   <!--  스크립트의 로케이션을 이용하는 요청방법  -->
   <div>
      <input type="button" value="전송" id="btn">
   </div>
	
	<script>
	$('#btn').click(function(){
		// location.href='${contextPath}/member/detail2?id=admin&pw=1234';
		// location.href='${contextPath}/member/detail2';
		
		// location.href='${contextPath}/member/detail3?id=admin&pw=1234';
		location.href='${contextPath}/member/detail3';
	});
	/* location은 GET방식과 같음 */
	
	</script>
	

	
	
	
<!-- 폼의 액션을 이용한 요청 get인지 post인지 구분해서 실행하기-->
	<form action="${contextPath}/member/detail4" method="post">
		<div>
			<input type="text" name="id" palceholder="아이디">
		</div>
		<div>
			<input type="text" name="pw" palceholder="패스워드">
		</div>
		<button>전송</button>
	</form>
	
	<hr>
	
	<div>
		<a href="${contextPath}/board/detail1?title=공지사항&hit=10">전송</a>
	</div>
	<div>
		<a href="${contextPath}/board/detail3?title=공지사항&hit=10">전송</a>
	</div>

</body>
</html>