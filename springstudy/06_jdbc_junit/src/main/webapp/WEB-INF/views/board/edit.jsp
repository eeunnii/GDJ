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
		
		// summernote
		$('#content').summernote({
			/* 객체의 형태로 데이터를 넣을 수 있음  */
			width:800,
			height: 400,
			lang: 'ko-KR',
			  toolbar: [
				    // [groupName, [list of button]]
				    ['style', ['bold', 'italic', 'underline', 'clear']],
				    ['font', ['strikethrough', 'superscript', 'subscript']],
				    ['fontsize', ['fontsize']],
				    ['color', ['color']],
				    ['para', ['ul', 'ol', 'paragraph']],
				    ['height', ['height']]
				  ]
		}); // ('#content').summernote
		
		// 목록
		$('#btn_list').click(function(){
			location.href = '${contextPath}/brd/list';
		});  // $('#btn_list').click(function
		
		// 서브밋 
		$('#frm_board').submit(funtion(event){
			if($('#title').val()==''){
				alert('제목은 필수입니다');
				event.preventDefault(); // 서브밋 취소 
				return; // 더 이상 코드진행X
			}
		});
	}); // $(document).ready(function()
	
	
	
	
</script>
</head>
<body>
<div>
<h1>수정화면</h1>
<form action="${contextPath}/brd/edit" method="POST" id="frm_board">
<input type="hidden" name="board_no" value="${board.board_no}">
	<div>
		<label for="title">제목</label>		
		<input type="text" name="title" id="title" value="${board.title}">
	</div>
	<div>
		<label for="write">작성자</label>		
		<input type="text" name="write" id="write" value="${board.writer}">
	</div>
	<div>
		<label for="editordata">내용</label>		
		<textarea id="content" name="editordata"></textarea>
	</div>
	<div>
		<button>수정완료</button>	
		<input type="reset" value="입력초기화">
		<input type="button" value="목록" id="btn_list">
	</div>
</form>
</div>
	
</body>
</html>