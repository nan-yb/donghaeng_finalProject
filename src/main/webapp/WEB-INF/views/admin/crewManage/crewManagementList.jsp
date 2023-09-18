<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script>
	function ${pagingVO.funcName}(page){
		$("[name='searchForm']").find("[name='page']").val(page);
		$("[name='searchForm']").submit();
	}

	function makeList(data){
		var html = "";
		var crewList = data.dataList;
		if (crewList) {
			$.each(crewList, function(i, cor){
				html += "<tr><td>"+cor.crew_no+"</td>";
				html += "<td>"+cor.crew_name+"</td>";
				html += "<td>"+cor.crew_limit+"</td>";
				html += "<td>"+cor.crew_date+"</td></tr>";
			})
		} else {
			html += "<tr><td colspan='4'>게시글이 없음</td></tr>";
		}
		$('#navTag').html(data.pagingHTML);
		$('#bodyList').html(html);
	}
	
	
	$(function(){
		searchForm = $("[name='searchForm']");
		
		$(window).on("popstate", function(event){
			console.log(event);
			if (event.originalEvent.state) {
				var pagingVO = event.originalEvent.state;
				makeList(pagingVO);
			} else{
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
			}
		});
		
		$("#bodyList").on("click", "tr", function(){
			var what = $(this).find("td:nth-child(1)").text();
			location.href="${pageContext.request.contextPath }/admin/crewManagementView.do?what="+what;
		});
	});
	
	
	
</script>
<div id="wrapper">
	<div id="page-wrapper">
		<table class="table table-bordered table-striped table-hover">
			<thead class="thead-dark">
				<tr>
					<th>크루번호</th>
					<th>크루이름</th>
					<th>정원</th>
					<th>개설일</th>
				</tr>
			</thead>
			<tbody id="bodyList">
				<c:choose>
					<c:when test="${not empty pagingVO.dataList }">
						<c:forEach items="${pagingVO.dataList }" var="crew">
							<tr>
								<td>${crew.crew_no }</td>
								<td>${crew.crew_name }</td>
								<td>${crew.crew_limit}</td>
								<td>${crew.crew_date }</td>
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
		
		<form name="searchForm" action="${pageContext.request.contextPath }/admin/crewManagementList.do" class="form-inline justify-content-center">
			<input type="hidden" name="page" />
			<select name="searchType" class="form-control">
				<option value="">검색 조건</option>
				<option value="no" >크루번호</option>
				<option value="name">크루이름</option>
			</select>
			<input type="text" name="searchWord" value="${pagingVO.searchWord }" class="form-control"/>
			<input type="submit" value="검색" class="btn btn-success"/>
		</form>
		<script>
			document.searchForm.searchType.value= "${pagingVO.searchType}"
		</script>
	</div>
</div>