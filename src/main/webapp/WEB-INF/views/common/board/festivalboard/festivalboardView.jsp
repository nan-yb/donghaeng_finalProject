<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<SCRIPT type="text/javascript">
	function goBack() {
		window.history.back();
	}

	function successFn(resp) {

		var list = resp.response.body.items.item;
		var imgdiv = $('div#imgdiv');
		if(list){
			if(Array.isArray(list)){
				$.each(list,function(idx, item) {
					imgdiv.append('<IMG src="'+item.originimgurl+'" style="margin-left: 100px; "/>');
				});				
			}else{
				imgdiv.append('<IMG src="'+list.originimgurl+'" style="margin-left: 180px; "/>');
			}
		}
	}

	function successFn2(resp) {

		var list = resp.response.body.items.item;
		
		var maindiv = $('div#writerDiv');
		
		maindiv.append('<P class="lead" style="margin-left: 50px;">'+list.overview+'</P>');
		maindiv.append('<P class="lead" style="margin-left: 50px;">자&nbsp;세&nbsp;히&nbsp;보&nbsp;기<br/>'+list.homepage+'</P>');
		maindiv.append('<P class="lead" style="margin-left: 50px;">문&nbsp;의<br/>'+list.telname+'&nbsp;&nbsp;'+list.tel+'</P>');
		
	}

	$(function() {
		var contentid = $('[name="contentid"]').val();

		//이미지를 가져오기 위한 작업
		$.ajax({
			url : "${pageContext.request.contextPath}/festivalboard/festivalboardView.do",
			method : "post",
			dataType : "json",
			data : {
				contentid : contentid
			},
			success : successFn
		});

		// 상세정보를 가져오기 위한 작업
		$.ajax({
			url : "${pageContext.request.contextPath}/festivalboard/festivalboardView2.do",
			method : "post",
			dataType : "json",
			data : {
				contentid : contentid
			},
			success : successFn2
		});

		var title = $('[name="title"]').val();
		titleArr = title.split(' ');
		retitle = '';
		for (var i = 0; i < titleArr.length; i++) {
			retitle += titleArr[i] + '&nbsp;&nbsp;';
			if (i % 3 == 0 + 1) {
				retitle += '<br>';
			}
		}
		$('#inputTitle').html(retitle);

		var mapx = $('[name="mapx"]').val();
		var mapy = $('[name="mapy"]').val();

		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new daum.maps.LatLng(mapy, mapx), // 지도의 중심좌표
			level : 3
		// 지도의 확대 레벨
		};

		var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

		// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
		var mapTypeControl = new daum.maps.MapTypeControl();

		// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
		// daum.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
		map.addControl(mapTypeControl,
				daum.maps.ControlPosition.TOPRIGHT);

		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
		var zoomControl = new daum.maps.ZoomControl();
		map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);

		// 마커가 표시될 위치입니다 
		var markerPosition = new daum.maps.LatLng(mapy, mapx);

		// 마커를 생성합니다
		var marker = new daum.maps.Marker({
			position : markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);

		// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
		// marker.setMap(null);   

	})
</SCRIPT>

<section
	class="s-content s-content--narrow s-content--no-padding-bottom">
	<article class="row format-standard">
		<DIV class="s-content__header col-full">
			<H1 class="s-content__header-title">
				<SPAN id="inputTitle"></SPAN>
			</H1>
		</DIV>

		<DIV class="s-content__media col-full">
			<DIV class="s-content__post-thumb" id="imgdiv">
			</DIV>
		</DIV>

		<DIV id="writerDiv" class="col-full s-content__main">

			<div id="map" style="width: 85%; height: 400px; margin-left: 50px;"></div>
			<script type="text/javascript"
				src="//dapi.kakao.com/v2/maps/sdk.js?
				appkey=cdd539404ca75df59eae1d4d6af80bc6&libraries=services"></script>
			<P class="lead" style="margin-left: 50px;">주&nbsp;&nbsp;&nbsp;소&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;${festivalVO.addr1 }</P>
			<BR />
			
		</DIV>

		<INPUT type="hidden" name="contentid" value="${festivalVO.contentid }" />
		<INPUT type="hidden" name="title" value="${festivalVO.title }" /> 
		<input type="hidden" name="mapx" value="${festivalVO.mapx }">
		<input type="hidden" name="mapy" value="${festivalVO.mapy }">
	</article>

	<br>
	<input type="button" value="뒤로가기" onclick="goBack();" />

</section>
