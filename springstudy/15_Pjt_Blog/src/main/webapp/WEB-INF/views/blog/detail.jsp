<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<jsp:include page="../layout/header.jsp">
	<jsp:param value="${blog.blogNo}번 블로그" name="title"/>
</jsp:include>

<style>
 .blind{
 display:none;
 }
</style>

<div>

	<h1>${blog.title}</h1>
	
	<div>
		<span>▷ 작성일 <fmt:formatDate value="${blog.createDate}" pattern="yyyy. M. d HH:mm" /></span>
		&nbsp;&nbsp;&nbsp;
		<span>▷ 수정일 <fmt:formatDate value="${blog.modifyDate}" pattern="yyyy. M. d HH:mm" /></span>
	</div>
	
	<div>
		<span>조회수 <fmt:formatNumber value="${blog.hit}" pattern="#,##0" /></span>
	</div>
	
	<hr>
	
	<div>
		${blog.content}
	</div>
	
	<div>
		<form id="frm_btn" method="post">
			<input type="hidden" name="blogNo" value="${blog.blogNo}">
			<input type="button" value="수정" id="btn_edit_blog">
			<input type="button" value="삭제" id="btn_remove_blog">
			<input type="button" value="목록" onclick="location.href='${contextPath}/blog/list'">
		</form>
		<script>
			$('#btn_edit_blog').click(function(){
				/* 블로그 번호를 폼에 넣어야 서브밋할 때 감 */
				$('#frm_btn').attr('action', '${contextPath}/blog/edit');
				$('#frm_btn').submit();
			});
			
			$('#btn_remove_blog').click(function(){
				if(confirm('블로그를 삭제하면 블로그에 달린 댓글을 더 이상 확인할 수 없습니다. 삭제하시겠습니까?')){
					$('#frm_btn').attr('action', '${contextpath}/blog/remove');
					/* post로 하는 이유 : 주소로 직접 접속하는거 막을려고 */
					$('#frm_btn').submit();
				}
			});
			
			
			
		</script> 
	</div>
	
	
	<hr>
	
	
	<span id="btn_comment_list">
		댓글
		<span id="comment_count"></span>개
	</span>
	
	<hr>
	
	<div id="comment_area" class="blind">
		<div id="comment_list"></div>
		<div id="paging"></div>
	</div>
	
	
	
	<!-- 댓글 다는 곳은 정적(?)으로 만들어줬음. 답글은 입력창이 별도로 나오게 만들어야함 -->
	
	<div>
		<form id="frm_add_comment">
			<div class="add_comment">
				<div class="add_comment_input">
					<input type="text" name="content" id="content" placeholder="댓글을 작성하려면 로그인 해 주세요">
				</div>
				<div class="add_comment_btn">
					<input type="button" value="작성완료" id="btn_add_comment">
				</div>
			</div>
			<input type="hidden" name="blogNo" value="${blog.blogNo}">
		</form>
	</div>
	
	<!-- 현재 페이지 번호를 저장하고 있는 hidden -->
	<input type="hidden" id="page" value="1">
	
	<script>
	
		//함수 호출
		fn_commentCount();
		fn_addComment();
		fn_commentList();
		fn_changePage();
		fn_switchCommentList();
		fn_removeComment();
		fn_switchReplyArea();
		
		
		// 전역 변수 page(모든 함수에서 사용가능)
		var page=1;
		
		
		function fn_commentCount(){
			$.ajax({
				type: 'get',
				url : '${contextPath}/comment/getCount',
				data : 'blogNo=${blog.blogNo}',
				dataType: 'json',
				success: function(resData){  // resData={"commentCount" : 개수}
					$('#comment_count').text(resData.commentCount);
				}
			});
		}
		
		
		
		function fn_switchCommentList(){
			/* css까지 보고 해야함 */
			$('#btn_comment_list').click(function(){
				$('#comment_area').toogleClass('show');
			});
		}
		
		
		function fn_addComment(){
			$('#btn_add_comment').click(function(){
				if($('#comment').val() == ''){
					alert('댓글 내용을 입력하세요');
					return;
				}
				$.ajax({
					type: 'post',
					url: '${contextPath}/comment/add',
					data: $('#frm_add_comment').serialize(),
					dataType: 'json',
					success: function(resData){  // resData = {"isAdd", true}
						if(resData.isAdd){
							alert('댓글이 등록되었습니다.');
							$('#content').val('');
							fn_commentList();   // 댓글 목록 가져와서 뿌리는 함수
							fn_commentCount();  // 댓글 목록 개수 갱신하는 함수
						}
					}
				});
			});
		}
		
		function fn_commentList(){
			$.ajax({
				type : 'get',
				url : '${contextPath}/comment/list',
				data : 'blogNo=${blog.blogNo}&page='+page, // page도 넘겨줘야함
				dataType : 'json',
				success : function(resData){
					/*
						resDate = {
						 	"commentList" : {
						 		{댓글하나},
						 		{댓글하나},
						 		......
						 	}
							"pageUtil" : {
								page : x,
								....
							}
						}
					
					*/
					
					// 화면에 댓글 목록 뿌리기 
					$('#comment_list').empty();
					$.each(resData.commentList, function(i, comment){
						var div = '';
						if(comment.depth == 0){
							div +='<div>';
						}else{
							div +='<div style="margin-left : 40px;">';
						}
						div += '<div>';
						if(comment.stat == 1){
							/* 1이면 정상 */
							div += '<div>'+comment.content+'</div>';
							// 작성자만 삭제할 수 있도록 if 처리 필요
							div += '<input type="button" value="삭제" class="btn_comment_remove" data-comment_no="' + comment.commentNo + '">';
							// 댓글만 답글을 달 수 있도록 if문처리 필요함
							div += '<input type="button" value="답글" class="btn_reply_area" data-group_no="' + comment.commentNo + '">';
							
							// 그룹의 No을 알아야 댓글을 달 수 있다 ????
							
							// dept 0 이면 댓글이고 1이면 답글입
							
							
						}else {
							if(comment.depth == 0){
								div += '<div>삭제된 댓글입니다.</div>';
							}else{
								div += '<div>삭제된 답글입니다.</div>';
							}
						}
						/* 반복문에서 id사용은...금지! */
						div +='<div>';
						moment.locale('ko-KR');
						div += '<span style="font-size:12px; color: silver;">' + moment(comment.createDate).format('YYYY.MM.DD hh:mm') + '</span>';
						div += '</div>';
						div += '<div class="relpy_area blind">'; 
						/* blind클래스 값을 toggle로 줘서 들어갔다 나왔다하게 만들기 */
						div += '<form class="frm_reply">'; 
						div += '<input type="hidden" name="blogNo" value="' + comment.blogNo + '">';
						div += '<input type="hidden" name="groupNo" value="' + comment.commentNo + '">';
						div += '<input type="text" name="content" placeholder="답글을 작성하려면 로그인을 해주세요">';
						// 로그인한 사용자만 볼 수 있도록 if 처리
						div += '<input type="button" value="답글작성완료" class="btn_reply_add_form">'
						div += '</form>';
						div += '</div>';
						div += '</div>';
						$('#comment_list').append(div);
						$('#comment_list').append('<div style="border-bottom: 1px dotted solid gray;"</div>');
						
					});
					// 페이징
					$('#paging').empty();
					var pageUtil = resData.pageUtil;
					var paging='';
					// 이전 블록
					if(pageUtil.beginPage !=1){
						paging += '<span class="enale_link" data-page="'+(pageUtil.beginPage-1)+'">◀</sapn>';
					}
					//에이작스에서 페이지를 파라미터로 넘기는걸 하면 안됨
					//태그에 몇 페이지로 갈건지 페이지수를 여기다가 넣어놓을거임?
					for(let p = pageUtil.beginPage; p<=pageUtil.endPage; p++){
						if(p == page){
							paging += '<strong>' + p + '</strong>';
							
						}else{
							paging +='<span class="enale_link" data-page="'+p+'">' +p +'</span>';
						}
					}
					// 다음 블록
					if(pageUtil.endPage != pageUtil.totalPage){
						paging += '<span class="enale_link" data-page' + (pageUtil.endPage+1) +'"▶</span>";
					}
					$('#paging').append(paging);
					
					
					
				}
			});  // $.ajax
		}  // fn_commentList()
		
		
		/* 데이처 페이지갑ㅅ을 가져와서 ㅏ꿔주고 페이지  */
		
		
	function fn_chacgePage(){//만들어져 있었던 부모, on 
			// 동적요소 우리가 만든 이벤트는 신경써서 해줘야함(?)
			// 동적요소여서 일반 이벤트가 안걸림
			$(document).on('click', '.enable_link', function(){
				$('#page').val( $(this).data('page') );//페이지번호로 바꿔서 이동하기?
				fn_commentList();
			});
		}
		
		function fn_removeComment(){
			/* 원래 있는 버튼이 아니고 자바스크립트로 만든 동적 버튼이라 이벤트가 안걸림 */
			$(document).on('click', '.enable_link', function(){
				if(confirm('삭제된 댓글은 복구할 수 없습니다. 댓글을 삭제할까요?')){
					$.ajax({
						type : 'post',
						url : '${contextPath}/comment/remove',
						data : 'commentNo=' + $(this).data('comment_no'),
						dataType : 'json',
						success: function(resData){  // resData={ "isRemove" : true }
							if(resData.isRemove){
								alert('댓글이 삭제되었습니다.');
								fn_commentList();
								fn_commentCount();
							}
						}
					});
					
					
				}
			}
			
		}
		
		
		
		
		
 		/* 삭제버튼은 작성자만 삭제할 수 있게 구현해야함 session에 isUser올려두기 */
 		/* 갤러리 구현의 쿼리문은 사용자 테이블과의 조인이 필요할 수 있음 */
 		
 		
 		function fn_switchReplyArea(){
 			$(document).con('click', .'.btn_reply_area', function(){
 				$(this).parent().next().next().toggleClass('blind');
 			});
 			
 			
 			
 			
 			
 		}
 		
 		function fn_addReply(){
 			$(document).on('click', '.btn_reply_add', function(){
 				if($(this).prev().val() == ''){
 					alert('답글 내용을 입력하세요');
 					 return;
 				}
 				$.ajax({
 					type : 'post',
 					url : '${contextPath}/comment/reply/add',
 					data : $(this).closeat('.frm_reply').serialize(),  // 이건 안됨 $('.frm_reply')
 					dataType: 'json',
 					success: function(resData){  // resData = {"isAdd", true}
 						if(resData.isAdd){
 							alert('답글이 등록되었습니다.');
							fn_commentList(); // 목록갱신
							fn_commentCount();	// 갯수갱신				
 						}
 						
 					}
 				
 				});
 			}
 		}
 		
 		
 		
 		/* 댓글은 commentNo랑 groupNo랑 같음 */
 		/* 1단으로만 하기로 해서 댓글에는 답글버튼이 있는데 답글에는 답글버튼이 없음 */
		/* 정상댓글의 갯수만 세겠다 == 쿼리문짜야함. state=1 인것만 세면 됨 */
		
		/* 댓글정렬은 쿼리문에서 짜야함 */
		
		/* 사용자와 JOIN해서 유저이름가져오는거 연습해보기 */
	</script>
	
	
	
	
	
	


</div>

</body>
</html>