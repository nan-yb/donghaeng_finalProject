<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	.liHide{
		 list-style:none;
	}
</style>
<script type="text/javascript">
	$.getContextPath = function(){
		return "${pageContext.request.contextPath}";
	}
	
	function deleteFunc(notice_no){
		var result = confirm("삭제하시려면 확인을 눌러주세요.");
		if(result){
			document.deleteForm.submit();
		} else {
			return;
		}
	}
	function aFunc(board_no){
		document.deleteForm.submit();
	}
	<c:if test="${not empty message}">
		alert("${message}");
	</c:if>
	
</script>

<section class="s-content s-content--narrow s-content--no-padding-bottom">
<article class="row format-gallery">
	<div class="s-content__header col-full">
	<form name="deleteForm" action="<c:url value='/noticeboard/noticeboardDelete.do' />" method="post">
		<input type="hidden" name="notice_no" value="${notice.notice_no }" />
		<input type="hidden" name="notice_pass" />
		
	</form>
	<h5>&#91;공지사항&#93;</h5>
	
	<h1 class="s-content__header-title">
		${notice.notice_no}
	</h1>
	
	<ul class="s-content__header-meta">
		<li class="liHide">${notice.notice_date}</li>
		<li class="liHide">
			<a>조회수 : ${notice.notice_view_count+1}</a>
			첨부파일 :
			<c:forEach items="${notice.noticeFileList }" var="notice" varStatus="vs">
				<c:url value="/noticeboard/noticeboardDownload.do" var="downloadURL">
					<c:param name="what" value="${notice.notice_file_no }" />
				</c:url>
				<a href="${downloadURL }">${notice.notice_file_name }</a>
				<c:if test="${not vs.last }">&nbsp;|&nbsp;</c:if>
			</c:forEach>
		</li>
	</ul>
	</div>
	
	<div class="col-full s-content__main">
		<p>${notice.notice_content}</p>
		<div class="s-content__author">
			<div class="s-content__author-about">
            	<h4 class="s-content__author-name">
                	<a href="#0">${notice.person_id}</a>
                </h4>
                <p>개인의 소개말을 기입.</p>
                <ul class="s-content__author-social">
                	<li>Facebook</li>
                    <li>Instagram</li>
                    <li>Google</li>
                    <li>Naver</li>
                </ul>
            </div>
   		</div>
    </div>
	
	<c:url value="/noticeboard/noticeboardUpdate.do" var="updateURL">
		<c:param name="what" value="${notice.notice_no }" />
	</c:url>
	
	<c:if test="${authMember.person_type eq 3 }">
		<input type="button" value="수정" 
			onclick="location.href='${updateURL}';"
		/>
		<input type="button" value="삭제" 
			onclick="deleteFunc(${notice.notice_no});"
		/>
	</c:if>
	
	</article>
</section>