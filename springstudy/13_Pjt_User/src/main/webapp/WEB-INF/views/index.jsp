<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>

	<!-- 로그인이 안된 상태 -->
	<c:if test="${loginUser == null}">
		<a href="${contextPath}/user/agree">회원가입페이지</a>
		<a href="${contextPath}/user/login/form">로그인페이지</a>
	</c:if>
	
	<!-- 로그인이 된 상태  -->
	<c:if test="${loginUser != null}">
		<div>
			<a href="${contextPath}/user/mypage">${loginUser.name}</a>	님 반갑습니다.
		</div>
		<a href="${contextPath}/user/logout">로그아웃</a>
		<a id="lnk_retire" href="${contextPath}/user/retire">회원탈퇴</a> 
		
		<%-- #{userNo},#{id},#{joinDate}의 값은 session에 담겨있음 --%>
		
		<script>
		
		$('#lnk_retire').click(function(event){
			
			if(confirm('탈퇴하시겠습니까?') == false){
				event.preventDefault();  // 기본이벤트를 막음 -- location.href를 막음
										 // a 태그의 기본 이벤트인 href 속성 실행을 막음 
				return;
			}
		});
		
		</script>
	
	</c:if>


	
	
	


</body>
</html>