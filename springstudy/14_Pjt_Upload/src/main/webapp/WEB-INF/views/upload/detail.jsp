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
	
	$(funtion(){
		// 첨부 삭제 
		$('.btn_attach_remove').click(function(){
			if(confrim('해당 첨부파일을 삭제할까요?')){
				location.href='${contextpath}/upload/attach/remove?attachNo='+$(this).data('upload_no') +$(this).data('attach_no');
			}
		});
	});
	


</script>
</head>
<body>

	<div>
		<h1>업로드 게시판 정보</h1>
		<ul>
			<li>제목 : ${upload.title}</li>
			<li>내용 : ${upload.content}</li>
			<li>작성일 : ${upload.createDate}</li>
			<li>수정일 : ${upload.modifyDate}</li>
		</ul>
	</div>
	
	<hr>
	
	<div>
		<h3>첨부목록</h3>
		<c:forEach items="${attachList}" var="attach">
			<div>
				<a href="${contextPath}/upload/download?attachNo=${attach.attachNo}">${attach.origin}</a><!-- 원래 파일 이름  -->
				<input type="button" value="삭제" id="btn_attach_remove" data-upload_no="${upload.uploadNo}" data-attach_no="${attach}">
			</div>
		
	
		
		<!-- 갤러리 추가 구현 : 게시글 상세보기에서 삭제버튼 누르면 게시글, 첨부파일 날림, 수정! -->
		
		<!-- 첨부목록은 ajax로 처리하는게 좋음
		 -->
		
		</c:forEach>
	</div>

	
	
	
	


	
</body>
</html>