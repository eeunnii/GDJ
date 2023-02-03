<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 로그인 성공 시 session에 id, pwd 저장된 상태임 --%>
	<%
		Object id = session.getAttribute("id");
		Object pwd = session.getAttribute("pwd");
	%>
	
		<%--${id}님 환영합니다. 의 id는 session의 id임. 자바변수 EL사용X, session은 서버 메모리에 저장됨 --%>
	<%
		if(id != null && pwd !=null){ %>
			${id}님 환영합니다&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="로그아웃" id="btn_logout">
	<% } else { %>
			<div>
				<form action="02_login.jsp" method="POST">
					<div>
						<input type="text" name="id" placeholder="아이디">
					</div>
					<div>
						<input type="password" name="pwd" placeholder="비밀번호">
					</div>
					<div>
						<button>로그인</button>
					</div>
				</form>
			</div>
	<% }  %>
	
	<script type="text/javascript">
		document.getElementById('btn_logout').onclick = function(event){
			location.href = '03_logout.jsp';
		}
	
	
	
	
	
	
	</script>

</body>
</html>