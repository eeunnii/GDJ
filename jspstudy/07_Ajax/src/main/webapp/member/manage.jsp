<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String contextPath = request.getContextPath(); 
	pageContext.setAttribute("contextPath", contextPath);
%>
<%--	▲▲▲▲ ${} 이걸로 안됨 ▲▲▲▲
	jsp 4개의 저장장소에 저장되어있지 않아서	
	jstl : 자바코드를 태그로 바꾸는거 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	// 자바스크립트에선 일케함(밑에 있는거랑 같은 역할을 수행함)
	// onload = function(){  }

	// 제이쿼리에선 일케함
	$(document).ready(function(){
		fn_init();
		fn_getAllMembers();
		fn_getMember();
		fn_registration();
		fn_modify();
		fn_remove();
		
	});
	
	//readonly 는 boolean타입, chedcked도 boolean타입
	function fn_init(){
		$('#id').val('').prop('readonly', false);
		$('#name').val('');
		$(':radio[name=gender]').prop('checked',false);
		$('#grade').val('');
		$('#address').val('');
		//$('#memberNo').val(resData.member.memberNo);  안됨
	}
	
	function fn_getAllMembers() {
		$.ajax({
			/* 요청 */
			type : "get", //포스트맨에서 get방식
			url : "${contextPath}/member/list.do",
			/*응답*/
			dataType : 'json',
			success : function(resData){
				console.log(resData);  // resData : {"count" : 3, "members" : [{},{},{}]}
				
				// 1. resData.count, resData['count'] 둘 다 "count" : 3 의미함
				$('#count').text(resData.count);
				
				// 2. member_list 영역 초기화
				$('#member_list').empty();
				
				
				// 3. resData.mombers : [{},{},{}]
				// $.each(배열, function(인덱스, 배열요소){})
				$.each(resData.members, function(i, member){
					var tr = '<tr>';
					tr += '<td>'+member.memberNo+'</td>';
					tr += '<td>'+member.id+'</td>';
					tr += '<td>'+member.name+'</td>';
					tr += '<td>'+(member.gender=='M'? '남자':'여자')+'</td>';
					tr += '<td>'+member.grade+'</td>';
					tr += '<td>'+member.address+'</td>';
					tr += '<td><input type="hidden" value="'+member.memberNo+'"><input type="button" value="조회" class="btn_detail"><input type="button" value="삭제" class="btn_remove"><input type="hidden" value="'+member.memberNo+'"></td>'; //id를 btn_detail로 하면 id 같은게 3번 나옴 // 클래스는 여러개 있어도 되니까 가능
					//$('#member_list').html(tr); //html은 기존의 내용을 덮어쓰기함 -- > 마지막으로 가져온 세번째 사용자밖에 안보여줌
					//뒤에있는 형제 next. 앞에있는 형재 prev() -- 선택자.
					tr += '</tr>';
					$('#member_list').append(tr);  // append로 하면 3,4,5로나옴?
	
				});
			}
			
		});
	}
	function fn_getMember(){
		
			// 조회 버튼은 동적 요소이기 때문에 다음 이벤트 방식을 사용해야한다.
			// https://github.com/goodeeit/GDJ54/blob/main/webstudy/JS/21_jquery.html참고하기
			// $(부모요소).on(이벤트타입, 이벤트대상, 이벤트리스너)
			
		$('body').on('click', '.btn_detail', function(){
			$.ajax({
				/* 요청 */
				type : 'get',
				url : '${contextPath}/member/detail.do',
				data : 'memberNo=' + $(this).prev().val(),
				/* 응답 */
				dataType : 'json',
				success : function(resData){ //resData : 깃허브참고 
					if(resData.exists){
						alert('회원 정보가 조회되었습니다.');
						$('#id').val(resData.member.id).prop('readonly', true);
						$('#name').val(resData.member.name);   // 둘의 차이 : 아이디 - 수정불가, 비번-수정가능
						$(':radio[name=gender][value='+resData.member.gender+']').prop('checked', true); // .val()는 value의 값을 바꿈 // value가 괄호 밖에 있으면 안됨
						$('#grade').val(resData.member.grade);
						$('#address').val(resData.member.address);
						$('#memberNo').val(resData.member.memberNo);
					}else{
						alert('조회된 회원 정보가 없습니다.');
					}
				}
			})
		});
	}
	
	
	/* insert와 update는 post로 진행한다. */
	function fn_registration(){
		$('#btn_add').click(function(){
			$.ajax({
				/*요청*/
				type : 'post',
				url : '${contextPath}/member/add.do',
				data : $('#frm_member').serialize(), /* serialize(): form내부의 모든 입력요소를 파라미터로 만들어 주는 애 = 서브밋과 동일하게 동작*/ 
				/*응답*/
				dataType : 'json',
				//정상 응답
				success : function(resData){	//resData : {"isSuccess" : true}
					if(resData.isSuccess){
						alert('신규회원이등록되었습니다.');
						fn_getAllMembers(); // 목록을 새로 가져와서 갱신함
						/* ajax안에 ajax를 부르면 promise가 올 수 있음 */
						fn_init(); // 입력된 데이터를 초기화
					}else{
						alert('신규회원등록이 실패햇읍니다.');
					}
				},
				//예외응답
				error : function(jqXHR){  // 예외 처리 응답 데이터(일반 텍스트)는 jqXHR객체의 responseText 속성의 저장됨
					alert(jqXHR.responseText);
				}
			});  //$.ajax
		});
	}  // function

	
	function fn_modify(){
		$('#btn_modify').click(function(){
			
			$.ajax({
				/*요청*/
				type : 'post',
				url : '${contextPath}/member/modify.do',
				data : $('#frm_member').serialize(),
				dataType: 'json',
				success : function(resData){ //resData : {"isSuccess" : true}
					if(resData.isSuccess){
						alert('회원 정보가 수정되었습니다.');
						fn_getAllMembers(); // 수정된 내용이 반영되도록 회원목록을 새로 고침
					}else{
						alert('회원 정보 수정이 실패했습니다.');
					}
				},
				error : function(jqXHR){
					alert(jqXHR.responseText);
					
					
				}
			});  //$.ajax(
	
		}); //$('btn_modify').click(function()
	
	} // fn_modify

	function fn_remove(){
		
		$('body').on('click', '.btn_remove', function(){
			
			console.log($(this).next().val());
			if(confirm('삭제할까요?')==false){
				return; // return은 코드 진행을 막음
			}
			$.ajax({
				/* 요청 */
				type : 'get',  //딜리트는 get
				url : '${contextPath}/member/remove.do',
				data :'memberNo=' + $(this).next().val(),
				/* 응답*/
				dataType : 'json',
				success : function(resData){
					if(resData.isSuccess) {
						alert('회원 정보가 삭제되었습니다');
						fn_getAllMembers()
						fn_init();
					}else {
						alert('회원 정보 삭제가 실패했습니다.');
					}
				},
				error : function(jqXHR){
					alert(jqXHR.responseText);
				
				}
		
			});
		});
		
	}
