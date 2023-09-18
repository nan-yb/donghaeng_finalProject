<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<script type="text/javascript">

function ${pagingVO.funcName}(page){
	$("[name='searchForm']").find("[name='page']").val(page);
	$("[name='searchForm']").submit();
}
	
function makeList(data){
	var navTag = $('#navtag');
	var bodyTag = $('#bodylist');
	var body="";
	var regex = /^(\s+)/;
	var surveyList = data.dataList;
	if(surveyList){
		$.each(surveyList,function(i, survey){
			var testArray = regex.exec(survey.survey_title);
			if(testArray && testArray.length > 0){
				console.log(survey.evaluation_content+"--"+testArray[1]+"-");
				survey.evaluation_content = survey.evaluation_content.replace(regex, Array(testArray[1].length).join("&nbsp;"));
			}
			body+="<tr><td>"+survey.rnum+"</td>";
			body+="<td>"+survey.evaluation_no+"</td>";
			body+="<td>"+survey.evaluation_content+"</td>";
			body+="<td>"+survey.evaluation_count+"</td>";
			body+="<td>"+survey.evaluation_point+"</td>";
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
	
})

</script> 


<div id="wrapper">
        <div id="page-wrapper">
<table border='1'>
	<thead>
		<tr>
			<th>순번</th>
			<th>질문번호</th>
			<th>질문내용</th>
			<th>참여인원</th>
			<th>평균평점</th>
		</tr>
	</thead>
	<tbody id="bodylist">
	
		<c:choose>
			<c:when test="${not empty pagingVO.dataList }">
				<c:forEach items="${pagingVO.dataList }" var="survey">
					<tr>
						<td>${survey.rnum }</td>
						<td>${survey.evaluation_no }</td>
						<td>${survey.evaluation_content}</td>
						<td>${survey.evaluation_count }</td>
						<td>${survey.evaluation_point }</td>
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
				
				<form action="<c:url value="/survey/surveyView.do"/>" name="searchForm" class="form-inline justify-content-center">
					<input type="hidden" name="page" />
					<input type="hidden" name="what" value="${pagingVO.number}" />
				</form>
			</td>
		</tr>
	</tfoot>
</table>
</div>
</div>
	
    