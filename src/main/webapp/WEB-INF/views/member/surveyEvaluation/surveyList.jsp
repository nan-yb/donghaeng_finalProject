<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 검색해야할것은 기업명만 나중에는 설문 참여 했을 때 포인트 지급 -->

<script type = "text/javascript">
$(function(){
	var bodyTag = $('#bodylist');
	
	//뒤로가기
	$(window).on("popstate", function(event){
		console.log(event);
		location.reload();
	});
	
// 	//submit 결과를 ajaxForm이 가져옴
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
	 <c:url value="/member/surveyEvaluationView.do" var="surveyView" />
	 
	 $("#bodylist").on("click", "tr",function(){
		var what = $(this).find("td:nth-child(2)").text();
		location.href="${surveyView}?what="+what;
	 });
	
});
</script>


<section class="s-content">
	<div class="row masonry-wrap">
		<table class = "table-bordered table-striped table-hover">

			<thead class="thead-dark">
			<tr>			
				<th>순번</th>
				<th>설문 번호</th>
				<th>설문 제목</th>
				<th>기업</th>
				<th>시작일</th>
				<th>마감일</th>
			</tr>
		</thead>

		<tbody id="bodylist">
			<c:if test="${not empty survey }">
				<c:forEach items="${survey}" var="survey">
					<tr>
						<td>${survey.rnum }</td>
						<td>${survey.survey_no }</td>
						<td>${survey.survey_title }</td>
						<td>${survey.person_name}</td>
						<td>${survey.survey_date }</td>
						<td>${survey.survey_end_date }</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>

		</table>
	</div>
</section>