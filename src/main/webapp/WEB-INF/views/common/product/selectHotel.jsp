<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<SCRIPT type="text/javascript">

	$(function(){
		var selectCity = $('[name="selectCity"]');		
		var masonryDiv = $("#masonryDiv");
		var row = $("#row");
		
		var html ='';
		
		var hotelinfo = $('#hotelInfo');
		var hotelinput = '';
		$.ajax({
 			url : "${pageContext.request.contextPath}/common/package/selectBookList.do",
 			method : "post",
 			dataType : "json", 			
 			success : function successFun(resp) {
 				$.each(resp, function(idx, el){
 					hotelinput += '<input type="hidden" id="'+el.hotel_id+'" value="'+el.use_room_cnt+'">';
 				});
 				hotelinfo.html(hotelinput);
 			}
 		});
		
 		$.ajax({
 			url : "${pageContext.request.contextPath}/common/package/getSigugun.do",
 			method : "post",
 			dataType : "json", 			
 			success : function successFn(resp) {
 				console.log('ㄴ');
 				var list = resp.response.body.items.item;
 				if(list){
	 				html += '<option value="">시군구를 선택하세요</option>';
	 				if(Array.isArray(list)){
		 				$.each(list, function(idx, el){
		 					html += '<option value="'+el.code+'">'+el.name+'</option>';
		 				}); 							 					
	 				}else{
	 					html += '<option value="'+list.code+'">'+list.name+'</option>';
	 				}	 					
 				}
 				selectCity.html(html);
 				
 			}
 		});
		
		selectCity.on("change",function() {
			
			var cityCode = $(this).val();
			$('[name="cityCodeValue"]').val(cityCode);
			var areaCode = $('[name="areaCodeValue"]').val();
			$.ajax({
	 			url : "${pageContext.request.contextPath}/common/package/getHotelList.do",
	 			method : "post",
	 			data:{
	 				cityCode : cityCode
	 			},
	 			dataType : "json",			
	 			success : successCityFn
	 		});
		});
		
		selectCity.trigger("change");
	});
	
	function successCityFn(resp){
		console.log('ㅇ');
		var list = resp.response.body.items.item;
		var totalRecord = resp.response.body["totalCount"];
		
		entry = "";
		var paging = "";
		
		selectHotel = $("#selectHotel");
		masonryDiv = $("#masonryDiv");
		row = $("#row");

// 		카드찍을부분
		if (list) {
			var idArr = new Array();
			if(Array.isArray(list)){				
				$.each(list,function(idx, li) {
					contentid = li['contentid'];
					idArr[idx] = contentid;
					entry += '<article id="parts" class="masonry__brick entry format-standard aos-init aos-animate data-aos="fade-up">';
					entry += '<div class="entry__thumb">';
					if(li['firstimage'] == null){
						entry += '<img src="${pageContext.request.contextPath}/images/new2.png" style="width: 300px; height: 200px;" alt="이미지 없음"></div>';
					}else{
						entry += '<img src="'+li['firstimage']+'" style="width: 300px; height: 200px;" alt=""></div>';	
					}
					entry += '<div class="entry__text">';
					entry += '<div id="entry__header" class="entry__header'+idx+'">';
					entry += '<h1 class="entry__title">';
					entry += '<input type="hidden" id="contentid" value="'+li['contentid']+'">';					
					entry += '<a href="#" onclick="goView('+li['contentid']+')">' + li["title"].replace(/[a-z]/gi, '')+ '</a></h1>';
					entry += '</div></div></article>';
				});
				
				$.each(idArr, function(idxx, el){
					content = idArr[idxx];
	 				idx2 = 1;	 				
					$.ajax({
			 			url : "${pageContext.request.contextPath}/common/package/getHotelInfo.do",
			 			method : "post",
			 			data:{
			 				contentid : content
			 			},
			 			dataType : "json",			
			 			success : function success(resp){
			 				var res = resp.response.body.items.item;
							var ps = $('.entry__header'+idxx);
							
							var getinput = $('#hotelInfo input:nth-child('+idx2+')').attr('id');
							var getvalue = $('#hotelInfo input:nth-child('+idx2+')').val();
							
							console.log("??1 : "+getinput);
							console.log("??2 : "+getvalue);
							
							var roomcnt = res.roomcount.replace('실','')
							if(res.contentid == getinput){
								console.log(roomcnt-getvalue);
								ps.append('남은 방 수 : '+(roomcnt-getvalue)+'실' );
							}else{
				 				ps.append('남은 방 수 : '+res.roomcount);								
							}
			 				ps.append('<br>체크인 : '+res.checkintime);
			 				ps.append('<br>체크아웃 : '+res.checkouttime);
			 				ps.append('<br>문의전화 : '+res.reservationlodging);
			 				
			 				idx2++;			 				
	
			 			}
			 		});
				});
			}else{
				entry += '<article id="parts" class="masonry__brick entry format-standard aos-init aos-animate data-aos="fade-up">';
				entry += '<div class="entry__thumb">';
				if(list.firstimage == null){
					entry += '<img src="${pageContext.request.contextPath}/images/new2.png" style="width: 300px; height: 200px;" alt="이미지 없음"></div>';
				}else{
					entry += '<img src="'+list.firstimage+'" style="width: 300px; height: 200px;" alt=""></div>';					
				}
				entry += '<div class="entry__text">';
				entry += '<div class="entry__header">';				
				entry += '<h1 class="entry__title">';
				entry += '<input type="hidden" id="contentid" value="'+list.contentid+'">';
				entry += '<a href="#" onclick="goView('+list.contentid+')">' + list.title + '</a></h1></div></div></article>';
			
			}
		}else{
			
			entry += '<article class="masonry__brick entry format-quote aos-init aos-animate" data-aos="fade-up" style="position: absolute; left: 33.3333%; top: 0px;">';
            entry += '<div class="entry__thumb">';
            entry += '<blockquote>';
            entry += '<p>죄송합니다.<br/>선택하신 현재 지역에는<br/> 호텔이 존재하지 <br/>않습니다...</p>';
            entry += '</blockquote>';
            entry += '</div>';
            entry += '</article>';
			
		}
		
		
		var screenSize = 5;
		var blockSize = 5;
		var currentPage = resp.response.body["pageNo"];
		var totalPage = totalRecord % screenSize == 0 ? totalRecord
				/ screenSize : totalRecord / screenSize + 1;
		var startPage = parseInt((currentPage - 1) / blockSize) * blockSize + 1;
		var endPage = startPage + (blockSize - 1);
		var endRow = currentPage * screenSize;
		var startRow = endRow - (screenSize - 1);

		paging += '<nav class="pgn"><ul class="pagination justify-content-center">';
		if (startPage > blockSize) {

			paging += "<li><a class='pgn__num' href='javascript:funcName("
					+ (startPage - blockSize) + ");'>이전</a></li>";
		}
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		for (var page = startPage; page <= endPage; page++) {

			var active = '';
			var pageStr = page + '';
			if (page == currentPage) {
				paging += '<li><a class="pgn__num current" href="javascript:funcName('
					+ page + ');">' + pageStr + '</a></li>';
			}else{
				paging += '<li><a class="pgn__num " href="javascript:funcName('
						+ page + ');">' + pageStr + '</a></li>';				
			}
		}
		if (endPage < totalPage) {
			paging += "<li ><a class='pgn__num' href='javascript:funcName("
					+ (endPage + 1) + ");'>다음</a></li>";
		}
		paging += "</ul></nav>";
		
		$(document).on("click", "article", function(){
			var contentid = $(this).children('div.entry__text').children('div#entry__header').children('h1.entry__title').children('input#contentid').val();
			var hotelName = $(this).children('div.entry__text').children('div#entry__header').children('h1.entry__title').children('a').text();
			console.log();
			var hotel = "";
			hotel += '<p class="lead">선택하신 호텔 : '+hotelName+'</p>';
			hotel += '<form action="${pageContext.request.contextPath}/common/package/CheckfreeProductForm.do">';
			hotel += '<input type="hidden" name="contentid" value="'+contentid+'">';
			hotel += '<input type="hidden" name="hotelName" value="'+hotelName+'">';
			hotel += '<input type="submit" value="다음">';
			hotel += '</form>';
			selectHotel.html(hotel);
		});

		masonryDiv.html(entry);
		row.html(paging);
		
	}
	
	function goView(contentid) {
		var contentidForm = $('[name="contentidForm"]');
		$('[name="contentid"]').val(contentid);		
		contentidForm.submit();

	}
	
	
	function funcName(pageNo) {
		var cityCode = $('[name="cityCodeValue"]').val();

		$.ajax({
			url : "${pageContext.request.contextPath}/common/package/getHotelList.do",
			method : "post",
			dataType : "json",
			data : {
				cityCode : cityCode,
				pageNo : pageNo
			},
			success : successCityFn
		});
		
	}
	
</SCRIPT>


<section class="s-content s-content--narrow s-content--no-padding-bottom">
	
<!-- 	선택된 도시의 시군구를 선택하는 부분 -->
	<select name="selectCity"></select>
	
	<!-- 	카드데이터를 출력해줄 부분 -->
	<div class="row masonry-wrap" >
		<div class="masonry" style="position: relative; height: 500px" id="masonryDiv"></div>
	</div>
	
	<div id="selectHotel"></div>
<!-- 	페이지 처리를 출력할 부분 -->
	<div class="row" id="row"></div>
	
<!-- 	상세보기시 사용할 폼 -->
	<input type="hidden" name="cityCodeValue">
	<form action="${pageContext.request.contextPath}/lodging/lodgingView.do" name="contentidForm">
		<input type="hidden" name="contentid">	
	</form>
	
	
<!-- 	객실 갯수를 빼기 위해 사용 -->
	<div id="hotelInfo"></div>
	
	
	
	
</section>