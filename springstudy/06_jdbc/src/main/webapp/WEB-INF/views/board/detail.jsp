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
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.css">
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.min.js"></script>
<script>
	$(document).ready(function(){
		var frm = $('$frm_remove');  /* $부를 때 마다 제이쿼리 호출하는거라 별로 안좋음. 코드개선이아니고 성능개선임 */ 
		
		// 편집화면으로 이동
		$('#btn_edit').click(funtion(){
			frm.atter('action', '${contextPath}/brd/edit');
		});
		
		
		
		//삭제 
		$('#btn_remove').submit(function(event){
			if(confirm('삭제할가요?')==true){
				$('#frm_btn').attr('action', '${contextPath}/brd/remove');
				$('#frm_btn').submit();
				return; // 코드 진행을 막음
			}
		});
		
		
		//목록
		$('#btn_list').click(function(){
			location.href='${contextPath}/brd/list';
		});
	});
</script>
</head>
<body>
	
	<ul>
		<li>글번호 : ${board.board_no}</li>
		<li>제목 : ${board.title}</li>
		<li>작성자 : ${board.writer}</li>
		<li>작성일 : ${board.create_date}</li>
		<li>수정일 : ${board.modify_date}</li>
	</ul>
	<div>
		${board.content}
	</div>
	<hr>
	
	<!-- 하나의 폼에 여러개의 submit이 들어갈 수 있다 -->
	<form method="post" id="frm_btn">
	<div>
		<input type="hidden" value="${board.board_no}" name="board_no">
		<input type="button" value="편집" id="btn_edit">
		<input type="button" value="삭제" id="btn_remove"> <!-- 서브밋-> button으로 수정함. 클릭이벤트로 가겠다는 뜻 -->
		<input type="button" value="목록" id="btn_list">
	</div>
	
	</form>

</body>
</html>