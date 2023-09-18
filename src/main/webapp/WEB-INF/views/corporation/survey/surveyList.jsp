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
				console.log(survey.survey_title+"--"+testArray[1]+"-");
				survey.survey_title = survey.survey_title.replace(regex, Array(testArray[1].length).join("&nbsp;"));
			}
			body+="<tr><td>"+survey.rnum+"</td>";
			body+="<td>"+survey.survey_no+"</td>";
			body+="<td>"+survey.survey_title+"</td>";
			body+="<td>"+survey.company_id+"</td>";
			body+="<td>"+survey.survey_date+"</td>";
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
	
	 <c:url value="/survey/surveyView.do" var="surveyView" />
	 
	 $("#bodylist").on("click", "tr",function(){
		var what = $(this).find("td:nth-child(2)").text();
		location.href="${surveyView}?what="+what;
	 });
})

</script> 

<style>
	.xans-layout-poll { margin:0 0 20px; padding:0 0 16px; border-bottom:1px solid #e8e8e8; }
.xans-layout-poll h2 { height:30px; color:#2e2e2e; font-size:12px; }
.xans-layout-poll h3 { min-height:27px; padding:0 0 0 36px; color:#757575; font-size:12px; line-height:18px; background:url("http://img.echosting.cafe24.com/skin/base_ko_KR/layout/ico_poll_question.gif") no-repeat 10px 3px; }
.xans-layout-poll h3 strong { visibility:hidden; overflow:hidden; width:0; height:0; font-size:0; line-height:0; }
.xans-layout-poll h3 span { display:inline-block; padding:0 0 0 9px; border-left:1px solid #d9d9d9; }
.xans-layout-poll ul { margin:15px 0 14px 10px; color:#757575; font-size:11px; line-height:1.5; }
.xans-layout-poll li { margin:5px 0 0; }
.xans-layout-poll li input[type=radio] { width:13px; height:13px; vertical-align:-3px; *vertical-align:2px; margin:0 7px 0 0; }
.xans-layout-poll p { margin:0 10px 0 0; padding:0 23px 0 0; text-align:right; line-height:1.5; background:url("http://img.echosting.cafe24.com/skin/base_ko_KR/layout/ico_poll2.gif") no-repeat; }
.xans-layout-poll p a { padding:0 0 0 9px; text-decoration:none; background:url("http://img.echosting.cafe24.com/skin/base_ko_KR/layout/ico_poll1.gif") no-repeat; }
.xans-layout-poll p.btnResult { background-position:100% 1px; }
.xans-layout-poll p.btnPoll { background-position:100% -99px; }
.xans-layout-poll p.btnResult a { color:#757575; background-position:0 3px; }
.xans-layout-poll p.btnPoll a { color:#000; background-position:0 -97px; }
</style>

<div id="wrapper">

        <div id="page-wrapper">

		<div module="Layout_Poll">
			<!--@css(/css/module/layout/poll.css)-->
			<h2>온라인 설문조사</h2>
			<h3>
				<strong>질문 :</strong><span>{$poll_title}</span>
			</h3>
			<fieldset>
				<legend>설문조사 항목</legend>
				<ul>
					<li>{$poll}{$poll_name}</li>
					<li>{$poll}{$poll_name}</li>
					<li>{$poll}{$poll_name}</li>
					<li>{$poll}{$poll_name}</li>
				</ul>
				<p class="btnResult">
					<a href="#none" onclick="{$action_poll_result}">결과보기</a>
				</p>
				<p class="btnPoll">
					<a href="#none" onclick="{$action_poll_submit}">투표하기</a>
				</p>
			</fieldset>
		</div>
		<table border='1'>
	<thead>
		<tr>
			<th>순번</th>
			<th>설문번호</th>
			<th>제목</th>
			<th>기업ID</th>
			<th>작성날짜</th>
		</tr>
	</thead>
	<tbody id="bodylist">
		<c:choose>
			<c:when test="${not empty pagingVO.dataList }">
				<c:forEach items="${pagingVO.dataList }" var="survey">
					<tr>
						<td>${survey.rnum }</td>
						<td>${survey.survey_no }</td>
						<td>${survey.survey_title }</td>
						<td>${survey.company_id }</td>
						<td>${survey.survey_date }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="7">검색 조건에 맞는 글이 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
		<tr>
		<td><a href='<c:url value="/survey/surveyInsert.do"/>'>등록</a></td>
		</tr>
	</tbody>
	
	<tfoot>
		<tr>
			<td colspan="7" class="text-center">
				<nav aria-label="Page navigation" id="navtag">
				 	${pagingVO.pagingHTML } 
				</nav>
				
				<form action="<c:url value="/survey/surveyList.do"/>" name="searchForm" class="form-inline justify-content-center">
					<input type="hidden" name="page" />
					<input type="hidden" name="company_id" value="${authMember.person_id}" />
					<select class="form-control" name="searchType">
						<option value="">검색 조건</option>
						<option value="title">설문 제목</option>
					</select>
					<script type="text/javascript">
						$('[name="searchType"]').val("${param.searchType}");
					</script>
					<input class="form-control" type="text" name="searchWord" 
							value="${param.searchWord }"/>
					<input class="btn btn-success" type="submit" value="검색" />
					&nbsp;&nbsp;&nbsp;
				</form>
			</td>
		</tr>
	</tfoot>
</table>
</div>
</div>
	
    