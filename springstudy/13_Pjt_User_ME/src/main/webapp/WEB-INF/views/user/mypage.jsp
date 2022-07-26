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
<script>
	

	$(function(){
		fn_showHide();
		fn_init();
		fn_pwCheck();
		fn_pwCheckAgain();
		fn_pwSubmit();
	});
	
	var idPass = false;
	var pwPass = false;
	
	function fn_showHide(){
		$('#modify_pw_area').hide();
		$('#btn_edit_pw').click(function(){
			fn_init();
			$('#modify_pw_area').show();
		}); // 클릭하면 다시 보여달라!!
		
		$('#btn_edit_pw_cancel').click(function(){  // 취소버튼 클릭하면 다시 닫힌다
			fn_init();
			$('#modify_pw_area').hide();
		});
		
	}
	
	function fn_init(){
		$('#pw').val('');
		$('#re_pw').val('');
		$('#msg_pw').text('');
		$('#msg_re_pw').text('');
		
	}
	
	// 2. 패스워드
	function fn_pwCheck(){
		
		$('#pw').keyup(function(){
			
			// 입력한 패스워드
			let pwValue = $(this).val();
			
			// 정규식(8~20자, 소문자+대문자+숫자+특수문자8종(!@#$%^&*) 3개 이상 조합)
			let regPw = /^[0-9a-zA-Z!@#$%^&*]{8,20}$/;
			
			// 3개 이상 조합 확인
			let validatePw = /[0-9]/.test(pwValue)        // 숫자가 있으면 true, 없으면 false
			               + /[a-z]/.test(pwValue)        // 소문자가 있으면 true, 없으면 false
			               + /[A-Z]/.test(pwValue)        // 대문자가 있으면 true, 없으면 false
			               + /[!@#$%^&*]/.test(pwValue);  // 특수문자8종이 있으면 true, 없으면 false
			
			// 정규식 및 3개 이상 조합 검사
			if(regPw.test(pwValue) == false || validatePw < 3){
				$('#msg_pw').text('8~20자의 소문자, 대문자, 숫자, 특수문자(!@#$%^&*)를 3개 이상 조합해야 합니다.');
				pwPass = false;
			} else {
				$('#msg_pw').text('사용 가능한 비밀번호입니다.');
				pwPass = true;
			}
			               
		});  // keyup
		
	}  // fn_pwCheck
	
	// 3. 패스워드 확인
	function fn_pwCheckAgain(){
		
		$('#re_pw').keyup(function(){
			
			// 입력한 패스워드 확인
			let rePwValue = $(this).val();
			
			// 패스워드와 패스워드 재입력 검사
			if(rePwValue != '' && rePwValue != $('#pw').val()){
				$('#msg_re_pw').text('비밀번호를 확인하세요.');
				rePwPass = false;
			} else {
				$('#msg_re_pw').text('');
				rePwPass = true;
			}
			
		});  // keyup
		
	}  // fn_pwCheckAgain
	
	function fn_pwSubmit(){
		$('#fn_pwSubmit').submit(function(event){
			if(pwPss == false || rePwPass == false){
				alert('비밀번호 입력을 확인하세요');
				event.preventDefault();
				return;
			}
		})
	}


</script>
</head>
<body>
	
	
		<h1>마이페이지</h1>
	
		<div>
			<input type="button" value="비밀번호변경" id="btn_edit_pw" class="evn_hidden">
		</div>
		
		<div>
			<form action="${contextPath}/user/modify/pw" method="post">
		
		<!-- 패스워드 -->
		<div id="modify_pw_area">
			<label for="pw">패스워드*</label>
			<input type="password" name="pw" id="pw">
			<span id="msg_pw"></span>
		</div>
		
		<!-- 패스워드 재확인 -->
		
		<div>
			<button>비밀번호 변경하기 </button>
			<input type="button" value="취소하기" id="btn_edit_pw_cancel">
		</div>
		
		<!-- 로그인을 해야만 정상적으로 사용가능. 복구 안하면 새벽 1시에..? 무슨말씀이시지
			그게 싫으면.. 로그인한걸로 가져가고 날짜를 강제로 넣음..?
			복구를 했는데 로그인안하면 휴먼계정으로 보냄
		 -->
		
	</form>
	</div>
	

</body>
</html>