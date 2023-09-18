<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div id="wrapper">
	<div id="page-wrapper">
		<c:forEach items="${cor.personList }" var="person" >
		<table>
		<tr>
			<th>기업회원ID</th>
			<td>${cor.company_id }</td>
		</tr>
		<tr>
			<th>기업이름</th>
			<td>${person.person_name }</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${person.person_tel }</td>
		</tr>
		<tr>
			<th>메일</th>
			<td>${person.person_mail }</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${person.person_zip }</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${person.person_addr1 } ${person.person_addr2 } </td>
		</tr>
		<tr>
			<th>사업자번호</th>
			<td>${cor.company_business_no }</td>
		</tr>
		<tr>
			<th>승인여부</th>
			<c:if test="${cor.company_apply eq 'Y' }">
			<td>승인</td>
			</c:if>
			<c:if test="${cor.company_apply eq 'N' }">
			<td>미승인</td>
			</c:if>
		</tr>
		<tr>
			<td>
				<input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/admin/corManagementList.do'" />
			</td>
		</tr>
		</table>
		</c:forEach>
	</div>
</div>