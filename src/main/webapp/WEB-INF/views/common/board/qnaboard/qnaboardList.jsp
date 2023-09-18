<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
function ${pagingVO.funcName }(page){
	$("[name='searchForm']").find("[name='page']").val(page);
	$("[name='searchForm']").submit();
}


function makeList(data){
	var body ="";
	var qnaboardList = data.dataList;
	if(qnaboardList){
		$.each(qnaboardList, function(i, qnaboard){
			body+="<tr><td>"+qnaboard.qnaboard_no+"</td>";
			body+="<td>"+qnaboard.qnaboard_title+"</td>";
			$.each(qnaboard.personList, function(i, person){
			body+="<td>"+person.person_name+"</td>";
			})
			body+="<td>"+qnaboard.qnaboard_date+"</td></tr>";
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
	
});

$(function(){
	<c:url value="/qnaboard/qnaboardView.do" var="boardView" />
	$("#bodyList").on("click", "tr", function(){
		var what = $(this).find("td:nth-child(1)").text();
		location.href="${boardView}?what="+what;
	});
})
</script>
<section class = "s-content">
	<div class="row masonry-wrap">
	<c:set var="mutable" value="false" />
	<c:if test="${not empty authMember and '1' eq authMember.person_type }">
		<c:set var="mutable" value="true" />
	</c:if>
	
	<c:if test="${mutable eq 'true' }">
		<input type="button" value="등록" onclick="location.href='${pageContext.request.contextPath}/qnaboard/qnaboardForm.do'"  />
	</c:if>
	
	<c:if test="${mutable eq 'false'}">
		<input type="button" value="등록" onclick="alert('회원으로 로그인을 해야합니다.')"  />
	</c:if>
	
<form action="${pageContext.request.contextPath }/qnaboard/qnaboardList.do" name="searchForm">
	<input name="page" type="hidden" />
	<input type="radio" name="searchType" value="writer">작성자
	<input type="radio" name="searchType" value="title">제목
	<input type="text" name="searchWord" value="${pagingVO.searchWord }" />
	<input type="submit" value="검색" />
	<script>
		document.searchForm.searchType.value= "${pagingVO.searchType}"
// 		$('[name="searchType"]').val("${param.searchType}");
	</script>
</form>

<table class="table table-bordered table-striped table-hover">
	<thead class="thead-dark">
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>작성자</th>
			<th>날짜</th>
		</tr>
	</thead>
	<tbody id="bodyList">
		<c:choose>
			<c:when test="${not empty pagingVO.dataList }">
				<c:forEach items="${pagingVO.dataList }" var="qnaboard">
					<tr>
						<td>${qnaboard.qnaboard_no }</td>
						<td>${qnaboard.qnaboard_title }</td>
						<c:forEach items="${qnaboard.personList}" var="person">
							<td>${person.person_name}</td>
						</c:forEach>
						<td>${qnaboard.qnaboard_date }</td>
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
</div>
</section>