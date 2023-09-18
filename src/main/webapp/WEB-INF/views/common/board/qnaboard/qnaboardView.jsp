<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
<script type="text/javascript">


	function deleteFunc(){
		if (confirm("게시글을 삭제하시겠습니까?") == true) {
			document.deleteForm.submit();
		} else {
			return;
		}
	}
	
	
	function replyListMaker(resp){
		if (resp.errors) {
			alert(resp.errors.message);
		} else {
			var html = "";
			if (resp.dataList) {
				$.each(resp.dataList, function(idx, reply){
					html += "<tr><td>"+reply.person_name+"</td>";
					html += "<td>"+reply.qnaboard_reply_content+"</td>";
					html += "<td>"+reply.qnaboard_reply_date;
					if( '${not empty authMember}' && '${authMember.person_id}' == reply.company_id){
						html += "&nbsp;<span class='replyUpdateBtn' onclick='replyUpdateFunc("+reply.qnaboard_reply_no+")' >[수정]</span>&nbsp;<span class='replyDelBtn' onclick='replyDelFunc("+reply.qnaboard_reply_no+")'>[삭제]</span>";
					}
					html += "</td></tr>";
				});
			}else {
				html += "<tr><td colspan='3'>댓글없음</td></tr>"
			}
			pagingArea.html(resp.pagingHTML);
			listBody.html(html);
			replyForm[0].reset();
		}
	}
	
	//버튼방식
// 	function replyListMaker(resp){
// 		if (resp.errors) {
// 			alert(resp.errors.message);
// 		} else {
// 			var html = "";
// 			if (resp.dataList) {
// 				$.each(resp.dataList, function(idx, reply){
// 					html += "<input type='hidden' name='qnaboard_reply_no' value='"+reply.qnaboard_reply_no+"' />";
// 					html += "<input type='hidden' name='reply_content' value='"+reply.qnaboard_reply_content+"' />";
// 					html += "<tr><td>"+reply.person_name+"</td>";
// 					html += "<td>"+reply.qnaboard_reply_content+"</td>";
// 					html += "<td>"+reply.qnaboard_reply_date+"&nbsp;<input type='button' id='replyUpdateBtn' value='수정' onclick='replyUpdateFunc("+reply.qnaboard_reply_no+")' />&nbsp;<input type='button' id='replyDelBtn' value='삭제' onclick='replyDelFunc("+reply.qnaboard_reply_no+")' /></td>";
// 					html += "</tr>";
// 				});
// 			}else {
// 				html += "<tr><td colspan='3'>댓글없음</td></tr>"
// 			}
// 			pagingArea.html(resp.pagingHTML);
// 			listBody.html(html);
// 			replyForm[0].reset();
// 		}
// 	}
	
	function replyUpdateFunc(reply_no){
			if (confirm("댓글을 수정하시겠습니까?") == true) {
				$("#replyUpdateModal").find("#reply_no").val(reply_no);
				$("#replyUpdateModal").find("#reply_content").val();
				$("#replyUpdateModal").modal("show");
			} else {
				return;
			}
	}
	
	function replyDelFunc(reply_no){
		if (confirm("댓글을 삭제하시겠습니까?") == true) {
			var action = replyForm.attr("action");
			replyForm.attr("action", "${pageContext.request.contextPath}/qnaboard/qnaReplyDelete.do");
			replyForm.find("[name='qnaboard_reply_no']").val(reply_no);
			replyForm.submit();
 			replyForm.attr("action", action);
 			replyForm[0].reset();
		} else {
			return;
		}
	}
	
	
	
	$(function(){
		pagingArea = $("#pagingArea");
		listBody = $("#listBody");
		replyForm = $("[name='replyForm']");
		reUpdateModal = $("#replyUpdateModal");
		modalBtn = $("#modalBtn");
		
		
// 		listBody.on("click", ".replyUpdateBtn", function(){
// 			var reply_content = listBody.find("[name='reply_content']").val();
// 			if (confirm("댓글을 수정하시겠습니까?") == true) {
// 				reUpdateModal.find("#reply_no").val(reply_no);
// 				reUpdateModal.find("#reply_content").val(reply_content);
// 				reUpdateModal.modal("show");
// 			} else {
// 				return;
// 			}
// 		});
		
		modalBtn.on("click", function(){
			var action = replyForm.attr("action");
			replyForm.attr("action", "${pageContext.request.contextPath}/qnaboard/qnaReplyUpdate.do");
			reply_no = reUpdateModal.find("#reply_no").val();
			reply_content = reUpdateModal.find("#reply_content").val();
			replyForm.find("[name='qnaboard_reply_no']").val(reply_no);
			replyForm.find("[name='qnaboard_reply_content']").val(reply_content);
			replyForm.submit();
			replyForm.attr("action", action);
 			replyForm[0].reset();
 			$("#modalForm")[0].reset();
 			reUpdateModal.modal("hide");
		});
		
		
		
		
// 		listBody.on("click", ".replyDelBtn", function(){
// 			var reply_no = listBody.find("[name='qnaboard_reply_no']").val();
// 			if (confirm("댓글을 삭제하시겠습니까?") == true) {
// 				var action = replyForm.attr("action");
// 				replyForm.attr("action", "${pageContext.request.contextPath}/qnaboard/qnaReplyDelete.do");
// 				replyForm.find("[name='qnaboard_reply_no']").val(reply_no);
// 				replyForm.submit();
// 	 			replyForm.attr("action", action);
// 	 			replyForm[0].reset();
// 			} else {
// 				return;
// 			}
// 		});
		
		
		replyForm.ajaxForm({
			dataType: 'json',
			success: function(resp){
				replyListMaker(resp);
			},
			error: function(resp){
				console.log(resp.status);
			}
		});
	});
	
	
	function pagingReply(page, qnaboard_no){
		$.ajax({
			url: "${pageContext.request.contextPath}/qnaboard/qnaReplyList.do",
			data:{
				page: page,
				qnaboard_no: qnaboard_no
			},
			dataType: "json",
			success: replyListMaker,
			error: function(resp){
				console.log(resp.status);
			}
		});
	}
	
