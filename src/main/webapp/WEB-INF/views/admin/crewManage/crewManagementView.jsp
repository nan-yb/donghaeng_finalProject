<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script>
	function updateType(){
		var crew_no = ${crew.crew_no}
		var crew_type = $("#wrapper").find("#crew_type").val();
		
		$.ajax({
			url: "${pageContext.request.contextPath}/admin/crewManagementUpdate.do",
			data: {
				crew_no: crew_no,
				crew_type: crew_type
			},
			dataType: "json",
			success: function(resp){
				console.log(resp);
			},
			error:function(resp){
				console.log(resp.status);
			}
		})
		alert("크루제한을 변경했습니다.");
		
	}
</script>
<div id="wrapper">
	<div id="page-wrapper">
		<table class="table table-bordered table-striped table-hover">
		<tr>
			<th>크루번호</th>
			<td>${crew.crew_no }</td>
		</tr>
		<tr>
			<th>크루장ID</th>
			<td>${crew.mem_id }</td>
		</tr>
		<tr>
			<th>크루이름</th>
			<td>${crew.crew_name }</td>
		</tr>
		<tr>
			<th>크루설명</th>
			<td>${crew.crew_content }</td>
		</tr>
		<tr>
			<th>정원</th>
			<td>${crew.crew_limit }</td>
		</tr>
		<tr>
			<th>크루생성날짜</th>
			<td>${crew.crew_date }</td>
		</tr>
		<tr>
			<th>크루제한여부</th>
			<td>
				<select id="crew_type" >
					<option >${crew.crew_type }</option>
					<option value="T" >제한(T)</option>
					<option value="F">미제한(F)</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="수정" onclick="updateType()">
				<input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/admin/crewManagementList.do'" />
			</td>
		</tr>
		</table>
	</div>
</div>