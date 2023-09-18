<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/ckeditor/ckeditor.js"></script>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>

<section class="s-content">
	<div class="row masonry-wrap">
		<form:form modelAttribute="board" id="boardForm" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="bo_no" value="${board.bo_no }" />
			<input type="hidden" name="bo_ip"
				value="${pageContext.request.remoteAddr }" />
			<input type="hidden" name="bo_parent" value="${param.parent }" />

			<table>
				<tr>
					<th>글제목</th>
					<td><div class="input-group">
							<input class="form-control" type="text" name="bo_title"
								value="${board.bo_title}" />
							<form:errors path="bo_title" element="span"
								cssClass="error input-group-text" />
						</div></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><div class="input-group">
							<input class="form-control" type="text" name="bo_writer"
								value="${board.bo_writer}" />
							<form:errors path="bo_writer" element="span"
								cssClass="error input-group-text" />
						</div></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><div class="input-group">
							<input class="form-control" type="text" name="bo_pass"
								value="${board.bo_pass}" />
							<form:errors path="bo_pass" element="span"
								cssClass="error input-group-text" />
						</div></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><div class="input-group">
							<input class="form-control" type="text" name="bo_mail"
								value="${board.bo_mail}" />
							<form:errors path="bo_mail" element="span"
								cssClass="error input-group-text" />
						</div></td>
				</tr>
				<tr>
					<th>기존 파일</th>
					<td><c:forEach items="${board.pdsList }" var="pds"
							varStatus="vs">
							<span> ${pds.pds_filename } <span class="pdsDelete"
								id="span_${pds.pds_no }">[삭제]</span> <c:if
									test="${not vs.last }">&nbsp;|&nbsp;</c:if>
							</span>
						</c:forEach></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="bo_file" /> <input type="file"
						name="bo_file" /> <input type="file" name="bo_file" /></td>
				</tr>
				<tr>
					<th>글내용</th>
					<td>
						<div class="input-group">
							<textarea rows="" cols="" name="bo_content" id="bo_content">${board.bo_content }</textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="저장" /> <input
						type="reset" value="취소" /> <input type="button" value="뒤로가기"
						onclick="history.back();" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</section>

<script type="text/javascript">
	var boardForm = $("#boardForm");
	var inputTag = "<input type='text' name='delFiles' value='%v' />";
	$(".pdsDelete").on("click", function() {
		$(this).parent().hide();
		var regex = /span_(\d+)/ig;
		var pds_no = regex.exec($(this).prop("id"))[1];
		boardForm.append(inputTag.replace("%v", pds_no));
	});
	CKEDITOR
			.replace(
					'bo_content',
					{

						extraAllowedContent : 'h3{clear};h2{line-height};h2 h3{margin-left,margin-top}',

						// Adding drag and drop image upload.
						extraPlugins : 'uploadimage',
						uploadUrl : '${pageContext.request.contextPath}/board/uploadImage.do',

						// Configure your file manager integration. This example uses CKFinder 3 for PHP.
						filebrowserImageUploadUrl : '${pageContext.request.contextPath}/board/uploadImage.do',

						height : 560,

						removeDialogTabs : 'image:advanced;link:advanced'
					});
</script>







