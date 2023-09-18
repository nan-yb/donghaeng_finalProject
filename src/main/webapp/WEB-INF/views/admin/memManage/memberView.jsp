<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script>
function updateFunc(){
	var result = confirm("수정하시려면 확인을 눌러주세요.");
	
	stateControll = $("#stateControll");
	mem_state = $("input[name='mem_state']");
	
	
	var state = stateControll.val();
	console.log(state);
	
	if(state){
		mem_state.val(state);
	}else{
		alert('체크 안됨')
	}
	
	if(result){
		document.updateForm.submit();
	} else {
		return;
	}
}
	
	<c:if test="${not empty message}">
		alert("${message}");
	</c:if>
</script>

<div id="wrapper">
	<div id="page-wrapper">
		<div class="s-content__header col-full">
			<form name="updateForm"
				action="<c:url value = '/admin/blackListUpdate.do'/>" method="post">
				<input type="hidden" name="mem_id" value="${member.mem_id}" />
				<input type="hidden" name="mem_state" value="" />
			</form>

			<table>
				<tr>
					<th>회원ID</th>
					<td>${member.mem_id }</td>
				</tr>

				<tr>
					<th>이름</th>
					<td><c:forEach items="${member.personList }" var="person">
							<span> ${person.person_name } </span>
						</c:forEach></td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td><c:forEach items="${member.personList }" var="person">
							<span> ${person.person_zip } </span>
						</c:forEach></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><c:forEach items="${member.personList }" var="person">
							<span> ${person.person_addr1 } ${person.person_addr2 } </span>
						</c:forEach></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><c:forEach items="${member.personList }" var="person">
							<span> ${person.person_tel } </span>
						</c:forEach></td>
				</tr>
				<tr>
					<th>메일</th>
					<td><c:forEach items="${member.personList }" var="person">
							<span> ${person.person_mail } </span>
						</c:forEach></td>
				</tr>
				<tr>
					<th>회원상태</th>
					<td><select id="stateControll" class="form-control"
						name="selectState" contextmenu="${member.mem_state }">
							<option value="">${member.mem_state }</option>
							<option value="T">일반&#40;T&#41;</option>
							<option value="F">블랙리스트&#40;F&#41;</option>
					</select> <script type="text/javascript"> 
	 						$('[name="selectState"]').val("${param.selectState}");
	 				</script></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><c:forEach items="${member.personList }" var="person">
							<span> ${person.person_name } </span>
						</c:forEach></td>
				</tr>



				<tr>
					<td colspan="2"><input type="button" value="저장"
						onclick="updateFunc()" /> <input type="reset" value="취소" /> <input
						type="button" value="뒤로가기" onclick="history.back();" /></td>
				</tr>
			</table>
		</div>
	</div>
</div>