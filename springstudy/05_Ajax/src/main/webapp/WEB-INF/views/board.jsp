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
<script>
	$(document).ready(function(){
		fn_ajax1();
		fn_ajax2();
		fn_ajax3();
	});
	function fn_ajax1(){
		$('#btn1').click(function(){
			$('#result').empty();
			
			$.ajax({
				/*요청*/
				type : 'get',
				url : '${contextPath}/board/detail1',
				data : $('#frm_board').serialize();
				/*응답*/
				dataType : 'json'
				success : function(resData){
					$('<ul>')
					.append( $('<li>').text(resData.title) )
					.append( $('<li>').text(resData.content) )  // 자식 들어가는 자리
					.appendTo('#result')  // 부모 들어가는자리
					
				},
				error : function(jqXHR){
					$('#result').text(jqXHR.status);
				}
				
				
				/*응답*/
				
			});
			
			
			
		});
	}
	function fn_ajax2(){  /* 제목 입력 안하면 실패 */
		$('#btn2').click(function(){
			$('#result').empty();
			
			$.ajax({
				/*요청*/
				type : 'get',
				url : '${contextPath}/board/detail2',
				data : $('#frm_board').serialize(),
				/*응답*/
				dataType: 'json' ,
				success : function(resData){
					$('<ul>')
					.append($('<li>').text(resData.title))
					.append($('<li>').text(resDate.content))
					.appendTo('#result');
				},
				error : function(jqXHR){
					if(jqXHR.status == 500){
						alert('제목은 필수입니다.');
					}
				}
				
			});
			
			
			
		});
	}
	function fn_ajax3(){
		$('#btn3').click(function(){
			$('#result').empty();
			
			$.ajax({
				/*요청*/
				type : 'get'
				url : '${contextPath}/board/detail3',
				data : $('#frm_board').serialize(),
				/*응답*/
				dataType : 'json',
				success : funtion(resData){
					
				}
				
				
				/*응답*/
				
			});
			
			
			
		});
	}
	


</script>
</head>
<body>

	<form id="frm_board">
		
		<div>
			<label for="title">제목</label>
			<input type="text" name="title" id="title">
		</div>
		
		<div>
			<label for="content">내용</label>
			<input type="text" name="content" id="content">
		</div>
		
		<div>
			<input type="button" value="전송1" id="btn1">
			<input type="button" value="전송2" id="btn2">
			<input type="button" value="전송3" id="btn3">
		</div>
		
	</form>
		
		<hr>
		
		<div id="result"></div>

</body>
</html>