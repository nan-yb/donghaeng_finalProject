<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<style>
	.liHide{
		 list-style:none;
	}
</style>

<script type="text/javascript" src="<c:url value='/js/freeReplyProcess.js' />"></script>
<script type="text/javascript">

	<c:if test="${not empty person.per_img }">
	$.getImage = function(){ 	
	    return "data:image/*;base64,${person.per_imgToBase64}";
		}
	</c:if>
	
	<c:if test="${empty person.per_img}">
	$.getImage = function(){ 	
	    return "${cPath}/images/sample-image.jpg";
		}
	</c:if>

	$.getContextPath = function(){
		return "${pageContext.request.contextPath}";
	}
	
	$.getAuthMember = function(){
		return "${authMember.person_id}";
	}
	
	function deleteFunc(board_no){
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
	
	<form name="deleteForm" action="<c:url value='/freeboard/freeboardDelete.do' />" method="post">
		<input type="hidden" name="board_no" value="${board.board_no }" />
		<input type="hidden" name="board_pass" />
		<input type="hidden" name="mem_id" value="${authMember.person_id}"/>
	</form>
	<div class="board_header">
		<div class="h5_date"> <h5>&#91;자유게시판&#93;${board.board_title}</h5> ${board.board_date}</div>
<%-- 		<h5 class="liHide">${board.board_date}</h5> --%>
	</div>
<!-- 	<h3  class="s-content__header-title"> -->
<%-- 		${board.board_title} --%>
<!-- 	</h3> -->
<!-- 	<ul class="s-content__header-meta"> -->
<%-- 		<li class="liHide">${board.board_date}</li> --%>
            <div style="text-align: left;">${board.mem_id}</div>
            
		<div class="col-full s-content__main">
			<p>${board.board_content}</p>
    	</div>
                
<!-- 		<li class="liHide"> -->
			<div class="board_plus">
			<a>조회수 : ${board.board_view_count+1}</a>
			<a>추천수 : ${board.board_recommend_count}</a>
			첨부파일 :
			<c:forEach items="${board.freeFileList }" var="board" varStatus="vs">
				<c:url value="/freeboard/freeboardDownload.do" var="downloadURL">
					<c:param name="what" value="${board.board_file_no }" />
				</c:url>
				<a href="${downloadURL }">${board.board_file_name }</a>
				<c:if test="${not vs.last }">&nbsp;|&nbsp;</c:if>
			</c:forEach>
			</div>
		</div>

    
    <c:url value="/freeboard/freeboardUpdate.do" var="updateURL">
		<c:param name="what" value="${board.board_no }" />
	</c:url>
	
	<c:if test="${authMember.person_id eq board.mem_id}">
		<br><span style="padding-top: 3px;" class="upnde_btn2" onclick="location.href='${updateURL}';"><i class='far far fa-edit'> 수정</i></span>
	</c:if>
	
	<c:if test="${authMember.person_id eq board.mem_id}">
		<span class="upnde_btn2" onclick="deleteFunc(${board.board_no});"><i class='far fa-trash-alt'></i> 삭제</span>
	</c:if>
	<c:url value="/freeboard/freeboardInsert.do" var="insertURL">
		<c:param name="parent" value="${board.board_no }"/>
	</c:url>
	
	
	<c:url value="/freeboard/freeboardRcmd.do" var="rcmdURL">
		<c:param name="what" value="${board.board_no }" />
		<c:param name = "auth" value = "${authMember.person_id }"/>
	</c:url>
	
	<c:url value="/freeboard/freeboardReport.do" var = "reportURL">
		<c:param name="what" value = "${board.board_no }"/>
		<c:param name = "auth" value = "${authMember.person_id }"/>
	</c:url>
	
	<c:if test = "${not empty authMember.person_id }">
	<div class="board_good" onclick="location.href='${reportURL}';"><i class="fas fa-bullhorn">신고하기</i></div>
	<div class="board_good" onclick="location.href='${rcmdURL}';"><i class="far fa-thumbs-up">좋아요</i></div>
<!-- 		<input type="button" value="추천하기"  -->
<%-- 			onclick="location.href='${rcmdURL}';" --%>
<!-- 		/> -->
	</c:if>
</article>
<div class="comments-wrap">
	 <div id="comments" class="row">
   	  <div class="col-full">
		     	<ol class="commentlist">
<!-- 		     		<li class="depth-1 comment"> -->
		     			<div id="listBody"></div>
<!-- 		     		</li> -->
		     	</ol>
		     	<div class="respond">
					<form name="replyForm" method="post" action='<c:url value="/freeboard/replyInsert.do" />'>
						<fieldset>
						<input type="hidden" name="board_reply_no" />
						<input type="hidden" name="board_no" value="${board.board_no }" />
						<div class="form-field">
						<input type="hidden" name="board_reply_mem_id" class="full-width" value="${authMember.person_id}" readonly="readonly"/>
						</div>
						
						<nav aria-label="Page navigation" id="pagingArea">
						</nav>
						
						<c:if test="${empty authMember.person_id }">
							<h4>댓글</h4> <textarea class="full-width" placeholder="로그인 후 이용하실 수 있습니다." readonly="readonly"></textarea>
						</c:if>
						<c:if test="${not empty authMember.person_id }">
							<h4>댓글</h4> <textarea name="board_reply_content" class="full-width" placeholder="작성내용을 입력하세요."></textarea>
							<input type="submit" value="등록" class="submit btn--primary btn--large full-width"/>
						</c:if>
					</fieldset>
				</form>
			</div>
    	</div>
	</div>
</div>
</section>

<!-- Modal -->
<div class="modal fade" id="replyUpdateModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
          <span aria-hidden="true">댓글수정</span>
      </div>
      <div class="modal-freedy">
      	<form onsubmit="return false;" id="modalForm">
      		<input type="hidden" id="board_no" value="${board.board_no }"/>
      		<input type="hidden" id="board_reply_no" />
      		<textarea rows="10" cols="40" id="reply_content" placeholder="댓글을 입력해주세요"></textarea>
        </form>	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="modalUpdBtn">수정</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
	function paging(page){
		pagingReply(page, ${board.board_no});
	}
	paging(1);
</script> 