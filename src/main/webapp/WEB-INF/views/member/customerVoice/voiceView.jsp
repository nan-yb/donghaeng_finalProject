<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
	$.getContextPath = function(){
		return "${pageContext.request.contextPath}";
	}
	<c:if test="${not empty message}">
		alert("${message}");
	</c:if>
	function deleteFunc(customvoice_no){
		var customvoice_pass = prompt("비밀번호 입력");
		if(!customvoice_pass) return;
		document.deleteForm.customvoice_pass.value=customvoice_pass;
		document.deleteForm.submit();
	}	
</script>

<section class="s-content s-content--narrow s-content--no-padding-bottom">
<article class="row format-gallery">
<form name="deleteForm" action="<c:url value='/customer/goodVoiceDelete.do' />" method="post">
		<input type="hidden" name="customvoice_no" value="${customvoice.customvoice_no}" />
		<input type="hidden" name="customvoice_pass" />
		<input type="hidden" name="mem_id" value="${authMember.person_id}"/>
	</form>
	<div class="s-content__header col-full">
	<h1 class="s-content__header-title">
		${customvoice.customvoice_title} - ${customvoice.company_id}
	</h1>
	<ul class="s-content__header-meta">
	<li>작성자 : ${customvoice.mem_id}</li>
		<li class="date">${customvoice.customvoice_date}</li>
	</ul>
	</div>
	<div class="col-full s-content__main">
		<p>${customvoice.customvoice_content}</p>
    </div>
    <div>
    <c:url value="/customer/goodVoiceUpdate.do" var="updateURL">
					<c:param name="what" value="${customvoice.customvoice_no}" />
				</c:url>
				<input type="button" value="수정" 
					onclick="location.href='${updateURL}';"
				/>
				
				<input type="button" value="삭제" 
					onclick="deleteFunc('${customvoice.customvoice_no}');"
				/>
    </div>
</article>
</section>








