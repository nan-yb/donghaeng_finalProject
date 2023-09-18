<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="http://malsup.github.com/jquery.form.js"></script>

<script type="text/javascript">

function ${pagingVO.funcName }(page){
	$("[name='searchForm']").find("[name='page']").val(page);
	$("[name='searchForm']").submit();
}
 

function makeList(data){
		var body ="";
		var regex = /^(\s+)/;
		var boardList = data.dataList;
		var navTag = $('#navtag');
		var bodyTag = $('#bodyList');
		if(boardList){
			$.each(boardList, function(i, board){
				var testArray = regex.exec(board.board_title);
				if (testArray && testArray.length > 0) {
					console.log(board.board_title+"- -"+testArray[1]+"-");
					board.board_title = board.board_title.replace(regex, Array(testArray[1].lenght).join("&nbsp;"));
				}

				body+="<tr><td>"+board.suggest_board_no+"</td>";
				body+="<td>"+board.suggest_board_title+"</td>";
				$.each(board.personList,function(i, person){
					body+="<td>"+person.person_name+"("+board.mem_id+")</td>";
				});
				body+="<td>"+board.suggest_board_date+"</td></tr>";				
			})
		}else{
			body+="<tr><td colspan='4'>게시글 없음</td></tr>";
		}
		navTag.html(data.pagingHTML);
		bodyTag.html(body);
	}

	$(function(){
		searchForm = $("[name ='searchForm']");
		
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
		<c:url value="/suggest/suggestView.do" var="boardView" />
		
		$("#bodyList").on("click", "tr", function(){
			var what = $(this).find("td:nth-child(1)").text();
			location.href="${boardView}?what="+what;
		});
	});	
	
</script>


<section class="s-content">
	<div class="row masonry-wrap">
		<table>
			<thead>
				<tr>
					<th>글번호</th>
					<th>글제목</th>
					<th>글쓴이</th>
					<th>날짜</th>
				</tr>
			</thead>
			<tbody id="bodyList">
				<c:choose>
					<c:when test="${not empty pagingVO.dataList }">
						<c:forEach items="${pagingVO.dataList }" var="suggest">
							<tr>
								<td>${suggest.suggest_board_no }</td>
								<td>${suggest.suggest_board_title }</td>
						<c:forEach items="${suggest.personList }" var="person">
								<td>${person.person_name }(${suggest.mem_id})</td>
								<td>${suggest.suggest_board_date }</td>
						</c:forEach>		
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="4">검색 조건에 맞는 글이 없음</td>
						</tr>
					</c:otherwise>
				</c:choose>
			<tfoot>
				<tr>
					<td colspan="4" class="text-center">
						<nav aria-label="Page navigation" id="navtag">
							${pagingVO.pagingHTML}
						</nav>

						<form action="<c:url value="/suggest/suggestList.do"/>" name="searchForm">
							<input type="hidden" name="page" /> <select class="text-center"
								name="searchType">
								<option value="">전체</option>
								<option value="mem_id">작성자ID</option>
								<option value="title">제목</option>
								<option value="content">내용</option>
							</select>
							<script type="text/javascript">
								$('[name="searchType"]').val("${param.searchType}");
							</script>
							<input class="text-center" type="text" name="searchWord" value="${param.searchWord }" /> 
							<input class="btn btn-success" type="submit" value="검색" /> &nbsp;&nbsp;&nbsp; 
							<input class="btn btn-success" type="button" value="새글쓰기"
								onclick="location.href='<c:url value="/suggestboard/suggestboardInsert.do"/>'" />
						</form>

					</td>
				</tr>
			</tfoot>
		</table>
		<!-- 		<INPUT type="button" value="글쓰기" -->
		<%-- 			onclick="location.href='<c:url value="/suggestboard/suggestboardInsert.do"/>'" /> --%>
	</div>
</section>