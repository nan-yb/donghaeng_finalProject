<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<SCRIPT type="text/javascript">
	$.getContextPath  = function(){
		return "${pageContext.request.contextPath}";
	}

	$(function(){
		var selectLocal = $('[name="selectLocal"]');
		var selectCity = $('[name="selectCity"]');
		var selectCity = $('[name="selectCity"]');		
		var masonryDiv = $("#masonryDiv");
		var row = $("#row");
		
		selectLocal.on("change",function() {
			
			var areaCode = $(this).val();
			if(areaCode==''){
				selectCity.html('<option value="">시군구를 선택하세요</option>');
				masonryDiv.empty();
				row.empty();
				return;
			}
			$('[name="areaCodeValue"]').val(areaCode);
			
			var html ='';
	 		$.ajax({
	 			url : "${pageContext.request.contextPath}/lodging/areaCode.do",
	 			method : "post",
	 			data:{
	 				areaCode : areaCode
	 			},
	 			dataType : "json",			
	 			
	 			success : function successFn(resp) {	 				
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
		});
		
		selectCity.on("change",function() {
			
			var cityCode = $(this).val();
			$('[name="cityCodeValue"]').val(cityCode);
			var areaCode = $('[name="areaCodeValue"]').val();
			$.ajax({
	 			url : "${pageContext.request.contextPath}/lodging/cityCode.do",
	 			method : "post",
	 			data:{
	 				areaCode : areaCode,
	 				cityCode : cityCode
	 			},
	 			dataType : "json",			
	 			success : successCityFn
	 		});
		});		
		selectLocal.trigger("change");
		selectCity.trigger("change");
	});
	
	function successCityFn(resp){
		var list = resp.response.body.items.item;
		var totalRecord = resp.response.body["totalCount"];
		
		var entry = "";
		var paging = "";
		masonryDiv = $("#masonryDiv");
		row = $("#row");
		

// 		카드찍을부분
		if (list) {
			if(Array.isArray(list)){								
				$.each(list,function(idx, li) {
					
					entry += '<article class="masonry__brick entry format-standard aos-init aos-animate data-aos="fade-up">';
					entry += '<div class="entry__thumb">';
					entry += '<a href="#" class="entry__thumb-link">';
					if(li['firstimage'] == null){
						entry += '<img src="${pageContext.request.contextPath}/images/new2.png" style="width: 300px; height: 200px;" alt="이미지 없음"></a></div>';
					}else{
						entry += '<img src="'+li['firstimage']+'" style="width: 300px; height: 200px;" alt=""></a></div>';	
					}
					entry += '<div class="entry__text">';
					entry += '<div class="entry__header">';
					entry += '<h1 class="entry__title">';
					entry += '<a href="#" onclick="goView('+li['contentid']+')">' + li["title"].replace(/[a-z]/gi, '')+ '</a></h1></div></div></article>';
				});
			}else{
				entry += '<article class="masonry__brick entry format-standard aos-init aos-animate data-aos="fade-up">';
				entry += '<div class="entry__thumb">';
				entry += '<a href="#" class="entry__thumb-link">';
				if(list.firstimage == null){
					
					entry += '<img src="${pageContext.request.contextPath}/images/new2.png" style="width: 300px; height: 200px;" alt="이미지 없음"></a></div>';
				}else{
					entry += '<img src="'+list.firstimage+'" style="width: 300px; height: 200px;" alt=""></a></div>';					
				}
				entry += '<div class="entry__text">';
				entry += '<div class="entry__header">';
				entry += '<h1 class="entry__title">';
				entry += '<a href="#" onclick="goView('+list.contentid+')">' + list.title+ '</a></h1></div></div></article>';
			
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

		masonryDiv.html(entry);
		row.html(paging);
		
	}
	
	function goView(contentid) {
		var contentidForm = $('[name="contentidForm"]');
		$('[name="contentid"]').val(contentid);		
		contentidForm.submit();

	}
	
	
	function funcName(pageNo) {
		var areaCode = $('[name="areaCodeValue"]').val();
		var cityCode = $('[name="cityCodeValue"]').val();

		$.ajax({
			url : "${pageContext.request.contextPath}/lodging/cityCode.do",
			method : "post",
			dataType : "json",
			data : {
				areaCode : areaCode,
				cityCode : cityCode,
				pageNo : pageNo
			},
			success : successCityFn
		});
		
	}
	
</SCRIPT>


<section class="s-content s-content--narrow s-content--no-padding-bottom">

	<select name="selectLocal">
		<OPTION value="">지역을 선택하세요</OPTION>
		<OPTION value="1">서울</OPTION>
		<OPTION value="2">인천</OPTION>
		<OPTION value="3">대전</OPTION>
		<OPTION value="4">대구</OPTION>
		<OPTION value="5">광주</OPTION>
		<OPTION value="6">부산</OPTION>
		<OPTION value="7">울산</OPTION>
		<OPTION value="8">세종</OPTION>
	</select>
	
<!-- 	선택된 도시의 시군구를 선택하는 부분 -->
	<select name="selectCity"></select>
	
	<!-- 	카드데이터를 출력해줄 부분 -->
	<div class="row masonry-wrap" >
		<div class="masonry" style="position: relative; height: 500px" id="masonryDiv"></div>
	</div>
	
<!-- 	페이지 처리를 출력할 부분 -->
	<div class="row" id="row"></div>
	
	<input type="hidden" name="areaCodeValue">
	<input type="hidden" name="cityCodeValue">
	<form action="<c:url value='/lodging/lodgingView.do'/>" name="contentidForm">
		<input type="hidden" name="contentid">	
	</form>
	
</section>