<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${contextPah}/resources/css/jquery-ui.min.css">
<!-- t순서 중요. 제이쿼리가 먼저 와야함 -->
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script src="${contextPath}/resources/js/jquery-ui.min.js"></script>
<script>
	$(document).ready(function(){
		$('#targetDt').datepicker({
			dateFormat:'yymmdd'  // 실제로는 yyyymmdd로 적용
			
			
		});
		
		$('#btn').click(function(){
			//alert($('#targetDt').val()); 으로 확인해보기
			$.ajax({
				type: 'get',
				url : '${contextPath}/movie/boxOfficeList',
				data : 'targetDt=' + $('#targetDt').val(),
				dataType : 'json',  // String data가 json덩어리임 json이라고 생각하기 
				success : function(resData){
					// 기존 목록 초기화 
					$('#boxOfficeList').empty();
					// 가져온 목록 나타내기 
					$.each(resData.boxOfficeResult.dailyBoxOfficeList, function(i, movie){
						
						
						
						
					})
					
					
				}
			});
			
		});
	});

</script>
</head>
<body>

	<div>
		<label for="targetDt">조회날짜</label>
		<input type="text" id="targetDt">
		<input type="button" value="조회" id="btn">
	</div>
	
	<hr>
	
	
	<div>
		<table border="1">
			<thead>
				<td>순위</td>
				<td>영화제목</td>
				<td>개봉일</td>
				<td>일일관객수</td>
				<td>누적관객수</td>
			</thead>
		<tbody id="boxOfficeList"></tbody>
		
		</table>
	</div>



</body>
</html>