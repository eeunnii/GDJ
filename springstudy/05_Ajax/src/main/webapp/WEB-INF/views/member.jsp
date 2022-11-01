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
<style>
#frm_member {
	width : 480px;
	margin : 0auto;
}
label {
	display : inline-block;
	width : 80px;
}
</style>
<script>
	$(document).ready(function(){
		$('#btn1').click(function(){fn_ajax1();} );
		$('#btn2').click(function(){fn_ajax2();} );
		$('#btn3').click(function(){fn_ajax3();} );
		$('#btn4').click(function(){fn_ajax4();} );
	});
	
	function fn_ajax1(){
		$('#result').empty();
		$.ajax({
			/*요청*/
			type : 'get',
			url : '${contextPath}/member/detail1',
			data : 'id=' + $('#id').val()+'&pw='+$('#pw').val(),
			/*응답*/
			dataType : 'text',
			success: function(resData){
				$('#result').text(resData);
				
			},
			error : function(jqXHR) {
				$('#result').text(jqXHR.responseText);
			}
		});
	}
	function fn_ajax2(){
		$('#result').empty;
		
		$.ajax({
			/*요청*/
			type : 'get',
			url : '${contextPath}/member/detail2',
			data : $('#frm_member').serialize(), /* 이게 뭐더라 파라미터로만들어주는거 */ 
			/*응답*/
			dataType : 'json',
			success : function(resData){
				var ul = '<ul>';
				ul +='<li>'+resData.id+'</li>';
				ul += '<li>' + resData.pw + '</li>';
				ul += '</ul>';
				$('#result').html(ul);
			},
			error : function(jqXHR){
				$('#result').text(jqXHR.responseText);
				
			}
		
		})
		
	}
	function fn_ajax3(){
		$('#result').empty();
		$.ajax({
			type : 'get',
			url : '${contextPath}/member/detail3',
			data : $('#frm_member').serialize(),
			/*응답*/
			dataType : 'json',
			success : function(resData){
				var ul = '<ul>';
				ul +='<li>'+resData.id+'</li>';
				ul += '<li>' + resData.pw + '</li>';
				ul += '</ul>';
				$('#result').html(ul);
			}
			//에러처리는 생략~
		});
	} // ajax
	
	function fn_ajax3() {
		$('#result').empty();
		
		$.ajax({
			/*요청*/
			url: '${contextPath}/member/detail4',
			//JSON 데이터를 서버로 보낼 때 반드시 post 방식을 사용해야함
			type :'post';
			//data에 파라미터가 없음을 주의! 
			//파라미터로 전달되지 않기 때문에 주소창을 이용한 get방식이 불가능하다.  // 그래서 type : post임
			// 전체데이터에 mine type을 적어서 보내야함
			data : JSON.stringify({
				'id' : $('#id').val(),
				'pw' : $('#pw').val(),  // js의 객체 
			// 서버로 보내는 json데이터의 mine-type을 작성해준다.
			contextType : 'application/json',
			/*응답*/
			dataType : 'json',
			success : function(resData){
				var ul = '<ul>';
				ul +='<li>'+resData.id+'</li>';
				ul += '<li>' + resData.pw + '</li>';
				ul += '</ul>';
				$('#result').html(ul);
			}
		})
			
			
			
		
		
		
		
		
		
		}); // ajax
	}
	
</script>
</head>
<body>

		<form id="frm_member">
		<div>
			<label for="id">아이디</label>
			<input type="text" name="id" id="id"/>
		</div>
		<div>
			<label for="id">패스워드</label>
			<input type="password" name="pw" id="pw"/>
		</div>
		
		<div>
			<input type="button" value="전송1" id="btn1">
			<input type="button" value="전송2" id="btn2">
			<input type="button" value="전송3" id="btn3">
			<input type="button" value="전송4" id="btn4">
		</div>
		
		</form>
		
		<hr>
		
		<div id="result"></div>

</body>
</html>