</script>
</head>
<body>

<div class="warp">
	<h1 class="title">회원관리</h1>
	<form id="frm_member">
	<div>
		<label for="id">아이디</label>
		<input type="text" id="id" name="id">
	</div>
	<div>
		<label for="name">이름</label>
		<input type="text" id="name" name="name">
	</div>
	<div>
		<label for="male">남자</label>
		<input type="radio" id="male" name="gender" value="M">
		<label for="female">여자</label>
		<input type="radio" id="female" name="gender" value="F">
	</div>
	<div>
		<label for="grade">회원등급</label>
		<select id="grade" name="grade">
			<option value="">등급선택</option>
			<option value="gold">골드</option>
			<option value="silver">실버</option>
			<option value="bronze">브론즈</option>
		</select>
	
	</div>
	<div>
		<label for="address">주소</label>
		<input type="text" id="address" name="address">
	</div>
	<div>
		<input type="button" value="초기화" id="btn_init" onclick="fn_init()"> 
		<input type="button" value="신규등록" id="btn_add"> 
		<input type="button" value="변경내용저장" id="btn_modify"> 
		<input type="button" value="회원삭제" class="btn_primary btn_remove"> 	
		<!-- 위 3줄은 ajax로 동작해야되서 button타입을 준거임. 서브밋할거 아니니까 -->
		<input type="hidden" id="memberNo"> 
		<!-- 먼 text도 된다고 햇던 것 같은D -->
	</div>

	<!-- Dao 에서 select(실무에서 find), insert(실무에서 add/regist), update(실무에서 save, delete) -->
	</form>
	<hr>
	<table class="member_table">
	 <caption>전체 회원수 : <span id="count"></span>명</caption>
		<thead>
			<tr>
				<td>회원번호</td>
				<td>아이디</td>
				<td>이름</td>
				<td>성별</td>
				<td>등급</td>
				<td>주소</td>
				<td></td>
			</tr>
		</thead>
		<tbody id="member_list"></tbody>
	</table>
</div>
	

</body>
</html>