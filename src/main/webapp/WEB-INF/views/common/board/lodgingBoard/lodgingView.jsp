<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<SCRIPT type="text/javascript">

	function successInfo(resp){
		var list = resp.response.body.items.item;
		var maindiv = $("div#mainDiv");
		
		$('#hotelName').text(list.title);
		
		maindiv.append('<P class="lead" style="margin-left: 50px;">'+list.overview+'</P>');
		if(list.homepage == null){
			maindiv.append('<P class="lead" style="margin-left: 50px;"></P>');			
		}else{
			maindiv.append('<P class="lead" style="margin-left: 50px;">자&nbsp;세&nbsp;히&nbsp;보&nbsp;기<br/>'+list.homepage+'</P>');			
		}
		if(list.telname == null){
			maindiv.append('<P class="lead" style="margin-left: 50px;">문&nbsp;의<br/>&nbsp;&nbsp;'+list.tel+'</P>');			
			
		}else{
			maindiv.append('<P class="lead" style="margin-left: 50px;">문&nbsp;의<br/>'+list.telname+'&nbsp;&nbsp;'+list.tel+'</P>');			
		}
		
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new daum.maps.LatLng(list.mapy, list.mapx), // 지도의 중심좌표
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
		var markerPosition = new daum.maps.LatLng(list.mapy, list.mapx);

		// 마커를 생성합니다
		var marker = new daum.maps.Marker({
			position : markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);

		// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
		// marker.setMap(null); 
		
		
	}
	
	function successImg(resp){
		var list = resp.response.body.items.item;
		var imgdiv = $('div#imgdiv');
		if(list){
			if(Array.isArray(list)){
				$.each(list,function(idx, item) {
					imgdiv.append('<IMG alt="이미지 없음" src="'+item.originimgurl+'" style="margin-left: 100px; "/><br>');
				});				
			}else{
				imgdiv.append('<IMG alt="이미지 없음" src="'+list.originimgurl+'" style="margin-left: 100px; "/><br>');
			}
		}
	}

	$(function(){
		var contentid = $('[name="contentid"]').val();
// 		console.log(contentid);
		$.ajax({
			url : "${pageContext.request.contextPath}/lodging/lodgingImg.do",
			method : "post",
			dataType : "json",
			data : {
				contentid : contentid
			},
			success : successImg
		});
		
		$.ajax({
			url : "${pageContext.request.contextPath}/lodging/lodgingInfo.do",
			method : "post",
			dataType : "json",
			data : {
				contentid : contentid
			},
			success : successInfo
		});
		
		
	});
	
</SCRIPT>

<section
	class="s-content s-content--narrow s-content--no-padding-bottom">

	<article class="row format-standard">

		<div class="s-content__header col-full">
			<h1 class="s-content__header-title" id="hotelName"></h1>
		</div>
		<!-- end s-content__header -->

		<div class="s-content__media col-full">
			<div class="s-content__post-thumb" id="imgdiv"></div>
		</div>
		<!-- end s-content__media -->

		<div class="col-full s-content__main" id="mainDiv">
		<div id="map" style="width: 85%; height: 400px; margin-left: 50px;"></div>
			<script type="text/javascript"
				src="//dapi.kakao.com/v2/maps/sdk.js?
				appkey=cdd539404ca75df59eae1d4d6af80bc6&libraries=services"></script>
		<input type="hidden" name="contentid" value="${contentid }">

<!-- 			<p class="lead">소제목</p> -->

<!-- 			<p>내용</p> -->

		</div>
		<!-- end s-content__main -->

	</article>
</section>