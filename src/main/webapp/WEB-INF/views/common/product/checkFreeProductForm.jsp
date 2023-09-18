<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<SCRIPT type="text/javascript">
	
	$(function() {

	})	
</SCRIPT>

<section
	class="s-content s-content--narrow s-content--no-padding-bottom">

	<article class="row format-standard">

		<div class="s-content__header col-full">
			<h1 class="s-content__header-title">예약확인</h1>
		</div>
		<!-- end s-content__header -->
<%-- 		${authMember } --%>
		<div class="col-full s-content__main">
			<table>
				<tr>
					<th>예약회원</th>
					<td>${mem_id }</td>
				</tr>
				<tr>
					<th>예약호텔</th>
					<td>${hotelName }</td>
				</tr>
				<tr>
					<th>예약인원</th>
					<td>${globalPersonnel } &nbsp; 명</td>
				</tr>
				<tr>
					<th>예약하신룸</th>
					<td>${bookRoom } &nbsp; (4인기준 1방)</td>
				</tr>
			</table>
			
			<form:form modelAttribute="hotelVO" action="${pageContext.request.contextPath }/common/package/insertFreeProduct.do">
				<input type="hidden" name="hotel_id" value="${hotelCode }">
				<input type="hidden" name="mem_id" value="${mem_id }">
				<input type="hidden" name="use_room_cnt" value="${bookRoom }">
				<input type="submit" value="예약">
			</form:form>	
			<form:form action="${pageContext.request.contextPath }/common/package/freeProductForm.do">
				<input type="submit" value="취소">		
			</form:form>		
				
		
		</div>
		<!-- end s-content__main -->

	</article>
</section>