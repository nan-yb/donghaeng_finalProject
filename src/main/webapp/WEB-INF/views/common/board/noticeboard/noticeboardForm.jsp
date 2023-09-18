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

<section class = "s-content">
	<div class="row masonry-wrap">
<form:form modelAttribute="notice" name="noticeForm" method="post" enctype="multipart/form-data">

	<input type = "hidden" name = "notice_no" value = "${notice.notice_no }"/>
	<input class = "form-control" type = "hidden" name = "person_id" value = "${authMember.person_id }"/>
	
	<table>
		<tr>
			<th>제목</th>
			<td>
				<div class = "input-group">
					<input class = "form-control" type = "text" name = "notice_title" value = "${notice.notice_title }"/>
				</div>
			</td>
		</tr>
		
		<tr>
			<th>기존 파일</th>
			<td>
				<c:forEach items="${notice.noticeFileList }" var="notice" varStatus="vs">
					<span>
						${notice.notice_file_name }
						<span class="noticeDelete" id="span_${notice.notice_file_no }">[삭제]</span>
						<c:if test="${not vs.last }">&nbsp;|&nbsp;</c:if>
					</span>
				</c:forEach>
			</td>
		</tr>
		
		<tr>	
			<th>첨부파일</th>
			<td>
				<input type="file" name="notice_file" />
				<input type="file" name="notice_file" />
				<input type="file" name="notice_file" />
			</td>
		</tr>
		
		<tr>
			<th>내용</th>
			<td>
				<div class="input-group">
					<textarea class = "form-control" id = "notice_content" name = "notice_content" >${notice.notice_content }</textarea>
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
</div>
</section>

<script type = "text/javascript">
// 	var noticeForm = $("#noticeForm");
// 	var inputTag = "<input type = 'text'name = 'delFiles' value='%v' />";
// 	$(".noticeDelete").on("click", function(){
// 		$(this).parent().hide();
// 		var regex = /span_(\d+)/ig;
// 		var notice_no = regex.exec($(this).prop("id"))[1];
// 		console.log(notice_no);
// 		noticeForm.append(inputTag.replace("%v", notice_no));
// 	});
	CKEDITOR.replace('notice_content', {
		
		extraAllowedContent: 'h3{clear};h2{line-height};h2 h3{margin-left,margin-top}',

	    // Adding drag and drop image upload.
	    extraPlugins: 'uploadimage',
	    uploadUrl: '${pageContext.request.contextPath}/noticeboard/uploadImage.do',

	    // Configure your file manager integration. This example uses CKFinder 3 for PHP.
	    filebrowserImageUploadUrl: '${pageContext.request.contextPath}/noticeboard/uploadImage.do',

	    height: 560,

	    removeDialogTabs: 'image:advanced;link:advanced'
	});
</script>