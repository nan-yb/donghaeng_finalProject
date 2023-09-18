<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="wrapper">
	<div id="page-wrapper">
		<form action="${pageContext.request.contextPath }/survey/surveyInsert.do"
			method="post">
			<input type="hidden" name="company_id" value="${authMember.person_id }">
			<table>
				<tr>
					<th>설문조사_제목</th>
					<td><input type="text" name="survey_title"
						value="${surveysurvey_title}" /><span class="error">${errors.survey_title}</span></td>
				</tr>

				<tr>
					<td><input type="submit" value="등록" /></td>
				</tr>

			</table>


			<table>
				<tr>
					<th>평가항목_내용</th>
					<td><input type="text" name="evaluation_content"
						value="${survey_evaluationevaluation_content}" /><span
						class="error">${errors.evaluation_content}</span></td>
				</tr>
				<tr>
					<th>평가항목_내용</th>
					<td><input type="text" name="evaluation_content"
						value="${survey_evaluationevaluation_content}" /><span
						class="error">${errors.evaluation_content}</span></td>
				</tr>
			</table>
		</form>
	</div>
</div>