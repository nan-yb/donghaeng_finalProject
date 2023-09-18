<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">
function ${pagingVO.funcName }(page){
	$("[name='searchForm']").find("[name='page']").val(page);
	$("[name='searchForm']").submit();
}
	
function makeList(data){
	var navTag = $('#navtag');
	var bodyTag = $('#bodylist');
	var body="";
	var regex = /^(\s+)/;
	var customvoiceList = data.dataList;
	if(customvoiceList){
		$.each(customvoiceList,function(i, customvoice){
			var testArray = regex.exec(customvoice.customvoice_title);
			if(testArray && testArray.length > 0){
				console.log(customvoice.customvoice_title+"--"+testArray[1]+"-");
				customvoice.customvoice_title = customvoice.customvoice_title.replace(regex, Array(testArray[1].length).join("&nbsp;"));
			}
			body+="<tr><td>"+customvoice.rnum+"</td>";
			body+="<td>"+customvoice.customvoice_no+"</td>";
			body+="<td>"+customvoice.customvoice_title+"</td>";
			body+="<td>"+customvoice.customvoice_date+"</td>";
			body+="<td>"+customvoice.company_id+"</td>";
			body+="<td>"+customvoice.mem_id+"</td>";
		})
	}else{
		body+="<tr><td colspan='7'>데이터없음</td></tr>";
	}
	navTag.html(data.pagingHTML);
	bodyTag.html(body);
}	
	
$(function(){
	
  	var navTag = $('#navtag');
	var bodyTag = $('#bodylist');
	var searchForm = $('[name="searchForm"]');
	
	//뒤로가기
	$(window).on("popstate", function(event){
		console.log(event);
		if(event.originalEvent.state){
			var pagingVO = event.originalEvent.state;
			makeList(pagingVO);
		}else{
			location.reload();
		}
	});
	
	searchForm.ajaxForm({ 
	       dataType:  'json', 
	       success:  function (data){
			makeList(data);
			// 비동기 처리 성공 -> push state on history (state, title, url)
			var pageNum = searchForm.find('[name="page"]').val();
			var queryString = searchForm.serialize();
			
			history.pushState(data, pageNum+" 페이지", "?"+queryString);
			searchForm.find('[name="page"]').val("");
		},
	 });
	 
	//페이지context 이런거 안붙힐라고
	 <c:url value="/customer/goodVoiceView.do" var="goodVoiceView" />
	 
	 $("#bodylist").on("click", "tr",function(){
		var what = $(this).find("td:nth-child(2)").text();
		location.href="${goodVoiceView}?what="+what;
	 });
})

</script> 
<section class="s-content">
	<div class="row masonry-wrap">
<table class="table table-bordered table-striped table-hover">
	<thead class="thead-dark">
		<tr>
			<th>순번</th>
			<th>글번호</th>
			<th>제목</th>
			<th>작성일</th>
			<th>업체ID</th>
			<th>작성자ID</th>
		</tr>
	</thead>
	<tbody id="bodylist">
		<c:choose>
			<c:when test="${not empty pagingVO.dataList }">
				<c:forEach items="${pagingVO.dataList }" var="customvoice">
					<tr>
						<td>${customvoice.rnum }</td>
						<td>${customvoice.customvoice_no }</td>
						<td>${customvoice.customvoice_title }</td>
						<td>${customvoice.customvoice_date }</td>
						<td>${customvoice.company_id }</td>
						<td>${customvoice.mem_id }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="7">검색 조건에 맞는 글이 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	
	<tfoot>
		<tr>
			<td colspan="7" class="text-center">
				<nav aria-label="Page navigation" id="navtag">
				 	${pagingVO.pagingHTML } 
				</nav>
				
				<form action="<c:url value="/customer/goodVoiceRetrieve.do"/>" name="searchForm" class="form-inline justify-content-center">
					<input type="hidden" name="page" />
					<select class="form-control" name="searchType">
						<option value="">검색 조건</option>
						<option value="company_id">업체ID</option>
						<option value="title">제목</option>
					</select>
					<select class="form-control" name="Type">
						<option value="G">칭찬</option>
						<option value="I">개선</option>
					</select>
					<script type="text/javascript">
						$('[name="searchType"]').val("${param.searchType}");
					</script>
					<input class="form-control" type="text" name="searchWord" 
							value="${param.searchWord }"/>
					<input class="btn btn-success" type="submit" value="검색" />
					&nbsp;&nbsp;&nbsp;
					<input class="btn btn-success" type="button" 
							value="새글쓰기" onclick="location.href='<c:url value="/customer/goodVoiceInsert.do"/>';" 
					/>
				</form>
			</td>
		</tr>
	</tfoot>
</table>
</div>
</section>
    	
	
    