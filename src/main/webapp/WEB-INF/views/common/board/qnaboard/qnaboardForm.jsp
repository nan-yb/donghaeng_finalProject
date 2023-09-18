<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="<c:url value='/js/ckeditor/ckeditor.js' />"></script>
<c:if test="${not empty message }">
	<script>
	alert("${message}");
	</script>
</c:if>
<section class = "s-content">
	<div class="row masonry-wrap">
<form method="post" id="boardForm" >
	<input type="hidden" name="qnaboard_no" value="${qnaboard.qnaboard_no }" />
	<table>
			<input type="hidden" name="mem_id" value="${authMember.person_id }" />
			<tr>
				<th>제목</th>
				<td><input type="text" name="qnaboard_title"
					value="${qnaboard.qnaboard_title }" /><span class="error">${errors.qnaboard_title}</span></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea cols="50" rows="30" name="qnaboard_content">${qnaboard.qnaboard_content }</textarea></td>
			</tr>
			<tr>
			<td colspan="3">
				<input type="submit" value="등록" />
				<input type="reset" value="취소" />
				<input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/qnaboard/qnaboardList.do'" />
			</td>
		</tr>
	</table>
</form>
<script>
	CKEDITOR.replace('qnaboard_content', {
	
	    extraAllowedContent: 'h3{clear};h2{line-height};h2 h3{margin-left,margin-top}',
	
	    // Adding drag and drop image upload.
// 	    extraPlugins: 'uploadimage',
// 	    uploadUrl: '${pageContext.request.contextPath}/board/uploadImage.do',
	
	    // Configure your file manager integration. This example uses CKFinder 3 for PHP.
// 	    filebrowserImageUploadUrl: '${pageContext.request.contextPath}/board/uploadImage.do',
	
	    height: 560,
	    
	    removeDialogTabs: 'image:advanced;link:advanced'
	});
	CKEDITOR.config.autoParagraph = false;
</script>
</div>
</section>