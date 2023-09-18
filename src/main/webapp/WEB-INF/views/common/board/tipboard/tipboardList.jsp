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
	var travel_tipList = data.dataList;
	if(travel_tipList){
		$.each(travel_tipList,function(i, travel_tip){
			var testArray = regex.exec(travel_tip.travel_tip_title);
			if(testArray && testArray.length > 0){
				console.log(travel_tip.travel_tip_title+"--"+testArray[1]+"-");
				travel_tip.travel_tip_title = travel_tip.travel_tip_title.replace(regex, Array(testArray[1].length).join("&nbsp;"));
			}
			body+="<tr><td>"+travel_tip.rnum+"</td>";
			body+="<td>"+travel_tip.travel_tip_no+"</td>";
			body+="<td>"+travel_tip.travel_tip_title+"</td>";
			$.each(travel_tip.personList,function(i, person){
				body+="<td>"+person.person_name+"("+person.person_id+")</td>";
			})
// 			body+="<td>"+travel_tip.travel_tip_mem_id+"</td>";
			body+="<td>"+travel_tip.travel_tip_date+"</td>";
			body+="<td>"+travel_tip.travel_tip_view_count+"</td>";
			body+="<td>"+travel_tip.travel_tip_recommend_count+"</td></tr>";
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
	
	//submit 결과를 ajaxForm이 가져옴
// 	searchForm.ajaxForm({ 
// 	       dataType:  'json', 
// 	       success:  function (data){
// 			makeList(data);
// 			// 비동기 처리 성공 -> push state on history (state, title, url)
// 			var pageNum = searchForm.find('[name="page"]').val();
// 			var queryString = searchForm.serialize();
			
// 			history.pushState(data, pageNum+" 페이지", "?"+queryString);
// 			searchForm.find('[name="page"]').val("");
// 		},
// 	 });
	 
	//페이지context 이런거 안붙힐라고
	 <c:url value="/tipboard/tipboardView.do" var="tipboardView" />
	 
	 $("#bodylist").on("click", "tr",function(){
		var what = $(this).find("td:nth-child(2)").text();
		location.href="${tipboardView}?what="+what;
	 });
})

</script> 
<section class="s-content">
	<div class="row masonry-wrap">
<table class="table table-bordered table-striped table-hover">
	<thead class="thead-dark">
		<tr>
			<th>순번</th>
			<th>번호</th>
			<th>제목</th>
			<th>작성자(ID)</th>
			<th>작성일</th>
			<th>조회</th>
			<th>추천</th>
		</tr>
	</thead>
	<tbody id="bodylist" class="bodyList">
		<c:choose>
			<c:when test="${not empty pagingVO.dataList }">
				<c:forEach items="${pagingVO.dataList }" var="travel_tip">
					<tr>
						<td>${travel_tip.rnum }</td>
						<td>${travel_tip.travel_tip_no }</td>
						<td>${travel_tip.travel_tip_title }</td>
						<c:forEach items="${travel_tip.personList }" var="person">
								<td>${person.person_name }(${person.person_id})</td>
						</c:forEach>
						<td>${travel_tip.travel_tip_date }</td>
						<td>${travel_tip.travel_tip_view_count }</td>
						<td>${travel_tip.travel_tip_recommend_count }</td>
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
				
				<form action="<c:url value="/tipboard/tipboardRetrieve.do"/>" name="searchForm" class="form-inline justify-content-center">
					<input type="hidden" name="page" />
					<select class="form-control" name="searchType">
						<option value="">검색 조건</option>
						<option value="mem_id">작성자ID</option>
						<option value="title">제목</option>
						<option value="content">내용</option>
					</select>
					<script type="text/javascript">
						$('[name="searchType"]').val("${param.searchType}");
					</script>
					<input class="form-control" type="text" name="searchWord" 
							value="${param.searchWord }"/>
					<input class="btn btn-success" type="submit" value="검색" />
					&nbsp;&nbsp;&nbsp;
					<c:if test="${not empty authMember.person_id }">
						<input class="btn btn-success" type="button" 
								value="새글쓰기" onclick="location.href='<c:url value="/tipboard/tipboardInsert.do"/>';" 
						/>
					</c:if>
				</form>
			</td>
		</tr>
	</tfoot>
</table>
</div>
</section>
    	
	
    