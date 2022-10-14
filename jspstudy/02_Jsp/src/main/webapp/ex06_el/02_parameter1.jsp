<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>
		<form action="02_parameter2.jsp" method="GET">
			<div>
				<input type="text" name="a">  <!-- a,b는 request에 저장되서 EL사용가능,  request저장되는거 속성, 파라미터 2개임-->
			</div>
			<div>
				<input type="text" name="b"> <!-- a,b는 속성아니고 파라미터임 -->
			</div>
			<div>
				<input type="submit" value="전송"> <!-- button태그와 같음 -->
			</div>
		</form>
	</div>

</body>
</html>