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
							
							
						}else {
							if(comment.depth == 0){
								div += '<div>삭제된 댓글입니다.</div>';
							}else{
								div += '<div>삭제된 답글입니다.</div>';
							}
						}
					
						div +='<div>';
						moment.locale('ko-KR');
						div += '<span style="font-size:12px; color: silver;">' + moment(comment.createDate).format('YYYY.MM.DD hh:mm') + '</span>';
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
			$('enable_link').on('click', '')) // 깃허브 
			$('#page').val($(this).val}) //페이지번호로 바꿔서 이동하기?
			 	
			})
		}
		
		
	</script>
	
	
	
	
	


</div>

</body>
</html>