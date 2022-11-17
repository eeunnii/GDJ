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
<!-- ▼ https://cdnjs.com/libraries/jquery-cookie 에서 가져온 태그 ▼ -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js" integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>

	//JSESSIONID -- 
	
	//브라우저가 닫히면 세션이 닫힌거?? 재시작했을 때 session값은 다른 값을 가진다.
	// JSESSIONID는 쿠키에 들어가 있음 
	
	// 로그인 유지를 할 것이다 . JSESSIONID을 활용, 2군데에 저장해놓을거임
	// 1. cookie를 새로 만들어서 value저장
	// 2. 위에서 만든 쿠키를 DB에 저장한다.
	// 영구적이라고 할 순 없지만 꽤 오래 로그인 되어잇음
	// DB랑 cookie를 비교해서 로그인
	// cookie에 id, pw저장하는거 위험함.. 그래서 session의 id와 pw를 쓸거임 
	// DB에 session의 id와 pw를 저장. 
	// DB와 cookie의 session id, pw를 비교해서 일치하는 값이 있으면 로그인처리함
	

	$(function(){
		fn_login();
		fn_displayRememberId();
	});
	
	function fn_login(){
		
		$('#frm_login').submit(function(event){
			
			// 서브밋의 취소 사유 : 아디, 비번 미입력 시 
			if('#id').val() == '' || $('#pw').val()==''){
				alert('아이디와 패스워드를 모두 입력하세요 ');
				event.preventDefault(); // 서브밋을 막음 
				return; // 코드 진행을 막음
			}
			
			// 아이디 저장 누르면 아이디를 쿠키에 저장하는거임(클라이언트에 저장. 우리 pc에 저장. 서버에 저장X)
			
			if($('#rememberId').is(':checked')){
				$.cookie('rememberId',$('#id').val());   // 쿠키처리는 백엔드 말고 프론트에서도 할 수 있음 cookie(쿠키이름,값)
			} else {
				$.cookie('rememberId',''); //체크 안하면 빈문자열
			}
		
		
		});
		
		
	}
	
	function fn_displayRememberId(){
		
		// 자바에서는 response에 쿠키를 담아서 보냈었음
		
		let rememberId = $.cookie('rememberId');
		if(rememberId ==''){
			$('#id').val('');
			$('#rememberId').prop('checked', false);
		}else{
			$('#id').val(rememberId);
			$('#rememberId').prop('checked', true);	
		}
	}
</script>
</head>
<body>

	<div>
	
		<h1>로그인</h1>
		
		<form id="frm_login" action="${contextPath}/member/login" method="post">
		
			<input type="hidden" name="url" value="${url}"> 
			
			<div>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id">
			</div>
			
			<div>
				<label for="pw">비밀번호</label>
				<input type="password" name="pw" id="pw">
			</div>
			
			<div>			
				<button>로그인</button>
			</div>
			
			<div>			
				<label for="rememberId">
					<input type="checkbox" id="rememberId">
					아이디 저장
				</label>
				<label for="keepLogin">
					<input type="checkbox" name="keepLogin" id="keepLogin">  <!-- value전달X, 값이 있는지 없는지를 판단할거임  -->
					<!-- 체크박스에 name이 있어도 체크 안하고 보내면 파라미터 전달도 안감 -->
					로그인 유지
				</label>
			</div>
		
		</form>
			
		<div>
			<a href="${contextPath}/member/findId">아이디 찾기</a> | 
			<a href="${contextPath}/member/findPw">비밀번호 찾기</a>
		</div>
	
	</div>
	
</body>
</html>