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
		var corList = data.dataList;
		if(corList){
			$.each(corList, function(i, cor){
				body+="<tr><td>"+cor.company_id+"</td>";
				$.each(cor.personList, function(i, person){
				body+="<td>"+person.person_name+"</td>";
				body+="<td>"+person.person_mail+"</td>";
				body+="<td>"+person.person_tel+"</td>";
				})
				body+="<td>"+cor.company_business_no+"</td>";
				body+="</tr>";
			})
		}else{
			body+="<tr><td colspan='5'>게시글 없음</td></tr>";
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
		
	});
	
	$(function(){
		<c:url value="/admin/corManagementView.do" var="corView" />
		$("#bodyList").on("click", "tr", function(){
			var what = $(this).find("td:nth-child(1)").text();
			location.href="${corView}?what="+what;
		});
	})
	
</script>
<div id="wrapper">
	<div id="page-wrapper">
		<table class="table table-bordered table-striped table-hover">
			<thead class="thead-dark">
				<tr>
					<th>여행사아이디</th>
					<th>여행사이름</th>
					<th>메일</th>
					<th>번호</th>
					<th>사업자번호</th>
				</tr>
			</thead>
			<tbody id="bodyList">
				<c:choose>
					<c:when test="${not empty pagingVO.dataList }">
						<c:forEach items="${pagingVO.dataList }" var="cor">
							<tr>
								<td>${cor.company_id }</td>
								<c:forEach items="${cor.personList}" var="person">
									<td>${person.person_name }</td>
									<td>${person.person_mail}</td>
									<td>${person.person_tel }</td>
								</c:forEach>
								<td>${cor.company_business_no }</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5">게시글이 없음</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5">
						<nav id="navTag">
						${pagingVO.pagingHTML }
						</nav>
					</td>
				</tr>
			</tfoot>
		</table>
		
		<form name="searchForm" action="${pageContext.request.contextPath }/admin/corManagementList.do" class="form-inline justify-content-center">
			<input type="hidden" name="page" />
			<select name="searchType" class="form-control">
				<option value="">검색 조건</option>
				<option value="id" >여행사아이디</option>
				<option value="name">여행사이름</option>
			</select>
			<input class="form-control" type="text" name="searchWord" value="${pagingVO.searchWord }"/>
			<input type="submit" value="검색" class="btn btn-success" />
		</form>
		<script>
			document.searchForm.searchType.value= "${pagingVO.searchType}";
		</script>
	</div>
</div>