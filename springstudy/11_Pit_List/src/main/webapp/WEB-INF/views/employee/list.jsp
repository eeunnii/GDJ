<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

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
				<c:forEach items="${employees}" var=emp>
					<tr>	
						<td></td>
						<td>${emp.employeeID}</td>
						<td>${emp.firstName}  ${emp.lastName}</td>
						<td>${emp.email}</td>
						<td>${emp.phoneNumber}</td>
						<td>${emp.hireDate}</td>
						<td>${emp.jobID}</td>
						<td>${emp.salary}</td>
						<td>${emp.commissionPct}</td>  <!-- 여기있는모든것들은 getter를 참고함 -->
						<td>${emp.deptDTO.departmentID}</td>
						<td>${emp.deptDTO.departmentName}</td>
					</tr>
				</c:forEach>
			
			
			</tbody>
			<tfoot>
				<tr>
					<!-- 이전 블록 : 1block이 아니면 이전 블록이 있다 -->
					<c:if test="${pageUtil.beginPage != 1}">
						<a href="${contextPath}/emp/list?page=${pageUtil.beginPage-1}">◀</a>
					</c:if>
					
					
					<!-- 페이지번호 : 현재 페이지는 링크가 없다 . -->
					<c:forEach var="p" begin=${pageUtil.beginPage} end="${pageUtil.endPage}" step="1">
						<c:if test="${p==pageUtil.page }">
						 ${p}
						</c:if>
						<c:if test="${p!=pageUtil.page}">
							<a href= "${contextPath}/emp/list?page${p}"></a>
						</c:if>
					</c:forEach>
					
					
					
					<!-- 다음블록 : 마지막 블록이 아니면 다음블록이 있다.-->
					<c:if test="${pageUtil.endPage != pageUtil.totalPage}">
						<a href="${contextPath}/emp/list?page=${pageUtil.endPage +1}">▶</a>
					</c:if>
				</tr>
			
			
			</tfoot>
			
		</table>
	</div>




<a href="${contextPath}/emp/list">사원목록</a>

</body>
</html>