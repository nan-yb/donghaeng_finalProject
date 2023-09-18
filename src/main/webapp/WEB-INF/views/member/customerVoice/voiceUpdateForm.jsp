<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script type="text/javascript">
	
$(function(){
	
	$(window).on("popstate", function(event){
		console.log(event);
		if(event.originalEvent.state){
			var pagingVO = event.originalEvent.state;
			makeList(pagingVO);
		}else{
			location.reload();
		}
	});
})

</script> 
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
<section class="s-content">
	<div class="row masonry-wrap">
	<form:form modelAttribute="customvoice" id="customvoiceForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="customvoice_no" value="${customvoice.customvoice_no }" /> 
		<input type="hidden" name="customvoice_parent" value="${param.parent }" />
		<input type="hidden" name="mem_id" value="${authMember.person_id}" />
		<select class="form-control" name="company_id">
		<option value="">업체 선택</option>
			<c:choose>
			<c:when test="${not empty company }">
				<c:forEach items="${company}" var="company">
					<option value='${company.company_id}'>${company.company_name}</option>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<option value=''>업체가 없음</option>
			</c:otherwise>
			</c:choose>
		</select>
		<table>
			<tr>
				<th>글제목</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="customvoice_title"
							value="${customvoice.customvoice_title}" /><form:errors path="customvoice_title" element="span" cssClass="error input-group-text" />
					</div></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="customvoice_pass"
							value="${customvoice.customvoice_pass}" /><form:errors path="customvoice_pass" element="span" cssClass="error input-group-text" />
					</div></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td>
					<div class="input-group">
						<textarea rows="10" cols="150" name="customvoice_content" id="customvoice_content">${customvoice.customvoice_content }</textarea>
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

















