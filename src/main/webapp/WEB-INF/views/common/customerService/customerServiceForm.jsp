<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/customer-service.css">

<section class="s-content">
	<div class="div_h3">
		<h3>고객센터</h3>
	</div>
	<div class="total_div">
		<div class="th_comment">
			<div class="th_div1">
				<span class="span1">처음부터끝까지</span><br>
				<br> <span class="span1"><strong>고객님의 여행</strong>을 챙겨드리겠습니다.</span>
			</div>
		</div>
		<a href="<c:url value="/qnaboard/qnaboardList.do"/>"><ul class="ul_div1">
			<li style="margin-top: 50px;"><div class="far fa-comments fa-3x"></div></li>
			<li><i class="icon-a">묻고답하기</i></li>
		</ul></a>
		<a href=" <c:url value="/noticeboard/noticeboardRetrieve.do"/>"><ul class="ul_div2">
			<li style="margin-top: 50px;"><div class="fas fa-headset fa-3x"></div></li>
			<li><i class="icon-a">공지사항</i></li>
		</ul></a>
		<a href="<c:url value="/suggest/suggestList.do"/>"><ul class="ul_div2">
			<li style="margin-top: 50px;"><div class="fas fa-question fa-3x"></div></li>
			<li><i class="icon-a">건의사항</i></li>
		</ul></a>
		<a href="<c:url value="/customer/goodVoiceRetrieve.do"/>"><ul class="ul_div2">
			<li style="margin-top: 50px;"><div class="fas fa-microphone fa-3x"></div></li>
			<li><i style="margin-left: 20px;" class="icon-a">고객의소리</i></li>
		</ul></a>
		<a href="<c:url value="/member/surveyEvaluationList.do" />"><ul class="ul_div2">
			<li style="margin-top: 50px;"><div class="fas fa-clipboard-list fa-3x"></div></li>
			<li><i class="icon-a">설문조사</i></li>
		</ul></a>
	</div>

</section>