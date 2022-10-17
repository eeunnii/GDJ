<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.6.1.min.js"></script>
</head>
<body>

	<%--너비/높이 입력 폼:삼각형 버튼, 사각형 버튼--%>
	<form>
	너비 : <input type="text" name="width"> <br>
	높이 : <input type="text" name="height">
	<button id="btn1" name="btn1" onclik="fn1()"></button>
	<button id="btn2" name="btn2" onclik="fn2()"></button>
	
	</form>
	
	
	<%-- 반지름 입력 폼 : 원 버튼 --%>
	
	 반지름 입력 : 
	<form action="${contextPath}/radius.do">
		<input type="text" name="radius">
		<button>원 버튼</button>
	</form>
	
	
	<script>
	function fn1(){
		$("#btn1").onclick = function{
			
			
			
		}

	}	
	
	
	
	
	</script>
</body>
</html>