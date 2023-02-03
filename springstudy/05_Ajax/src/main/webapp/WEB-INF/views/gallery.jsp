<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>

<h1>예쁜 동물들 보고 가세요 </h1>

<!-- 절대 경로의 이미지를 img 태그로 표시하기
	1. 요청
		1)경로+이미지를 파라미터로 전송해서 요청한다
	2. 응답
		모든 파일이 컴퓨터에 저장된 모습은 하나다 . 자바에서 인식하는 최소단위는 바이트임
		그림도 바이트로 저장되어잇음 . 바이트 배열로 받아오면 됨..
		
		이미지의 byte[] 바이트 배열을 받아오면 됨

 -->
<!--<img src="{contextPath}/image/display?path=C:\GDJ\images&filename=animal1.jpg" width="200px">   주소가 파라미터로 안넘어감. 404오류뜸 -->



<div id="galleries"></div>


<script>


	for(let n=1; n<=10; n++){
		
		$('<img>')
		.attr('src','${contextPath}/image/display?path='+encodeURIComponent('C:\\GDJ\\images')+'&filename=animal'+n+'.jpg')
		.attr('width', '200px')
		.appendTo('#galleries');
	}


//	$('#image').attr('src','${contextPath}/image/display?path='+encodeURIComponent('C:\\GDJ\\images')+'&filename=animal1.jpg')               /* attr, prop 가능 */
	
	/* filename은 항상 인코딩이 필요없는 수준으로 바꿔서 저장하기 때문에 파일네임을 인코딩하면 시스템이 잘못만들어진거임..? */
</script>





</body>
</html>