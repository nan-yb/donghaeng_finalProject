<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
	var noticeList = data.dataList;
	if(noticeList){
		$.each(noticeList,function(i, notice){
			var testArray = regex.exec(notice.notice_title);
			if(testArray && testArray.length > 0){
				console.log(notice.notice_title+"--"+testArray[1]+"-");
				notice.notice_title = notice.notice_title.replace(regex, Array(testArray[1].length).join("&nbsp;"));
			}
			body+="<tr><td>"+notice.notice_no+"</td>";
			body+="<td>"+notice.notice_title+"</td>";
			$.each(notice.personList,function(i, person){
				body+="<td>"+person.person_id+"</td>";
				body+="<td>"+person.person_name+"</td>";
			})
			body+="<td>"+notice.notice_date+"</td>";
			body+="<td>"+notice.notice_view_count+"</td></tr>";
		})
	}else{
		body+="<tr><td colspan='6'>데이터없음</td></tr>";
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
	 <c:url value="/noticeboard/noticeboardView.do" var="noticeboardView" />
	 
	 $("#bodyList").on("click", "tr",function(){
		var what = $(this).find("td:nth-child(1)").text();
		location.href="${noticeboardView}?what="+what;
	 });
})
</script>


<section class = "s-content">
	<div class="row masonry-wrap">
		<table class="table table-bordered table-striped table-hover">
		  <thead class="thead-dark">
		    <tr>
		    	<th>번호</th>
		    	<th>제목</th>
		    	<th>작성자ID</th>
		    	<th>작성자</th>
		    	<th>날짜</th>
		    	<th>조회수</th>
		    </tr>
		  </thead>
		  
		  <tbody id = "bodyList">
		    <c:choose>
					<c:when test="${not empty pagingVO.dataList }">
						<c:forEach items="${pagingVO.dataList }" var="notice">
							<tr>
								<td>${notice.notice_no }</td>
								<td>${notice.notice_title }</td>
								<c:forEach items="${notice.personList }" var="person">
										<td>${person.person_id }</td>
										<td>${person.person_name }</td>
								</c:forEach>
								<td>${notice.notice_date }</td>
								<td>${notice.notice_view_count }</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="6">검색 조건에 맞는 글이 없음.</td>
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
						
						<form action="<c:url value ="/noticeboard/noticeboardRetrieve.do"/>" name = "searchForm" class="form-inline justify-content-center">
							<input type="hidden" name="page" />
							<select class="form-control" name="searchType">
								<option value="">검색 조건</option>
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
							<c:if test = "${authMember.person_type eq 3 }">
								<input class="btn btn-success" type="button" 
										value="새글쓰기" onclick="location.href='<c:url value="/noticeboard/noticeboardInsert.do"/>';" 
								/>
							</c:if>
						</form>
		  	</tr>
		  </tfoot>
		</table>
	</div>
</section>
