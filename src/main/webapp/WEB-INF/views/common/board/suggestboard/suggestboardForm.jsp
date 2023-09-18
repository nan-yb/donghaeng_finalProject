<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>

<section class="s-content">
	<div class="row masonry-wrap">

	<!-- 건의 게시판 등록 -->
	<!-- 2.	kr.or.ddit.sboard.controller.SuggestboardInsertController -->
	<!-- 등록 -->

	<form:form modelAttribute="suggest" method="post" enctype="multipart/form-data">
		<input type="hidden" name="suggest_board_no" value="${suggest.suggest_board_no }" /> 
		<input type="hidden" name="mem_id" value="${authMember.person_id}" />
		<input type="hidden" name="suggest_board_date" value="${suggest.suggest_board_date}" />
		<table>
			<tr>
				<th>글제목</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="suggest_board_title"
							value="${suggest.suggest_board_title}" /><span
							class="input-group-text" class="error">${errors["suggest_board_title"]}</span>
					</div></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="suggest_board_pass"
							value="${suggest.suggest_board_pass}" /><span
							class="input-group-text" class="error">${errors["suggest_board_pass"]}</span>
					</div></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td>
					<div class="input-group">
						<textarea rows="10" cols="150" name="suggest_board_content" id="suggest_board_content">${suggest.suggest_board_content }</textarea>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<input type="submit" value="저장" />
					<input type="button" value="뒤로가기" onclick="history.back();"
					/>
					
				</td>
			</tr>
		</table>
	</form:form>


</div>
</section>
