<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mypage.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
<section class="s-content">
	<div class="myinfo">
		<ul class="myinfo_my">
			<li>안녕하세요.<strong>${authMember.person_name}</strong>님.
			</li>
			<li class="myinfo_my_li">즐거운 여행되세요.</li>
		</ul>
		<div class="myinfo_coin">
			<i class="fas fa-coins fa-3x"></i>
			<p class="myinfo_coin_p">동행 마일리지</p>
			<span class="mileage">&nbsp;&nbsp;&nbsp;&nbsp;${member.mem_mileage}</span>
			<p class="myinfo_coin_p">점</p>
		</div>

		<div class="myinfo_menu">
			<c:choose>
				<c:when test="${authMember.person_type eq '1' }">
					<span class="myinfo_btn"><a
						href="${pageContext.request.contextPath }/member/mypage/myInfo.do"><i
							class="fas fa-user-alt"></i>&nbsp;개인정보관리</a></span>
				</c:when>
				<c:when test="${authMember.person_type eq '2' }">
					<span class="myinfo_btn"><a
						href="${pageContext.request.contextPath }/common/mypage/corporationViewInfo.do"><i
							class="fas fa-user-alt"></i>&nbsp;개인정보관리</a></span>
				</c:when>
			</c:choose>
			<span class="myinfo_btn"><a
				href="${pageContext.request.contextPath }/member/mypage/scheduleList.do"><i
					class="far fa-calendar-alt">&nbsp;</i>개인일정관리</a></span>
		</div>
	</div>
	<div class="reservation_check"><i class="fas fa-user-check fa-2x"></i>&nbsp;여행상품 예약내역</div>
	<ul class="reservation_ul">
		<li class="reservation_li1">예약내역<br><a style="padding-left: 20px;  color: black;">0건</a></li>
		<li class="reservation_li" style="padding-left: 9px;">패키지상품<br><a style="padding-left: 30px; color: white;">0건</a></li>
		<li class="reservation_li" style="padding-left: 15px;">호텔예약<br><a style="padding-left: 20px; color: white;">0건</a ></li>
		<li class="reservation_li" style="padding-left: 9px;">비행기예약<br><a style="padding-left: 30px; color: white;">0건</a></li>
		<li class="reservation_li" style="padding-left: 15px;">기차예약<br><a style="padding-left: 20px; color: white;">0건</a ></li>
	</ul>	
	
	
	
	
	
	
	
	
	
	
	
	
</section>




