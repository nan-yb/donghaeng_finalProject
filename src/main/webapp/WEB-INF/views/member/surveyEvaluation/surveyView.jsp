<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type = "text/javascript">

</script>

<section class="s-content">
	<div class="row masonry-wrap">
		
		<table>
			<tr>
				<th>설문조사 번호</th>
				<c:forEach items="${survey}" var="survey">
					<td><span> ${survey.survey_no } </span></td>
				</c:forEach>
			</tr>
			
			<tr>
				<th>제목</th>
				<c:forEach items="${survey}" var="survey">
					<td><span> ${survey.survey_title } </span></td>
				</c:forEach>
			</tr>

			<tr>
				<th>기업</th>
				<c:forEach items="${survey}" var="survey">
					<td><span> ${survey.company_id } </span></td>
				</c:forEach>
			</tr>
			
			<tr>
				<th>설문 기간</th>
				<c:forEach items="${survey}" var="survey">
					<td> ${survey.survey_date } ~ ${survey.survey_end_date }</td>
				</c:forEach>
			</tr>
			
			<tr>
			<th>설문 현황</th>
				<c:forEach items="${survey}" var="survey">
					<c:forEach items = "${survey.surveyList }" var = "surveyList">
						<td><span> ${surveyList.evaluation_count } 명</span></td>
					</c:forEach>
				</c:forEach>
			</tr>
		</table>
		<c:url value="/member/surveyEvaluationForm.do" var="surveyForm">
			<c:forEach items="${survey}" var="survey">
				<c:param name="survey_no" value="${survey.survey_no }"></c:param>
			</c:forEach>
		</c:url>
		<c:if test= "${not empty authMember }">
			<input type = "button" value = "설문조사 작성"
				onclick="location.href='${surveyForm}';" />
		</c:if>
	</div>
</section>