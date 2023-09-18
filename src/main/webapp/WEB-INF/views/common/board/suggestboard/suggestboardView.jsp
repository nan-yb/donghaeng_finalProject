<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
	function deleteFunc(){
		if (confirm("게시글을 삭제?") == true) {
			document.deleteForm.submit();
		} else {
			return;
		}
	}

</script>
<section class="s-content">
	<div class="row masonry-wrap">
		<form name="deleteForm" action='<c:url value="/suggestboard/suggestboardDelete.do"/>' method="post">
			<input type="hidden" name="bo_no" value="${suggest.suggest_board_no}">
		</form>
		
		<table>
			<tr>
				<th>번호</th>
				<td>${suggest.suggest_board_no }</td>
			</tr>
			<tr>
				<th>회원명</th>
				<td>${suggest.person_name }</td>
			</tr>
			<tr>
				<th>작성날짜</th>
				<td>${suggest.suggest_board_date }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${suggest.suggest_board_title }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${suggest.suggest_board_content }</td>
			</tr>
			
			<tr>
				<td colspan="3">
					<c:url value="/suggestboard/suggestboardUpdate.do" var="updateURL">
						<c:param name="what" value="${suggest.suggest_board_no }"/>
					</c:url>			
					<input type="button" value="수정" onclick="location.href='${updateURL }'"/>
					<input type="button" value="삭제" onclick="deleteFunc()" />
					<input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/suggest/suggestList.do'" />
				</td>
			</tr>
		</table>
	</div>
</section>
