<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

<script type="text/javascript" src="<c:url value='/js/tipReplyProcess.js' />"></script>
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


	$.getAuthMember = function(){
	    return "${authMember.person_id}";
 	}
   	$.getContextPath = function(){
    	  return "${pageContext.request.contextPath}";
  	 }
   
   function deleteFunc(travel_tip_no){
	   var result = confirm("삭제하시려면 확인을 눌러주세요.");
	   if(result){
			document.deleteForm.submit();
		} else {
			return;
		}
   }
   function aFunc(travel_tip_no){
      document.deleteForm.submit();
   }
   
	function replyUpfunc(reply_no){
		if (confirm("댓글을 수정하시겠습니까?") == true) {
			$("#replyUpdateModal").find("#travel_tip_reply_no").val(reply_no);
			$("#replyUpdateModal").modal("show");
		} else {
			return;
		}
	}
   
   function replyDelfunc(reply_no){
		if (confirm("댓글을 삭제하시겠습니까?") == true) {
			var action = replyForm.attr("action");
			replyForm.attr("action", "${pageContext.request.contextPath}/tipboard/replyDelete.do");
			replyForm.find("[name='travel_tip_reply_no']").val(reply_no);
			replyForm.submit();
 			replyForm.attr("action", action);
 			replyForm[0].reset();
		} else {
			return;
		}
	}
   
   <c:if test="${not empty message}">
      alert("${message}");
   </c:if>
   
</script>

<section class="s-content s-content--narrow s-content--no-padding-bottom">
<article class="row format-gallery">
   <div class="s-content__header col-full">
   
   <form name="deleteForm" action="<c:url value='/tipboard/tipboardDelete.do' />" method="post">
      <input type="hidden" name="travel_tip_no" value="${travel_tip.travel_tip_no }" />
      <input type="hidden" name="travel_tip_pass" />
      <input type="hidden" name="travel_tip_mem_id" value="${authMember.person_id}"/>
   </form>
   <div class="board_header">
   <div class="h5_date"><h5>&#91;여행팁 게시판&#93;${travel_tip.travel_tip_title}</h5>${travel_tip.travel_tip_date}</div>
   </div>
   <div style="text-align: left;">${travel_tip.travel_tip_mem_id}</div>
      <div class="col-full s-content__main">
      <p>${travel_tip.travel_tip_content}</p></div>
   <div class="board_plus">
         <a>조회수 : ${travel_tip.travel_tip_view_count}</a>
         <a>추천수 : ${travel_tip.travel_tip_recommend_count}</a>
         첨부파일 :
         <c:forEach items="${travel_tip.pdsList }" var="pds" varStatus="vs">
            <c:url value="/tipboard/tipboardDownload.do" var="downloadURL">
               <c:param name="what" value="${pds.travel_tip_file_no }" />
            </c:url>
            <a href="${downloadURL }">${pds.travel_tip_file_name }</a>
            <c:if test="${not vs.last }">&nbsp;|&nbsp;</c:if>
         </c:forEach>
      </div>
   </div>

            <c:url value="/tipboard/tipboardUpdate.do" var="updateURL">
               <c:param name="what" value="${travel_tip.travel_tip_no }" />
            </c:url>
            
            <c:if test="${authMember.person_id eq travel_tip.travel_tip_mem_id }">
            	<br><span style="padding-top: 5px; padding-left: 27px;" class="upnde_btn2" onclick="location.href='${updateURL}';"><i class='far far fa-edit'> 수정</i></span>
            </c:if>
            
            <c:if test="${authMember.person_id eq travel_tip.travel_tip_mem_id }">
	            <span style=" padding-left: 27px;" class="upnde_btn2" onclick="deleteFunc(${travel_tip.travel_tip_no});"><i class='far fa-trash-alt'></i> 삭제</span>
			</c:if>
			
            <c:url value="/tipboard/tipboardInsert.do" var="insertURL">
               <c:param name="parent" value="${travel_tip.travel_tip_no }"/>
            </c:url>
            
            <c:url value="/tipboard/tipboardRcmd.do" var="rcmdURL">
               <c:param name="what" value="${travel_tip.travel_tip_no }" />
               <c:param name = "auth" value = "${authMember.person_id }" />
            </c:url>

            <c:url value="/tipboard/tipboardReport.do" var="reportURL">
               <c:param name="what" value="${travel_tip.travel_tip_no }" />
               <c:param name = "auth" value = "${authMember.person_id }" />
            </c:url>
            
            <c:if test = "${not empty authMember.person_id }">
            <div style=" padding-left: 18px;" class="board_good" onclick="location.href='${reportURL}';"><i class="fas fa-bullhorn">신고하기</i></div>
            <div style=" padding-left: 25px;" class="board_good" onclick="location.href='${rcmdURL}';"><i class="far fa-thumbs-up">좋아요</i></div>
<!--             <input type="button" value="추천하기"  -->
<%--                onclick="location.href='${rcmdURL}';" --%>
<!--             /> -->
			</c:if>
            
</article>
   <div class="comments-wrap">
    <div id="comments" class="row">
        <div class="col-full">
              <ol class="commentlist">
<!--                  <li class="depth-1 comment"> -->
                    <div id="listBody"></div>
<!--                  </li> -->
              </ol>
              <div class="respond">
               <form name="replyForm" method="post" action='<c:url value="/tipboard/replyInsert.do" />'>
                  <fieldset>
                  <input type="hidden" name="travel_tip_reply_no" />
                  <input type="hidden" name="travel_tip_no" value="${travel_tip.travel_tip_no }" />
                  <div class="form-field">
                  <input type="hidden" name="travel_tip_mem_id" class="full-width" placeholder="작성자 ID" value="${authMember.person_id}" readonly="readonly"/>
                  </div>
                  
                  <nav aria-label="Page navigation" id="pagingArea">
				  </nav>
                  
                  <c:if test="${empty authMember.person_id }">
                  		<h4>댓글</h4> <textarea name="travel_tip_reply_content" class="full-width" placeholder="로그인 후 이용하실 수 있습니다." readonly="readonly"></textarea>
                  </c:if>
                  
                  <c:if test="${not empty authMember.person_id }">
                  	<h4>댓글</h4> <textarea name="travel_tip_reply_content" class="full-width" placeholder="작성내용을 입력하세요."></textarea>
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
           <input type="hidden" id="travel_tip_reply_no" />
           <textarea id="reply_content" rows="10" cols="40" placeholder="댓글을 입력해주세요"></textarea>
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
   pagingReply(page, ${travel_tip.travel_tip_no});
   }
paging(1);
</script> 







