<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<jsp:include page="../layout/header.jsp">
   <jsp:param value="블로그작성" name="title"/>
</jsp:include>


<script>
	
	/* 자바스크립트에서 c:set 을 대체하는 contextpath 만들기 */
	
	function getContextPath(){
		//console.log(location); 확인용
		var begin = location.href.indexOf(location.origin) + location.origin.length;
		var end = location.href.indexOf("/", begin+1);
		/* 찾는 값 지정, 어디서 부터 찾을지 정한거임 */
		return location.href.substring(begin, end);
	}
	
	
	
	$(document).ready(function(){
		
		getContextPath();
		
		// summernote
		$('#content').summernote({
			width: 800,
			height: 400,
			lang: 'ko-KR',
			toolbar: [
			    // [groupName, [list of button]]
			    ['style', ['bold', 'italic', 'underline', 'clear']],
			    ['font', ['strikethrough', 'superscript', 'subscript']],
			    ['fontsize', ['fontsize']],
			    ['color', ['color']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']],
			    ['insert', ['link', 'picture', 'video']]
			],
			callback: {
				// 편집기에 이미지를 로드할 때 이미지는 function의 매개변수 files로 전달됨
				
				onImageUpload : function(files){
					// 이미지를 ajax를 이용해서 서버로 보낼 때 가상 form 데이터를 사용함
					var formData = new FormData();
					formData.append('file', files[0]); // 파라미터 file, 서머노트 편집기에 추가된 이미지가 files[0]임
					// 에이작스 처리로 이미지를 하드로 보내고 그 경로를 받아올거임
					
					// 이미지를 HDD에 저장하고 경로를 받아오는
					$.ajax({
						type: 'post',
						url : getContextPath() + '/blog/uploadImage', 
						data : formData,
						contentType : false, // ajax이미지 첨부용
						processData : false, // ajax이미지 첨부용
						dataType: 'json', // HDD에 저장된 이미지의 경로를 json으로 받아옴
						success : function(resData){
							$('#content').summernote('insertImage', 이미지경로);
							
							/*
								src=${contextPath}/load/image/aaa.jpg값이 넘어온 경우
								 summernote는
								 <img scr="${contextPath}/load/image/aaa.jpg"> 태그를 만든다
								 
								 스프링에서 정적 자원을 표시하는 방법은  servlet-context.xml에 있다.
								 mapping=${contextpath}/load/image/aaa.jpg인 파일의 
								 location=C:\\upload\\aaa.jpg
								 
								  스프링에서 정적 자원 표시하는 방법은 servlet-context.xml에 있다.
								  이미지(정적자원_)의 mapping과 location을 servlet-context.xml에 작성해야한다
							*/
							
							/* 업로드가 톰캣옆에 생김?????? */
							
						}
						
						
						
					}); // ajax
				} // onImageUpload
			}  // callbacks
			
		});
		
		// 목록
		$('#btn_list').click(function(){
			location.href = getContextPath()+'${contextPath}/brd/list';
		});
		
		// 서브밋
		$('#frm_write').submit(function(event){
			if($('#title').val() == ''){
				alert('제목과 필수입니다.');
				event.preventDefault();  // 서브밋 취소
				return;  // 더 이상 코드 실행할 필요 없음
			}
		});
		
	});
	
</script>

<%--위 스크립트를 별도 파일로 분리하면 ${contextPath}에서 문제발생함 --%>
</head>
<body>

	<div>
		<h1>작성 화면</h1>
		
		<form id="frm_write" action="${contextPath}/blog/add" method="post">
			<div>
				<label for="title">제목</label>
				<input type="text" name="title" id="title">
			</div>
			<div>
				<label for="writer">작성자</label>
				<input type="text" name="writer" id="writer">
			</div>
			<div>
				<label for="content">내용</label>
				<textarea name="content" id="content"></textarea>
				<!-- 서버노트로 바뀌는 부분 -->				
			</div>
			<div>
				<button>작성완료</button>
				<input type="reset" value="입력초기화">
				<input type="button" value="목록" id="btn_list">
			</div>
		</form>
	</div>

</body>
</html>