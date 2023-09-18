<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script>
	function ${pagingVO.funcName }(page){
		$("[name='searchForm']").find("[name='page']").val(page);
		$("[name='searchForm']").submit();
	}
	
	function makeList(data){
		var body ="";
		var memberList = data.dataList;
		if(memberList){
			$.each(memberList, function(i, member){
				body+="<tr><td>"+member.mem_id+"</td>";
				$.each(member.personList, function(i, person){
					body+="<td>"+person.person_name+"</td>";
					body+="<td>"+person.person_tel+"</td>"
					body+="<td>"+person.person_mail+"</td>"
				})
				body+="<td>"+member.mem_state+"</td>";
				body+="</tr>";
			})
		}else{
			body+="<tr><td colspan='4'>게시글 없음</td></tr>";
		}
		$('#navTag').html(data.pagingHTML);
		$('#bodyList').html(body);
	}
	
	$(function(){
		searchForm = $("[name='searchForm']");
		
		$(window).on("popstate",function(event){
			console.log(event);
			if (event.originalEvent.state) {
				var pagingVO = event.originalEvent.state;
				makeList(pagingVO);
			}else{
				location.reload();
			}
		});
		
		searchForm.ajaxForm({
			dataType: 'json',
			success: function(data){
				
				makeList(data);
				var pageNum = searchForm.find('[name="page"]').val();
				var queryString = searchForm.serialize();
				
				history.pushState(data, pageNum+"페이지", "?"+queryString);
				searchForm.find('[name="page"]').val("");
			},
		});
		
		<c:url value="/admin/blackListView.do" var="blackListView" />
		
		$("#bodyList").on("click", "tr",function(){
			var what = $(this).find("td:nth-child(1)").text();
			location.href="${blackListView}?what="+what;
		});
		
	});
</script>

<div id="wrapper">
	<div id="page-wrapper">
		
		
		<table class="table table-bordered table-striped table-hover">
			<thead class="thead-dark">
				<tr>
					<th>회원아이디</th>
					<th>회원이름</th>
					<th>전화번호</th>
					<th>메일</th>
					<th>상태</th>
				</tr>
			</thead>
			<tbody id="bodyList">
				<c:choose>
					<c:when test="${not empty pagingVO.dataList }">
						<c:forEach items="${pagingVO.dataList }" var="member">
							<tr>
								<td>${member.mem_id }</td>
								<c:forEach items="${member.personList}" var="person">
									<td>${person.person_name }</td>
									<td>${person.person_tel }</td>
									<td>${person.person_mail}</td>
								</c:forEach>
								<td>${member.mem_state }</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="4">게시글이 없음</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
						<nav id="navTag">
						${pagingVO.pagingHTML }
						</nav>
					</td>
				</tr>
			</tfoot>
		</table>
		
		<form name="searchForm" action="${pageContext.request.contextPath }/admin/blackListRetrieve.do" class="form-inline justify-content-center">
			<input type="hidden" name="page" />
			<select name="searchType" class="form-control">
				<option value="">검색 조건</option>
				<option value="id" >회원아이디</option>
				<option value="name">회원이름</option>
			</select>
			<input type="text" name="searchWord" value="${pagingVO.searchWord }" class="form-control"/>
			<input type="submit" value="검색" class="btn btn-success" />
		</form>
		<script>
			document.searchForm.searchType.value= "${pagingVO.searchType}"
		</script>
	</div>
</div>