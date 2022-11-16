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
<script>
	$(function(){
		fn_getlist();
		fn_enroll();
		fn_serch();
	});
	function fn_getlist(){
		$.ajax({
			type : 'get',
			url : '${contextPath}/list.json',
			dataType : 'json',
			success: function(resData){
				$.each(resData, function(i, staff){
					
					// 텍스트 만들기
					var tr= '<tr>';
					tr +='<td>'+staff.sno+'</td>';
					tr +='<td>'+staff.name+'</td>';
					tr +='<td>'+staff.dept+'</td>';
					tr +='<td>'+staff.salary+'</td>';
					tr += '</tr>';
					$('#stafflist').append(tr);
					
					/*
					위 아래 같은거임 
					$('<tr>')
							.append(($'<td>').text(staff.no))
							.append(($'<td>').text(staff.name))
							.append(($'<td>').text(staff.dept))
							.append(($'<td>').text(staff.salary))
							.appendTo($('#stafflist'));
					*/
		
				});
			}
		});
	}
	
	function fn_enroll(){
		$('#b1').click(function(){
			
			/* 사원번호는 숫자 다섯자리만 가능함 */
			if(/^[0-9]{5}$/.test($('#sno').val())==false){
				alert('사원번호는 5자리 숫자입니다.');
				return;
			}
			
			if(/^[가-힣]{3,5}$/.test($('#dept').val())==false){
				alert('부서는 3~5자리 한글입니다.');
				return;
			}
			
			$.ajax({
				type : 'POST',
				url : '${contextPath}/add',
				data : $('#staff').serialize(),   /* 시리얼라이즈는 name속성 기반으로 동작함 */
				dataType : 'text', 
				success : function(resData){
				
				/* 여기에 success만 있으면 안됨 error도 있어야됨 exception을 error가 받아오기 때문 */
				/* resData는 성공했습니다. 라는 텍스트만 넘어와야함 다른거 넘어오면 안됨 */
					alert(resData);
					$('#sno').val('');  /* 제이쿼리코드 */
					/*document.getElementById('sno').value='';  위에 있는 거랑 똑같은 쿼리임 */
					$('#name').val('');
					$('#dept').val(''); 
					$('#stafflist').empty();
					fn_getlist();
				},
				error : function(jqXHR){
					alert(jqXHR.responseText);
				}
				/* s로 응답보내는 방법과 e로 java에서 응답보내는 방법. success로 응답보낼거면 try로, error는 catch 에서 응답만들어줌 */
				/* responseEntity를 써서 응답을 간단히 만들 수 잇음 -- 5장에서 배웠었움 */
			});  // $.ajax
		});
	} // fn_enroll
	
	
	
	
	function fn_serch(){
		$('#btn_query').click(function(){
			$.ajax({
				type : 'get',
				url : '${contextPath}/query.json',
				data : $('#frm_serch').serialize(),
				dataType : 'json', 
				success : function(resData){
						$('#stafflist').empty();
						
						if(resData===''){
							alert('조회된 사원 정보가 없습니다.');
						}else {
							var tr= '<tr>';
							tr +='<td>'+resData.sno+'</td>';
							tr +='<td>'+resData.name+'</td>';
							tr +='<td>'+resData.dept+'</td>';
							tr +='<td>'+resData.salary+'</td>';
							tr += '</tr>';
							$('#stafflist').append(tr);
						}
						
				},
				error : function(jqXHR){
					
					
				}
			});  // $.ajax
		});  // $('#btn_query')
	}  // fn_serch()



</script>
</head>
<body>
<h3>사원등록</h3>
<form id="staff">
	<input type="text" id="sno" name="sno" placeholder="사원번호">
	<input type="text" id="name" name="name" placeholder="사원명">
	<input type="text" id="dept" name="dept" placeholder="부서명">
	<input type="button" value="등록" id="b1">
</form>

<hr>
<h3>사원조회</h3>
<form id="frm_serch">
	<input type="text" id="query" name="query" placeholder="사원조회">
	<input type="button" value="조회" id="btn_query">
</form>	
<hr>

<h3>사원목록</h3>
<table border="1">
	<thead>
		<tr>
			<td>사원번호</td>
			<td>사원명</td>
			<td>부서명</td>
			<td>연봉</td>
		</tr>
	</thead>
	<tbody id="stafflist">
	
	
	</tbody>


</table>







</body>
</html>