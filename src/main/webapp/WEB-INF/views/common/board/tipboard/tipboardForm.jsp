<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
<section class="s-content">
	<div class="row masonry-wrap">
	<form:form modelAttribute="travel_tip" id="travel_tipForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="travel_tip_no" value="${travel_tip.travel_tip_no }" /> 
		<input type="hidden" name="travel_tip_parent" value="${param.parent }" />
		<input type="hidden" name="travel_tip_mem_id" value="${authMember.person_id}" />
		<table>
			<tr>
				<th>글제목</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="travel_tip_title"
							value="${travel_tip.travel_tip_title}" /><form:errors path="travel_tip_title" element="span" cssClass="error input-group-text" />
					</div></td>
			</tr>
			<tr>
				<th>기존 파일</th>
				<td>
					<c:forEach items="${travel_tip.pdsList }" var="pds" varStatus="vs">
						<span>
							${pds.travel_tip_file_name }
							<span class="pdsDelete" id="span_${pds.travel_tip_file_no }">[삭제]</span>
							<c:if test="${not vs.last }">&nbsp;|&nbsp;</c:if>
						</span>
					</c:forEach>
				</td>
			</tr>
			<tr>	
				<th>첨부파일</th>
				<td>
					<input type="file" name="travel_tip_file" />
					<input type="file" name="travel_tip_file" />
					<input type="file" name="travel_tip_file" />
				</td>
			</tr>
			<tr>
				<th>글내용</th>
				<td>
					<div class="input-group">
						<textarea rows="10" cols="150" name="travel_tip_content" id="travel_tip_content">${travel_tip.travel_tip_content }</textarea>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="저장" />
					<input type="reset" value="취소" />
					<input type="button" value="뒤로가기" 
						onclick="history.back();"
					/>
				</td>
			</tr>
		</table>
	</form:form>
	<script type="text/javascript">
		var travel_tipForm = $("#travel_tipForm");
		var inputTag = "<input type='text' name='delFiles' value='%v' />";
		$(".pdsDelete").on("click", function(){
			$(this).parent().hide();
			var regex = /span_(\d+)/ig;
			var pds_no = regex.exec($(this).prop("id"))[1];
			travel_tipForm.append(inputTag.replace("%v", pds_no));
		});
		CKEDITOR.replace('travel_tip_content', {

		      extraAllowedContent: 'h3{clear};h2{line-height};h2 h3{margin-left,margin-top}',

		      // Adding drag and drop image upload.
		      extraPlugins: 'uploadimage',
		      uploadUrl: '${pageContext.request.contextPath}/tipboard/uploadImage.do',

		      // Configure your file manager integration. This example uses CKFinder 3 for PHP.
		      filebrowserImageUploadUrl: '${pageContext.request.contextPath}/tipboard/uploadImage.do',

		      height: 560,

		      removeDialogTabs: 'image:advanced;link:advanced'
	    });
	</script>
</div>
</section>

















