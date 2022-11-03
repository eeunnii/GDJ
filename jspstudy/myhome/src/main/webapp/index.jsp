<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--		 https://jquery.com/ 접속 후 다운로드 제이쿼리 주확생 버튼 누름
				스크롤 내려서 Using jQuery with a CDN 로 옴
				하이퍼링크  https://code.jquery.com  클릭
					minified 클릭. 복사 여기다가 붙여넣기 하면 js파일 없어도 쿼리 사용 가능
					min.jsp가 min이 mini 엿음  -->
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
</head>
<body>



	<div>
	<a href="${contextPath}/notice/list.no">공지사항</a>
	</div>




	<c:if test="${login==null}">
	<div>
		<form method="POST" action="${contextPath}/member/login.me">
		<div>
			<input type="text" name="id" placeholder="아이디">
		</div>
		<div>
			<input type="password" name="pw" placeholder="패스워드">
		</div>
		<div>
			<button>로그인</button>
		</div>
		<div>
			<a href="${contextPath}/member/join.me">회원가입</a>
		
		</div>
		</form>
	</div>
	</c:if>
	
	<c:if test="${login!=null}">
		<div>
			${login.name}님 어서오세요
			<input type="button" value="로그아웃" onclick="location.href='${contextPath}/member/logout.me';">
		</div>
		<div>
		<a href="${contextPath}/member/cancel.me" id="cancel_link">회원탈퇴</a>
		<script>
			$('#cancel_link').click(function(event){
				if(confirm('탈퇴하시겠습니까?')==false){
					/* a링크의 기본Default 동작(href로 이동)을 막는다. */
					event.preventDefault();  // 
					return;
					
				}
			})
		</script>
		</div>
	</c:if>
</body>
</html>