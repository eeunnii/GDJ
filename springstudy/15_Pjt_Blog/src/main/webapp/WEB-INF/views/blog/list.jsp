<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 

<jsp:include page="../layout/header.jsp">
	<jsp:param value="블로그목록" name="title"/>
</jsp:include>

	

<div>

	<h1>블로그 목록(전체 ${totalRecord}개)</h1>
	
	
	<%-- <c:if test="${loginUser != null }">--%>
	<%-- 로그인 한 사람만 버튼이 보임. 관리지만 작성하면 좋겠으면. ${loginUser.id == 'admin'으로 하면됨 }" --%>
	
	
		<div>
			<input type="button" value="블로그 작성하기"  onclick="location.href='${contextPath}/blog/write'">
		</div>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>
					<td>제목</td>
					<td>조회수</td>
					<td>작성일</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${blogList}" var="blog" varStatus="vs">
					<tr>
						<td>${beginNo - vs.index}</td>  <!-- -0, -1, -2로 index 값 사용함 -->
						<td><a href="${contextPath}/blog/increase/hit">${blog.title}</a></td>
						<td>${blog.hit}</td>
						<td>${blog.createDate}</td>
					</tr>	
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
						${paging}
					</td>
				</tr>
			</tfoot>
		</table>
	</div>	
</div>
	


</body>
</html>