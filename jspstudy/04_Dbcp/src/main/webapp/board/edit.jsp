<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.board_no}번 게시글 수 수 수정 화 화화 면</title>
</head>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function(){
		
		$('#frm_edit').submit(function(event){
			//제목, 내용 모두 변경이 없는 경우 (서브밋을 하면 안되는 2가지)
			//기존, 제목 내용은 ${board.title}, ${board.content}
			//입력한 제목, 내용 : $('#title').val(), $('#content').val()
			if('${board.title}'== $('#title').val() && '${board.content}' == $('#content').val()){
				alert('변경된 내용이 없습니다');
				event.preventDefault();
				return;
			}
			
			
			// 제목이 비어 있는 경우 
			if($('#title').val()==''){
				alert('제목은 필수입니다.');
				event.preventDefault();
				return;
			}
			
		});
		
/* 		$('#btn_edit').click(function(event){
			location.href='${contextPath}/board/edit.do?board_no=${board.board_no}';
		}); */
		
		$('#btn_list').click(function(event){
			location.href = '${contextPath}/board/list.do';											
		});
	});

</script>
<body>
	<h1>게시글 수정화면</h1>
	<div>
		<form action="${contextPath }/board/modify.do" method="POST" id="frm_edit">

	
	<div>
		게시글 번호 : ${board.board_no}
		<input type="hidden" name="board_no" value="${board.board_no}"> <!-- 히든은 네임과벨류가 필수임 -->   
		<!-- BoardModifyService에서 받는 파라미터가 3개라서 갯수를 맞춰주기 위해 히든으로처리함 -->
		 <!-- null로 인식되는건 check, radio 2개임. 그냥 input요소들은 빈칸으로 보내면 빈 문자열로 넘어감 
		 	name 속성이 있기 때문임 -->
	</div>
	<div>
		게시글 제목 : <input type="text" name="title" id="title" value="${board.title}">
	</div>
	<div>
		게시글내용 <br>
		<textarea name="content" id="content" rows="5" cols="30">${board.content}</textarea>
	</div>
	<div>
		작성일자 : ${board.create_date}
	</div>
	<div>
		<input type="submit" value="수정" >
		<input type="button" value="목록" id="btn_list">
	</div>
		</form>
	</div>	
</body>
</html>