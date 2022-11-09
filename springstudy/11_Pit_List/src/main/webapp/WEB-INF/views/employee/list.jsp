<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jqery-3.6.1.min.js">
	$(document).ready(function(){
		// area1, area2 표시
		// 초기 상태 : area1, area2 둘 다 숨김
		$('#area1, #area2').css('display', 'none');
		// column 선택에 따른 area1, area2 표시
		$('#column').change(function(){  /* 이벤트 대상이 되는건 this,제이쿼리에서쓸려면 달러{}붙임 */
			let combo = $(this);
			if(combo.val() == ''){
				$('#area1, #area2').css('display', 'none');
			} else if(combo.val() == 'HIRE_DATE' || combo.val() == 'SALARY'){
				$('#area1').css('display', 'none');
				$('#area2').css('display', 'inline');
			} else {
				$('#area1').css('display', 'inline');
				$('#area2').css('display', 'none');
			}
				
		});
	});

</script>
</head>
<body>
	<div>
		<form id="frm_search" action="${contextpath}/emp/search">
			<select id="column" name="column">
				<!-- 일치해야 검색하기 -->
				<option value="">:::선택</option>
				<option value="EMPLOYEE_ID">사원번호</option>
				<!-- -->
				<option value="E.DEPARTMENT_ID">부서번호</option>
				<option value="LAST_NAME">성</option>
				<option value="FIRST_NAME">이름</option>
				<option value="PHONE_NUMBER">연락처</option>
				<!-- 범위 -->
				<option value="HIRE_DATE">입사일</option>
				<option value="SALARY">연봉</option>
			</select>
			<span id="area1">
				<input type="text" name="query" id="query">
			</span>
			<span id="area2">
				<input type="text" id="begin" name="begin">
				~
				<input type="text" id="end" name="end">
 			</span>
 			<span>
 				<input type="submit" value="검색">
 				<input type="button" value="전체사원조회" id="btn_all">
 			</span>
		</form>
	</div>
	
	<hr>

	<div>
		<table>
			<thead>
				<tr>
					<td>순번</td>
					<td>사원번호</td>
					<td>사원명</td>
					<td>이메일</td>
					<td>전화번호</td>
					<td>입사일자</td>
					<td>연봉</td>
					<td>커미션</td>
					<td>부서번호</td>
					<td>부서명</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${employees}" var="emp" varStatus="vs">
					<tr>	
						<td>${beginNo-vs.index}</td>
						<td>${emp.employeeId}</td>
						<td>${emp.firstName}  ${emp.lastName}</td>
						<td>${emp.email}</td>
						<td>${emp.phoneNumber}</td>
						<td>${emp.hireDate}</td>
						<td>${emp.jobId}</td>
						<td>${emp.salary}</td>
						<td>${emp.commissionPct}</td>  <!-- 여기있는모든것들은 getter를 참고함 -->
						<td>${emp.deptDTO.departmentId}</td>
						<td>${emp.deptDTO.departmentName}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="10">
						${paging}
					</td>
				</tr>
			</tfoot>
		</table>
	</div>


</body>
</html>