</script>
<c:set var="mutable" value="false" />
<c:if test="${not empty authMember and authMember.person_id eq qnaboard.mem_id }">
	<c:set var="mutable" value="true" />
</c:if>

<c:set var="mutable2" value="false" />
<c:if test="${not empty authMember and '2' eq authMember.person_type }">
	<c:set var="mutable2" value="true" />
</c:if>





<section class="s-content">
	<div class="row masonry-wrap">
	<form name="deleteForm" action='<c:url value="/qnaboard/qnaboardDelete.do" />' method="post" >
		<input type="hidden" name="qnaboard_no" value="${qnaboard.qnaboard_no }">
	</form>
	
	
	<table>
		<tr>
			<th>번호</th>
			<td>${qnaboard.qnaboard_no }</td>
		</tr>
		<tr>
			<th>회원명</th>
			<c:forEach items="${qnaboard.personList }" var="person">
				<td>${person.person_name }</td>
			</c:forEach>
		</tr>
		<tr>
			<th>작성날짜</th>
			<td>${qnaboard.qnaboard_date }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${qnaboard.qnaboard_title }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="60" cols="60" disabled="disabled" draggable="false">${qnaboard.qnaboard_content }</textarea></td>
		</tr>
		<tr>
			<td colspan="3">
				<c:url value="/qnaboard/qnaboardUpdate.do" var="updateURL">
					<c:param name="what" value="${qnaboard.qnaboard_no }"/>
				</c:url>
				<c:if test="${mutable }">
				<input type="button" value="수정" onclick="location.href='${updateURL }'"/>
				<input type="button" value="삭제" onclick="deleteFunc()" />
				</c:if>
				<input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/qnaboard/qnaboardList.do'" />
			</td>
		</tr>
	</table>
	<div>
		<c:if test="${empty authMember.person_id }">
        	<textarea rows="5" cols="70" name="qnaboard_reply_content" placeholder="로그인 후 이용하실 수 있습니다." readonly="readonly"></textarea>
        </c:if>
        <c:if test="${not empty authMember.person_id }">
		<form name="replyForm" method="post" action="<c:url value='/qnaboard/replyInsert.do' />">
			<input type="hidden" name="qnaboard_no" value="${qnaboard.qnaboard_no }" />
			<input type="hidden" name="qnaboard_reply_no"  />
			<input type="hidden" name="company_id" value="${authMember.person_id }" />
			<textarea rows="5" cols="70" name="qnaboard_reply_content" placeholder="댓글을 등록하세요"></textarea>
			<c:if test="${mutable2 eq 'true' }">
			<input type="submit" value="등록" />
			</c:if>
			<c:if test="${mutable2 eq 'false' }">
			<input type="button" value="등록" onclick="alert('기업회원만 댓글을 달수있습니다.')" />
			</c:if>
		</form>
		</c:if>
	</div>
	<table>
		<tbody id="listBody">
		
		</tbody>
		<tfoot>
			<tr>
				<td colspan="3">
					<nav id="pagingArea">
					
					</nav>
				</td>
			</tr>
		</tfoot>
	</table>
	</div>
</section>

<!-- Modal -->
<div class="modal fade" tabindex="-1" role="dialog" id="replyUpdateModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">댓글 수정</h5>
             <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="float:right; margin-left: 400px;"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
         <form onsubmit="return false;" id="modalForm">
            <input type="hidden" id="reply_no" />
           <textarea id="reply_content" rows="10" cols="50" placeholder="댓글을 입력해주세요"></textarea>
        </form>   
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="modalBtn">수정</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
      </div>
    </div>
    </div>
</div>





<script>
	function paging(page){
		pagingReply(page,${qnaboard.qnaboard_no});
	}
	paging(1);
</script>


