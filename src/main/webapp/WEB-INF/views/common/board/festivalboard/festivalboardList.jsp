<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<SCRIPT type="text/javascript">
	function funcName(pageNo) {

		var areaCode = $('[name="selectLocal"]').val();

		$.ajax({
			url : "${pageContext.request.contextPath}/festival/festivalRetrieve.do",
			method : "post",
			dataType : "json",
			data : {
				areaCode : areaCode,
				pageNo : pageNo
			},
			success : successFn
		});
	}

	$(function() {

		masonryDiv = $("#masonryDiv");
		selectLocal = $("[name='selectLocal']");
		row = $("#row");

		festivalForm = $("#festivalInfo");

		selectLocal.on("change",function() {
			
			var areaCode = $(this).val();
			$.ajax({
				url : "${pageContext.request.contextPath}/festival/festivalRetrieve.do",
				method : "post",
				dataType : "json",
				data : {
					areaCode : areaCode
				},
				success : successFn
			});
		});

		selectLocal.trigger("change");

	})

	//   	자세히 보기 이벤트 처리
	function info(title, addr1, firstimage, contentid, mapx, mapy, tel) {
		var festivalInfoForm = $('[name="festivalInfo"]');
		$('[name="title"]').val(title);
		$('[name="addr1"]').val(addr1);
		$('[name="firstimage"]').val(firstimage);
		$('[name="contentid"]').val(contentid);
		$('[name="mapx"]').val(mapx);
		$('[name="mapy"]').val(mapy);
		$('[name="tel"]').val(tel);
		festivalInfoForm.submit();

	}

	//   	결과 처리 함수
	function successFn(resp) {

		var list = resp.response.body.items.item;
		var totalRecord = resp.response.body["totalCount"];

		var html = "";
		var paging = "";

		var array = new Array();

		if (list) {$.each(list,function(idx, li) {
				originTitle = list[idx].title;
				originAddr1 = list[idx].addr1;
				originFirstimage = list[idx].firstimage;
				originFirstimage2 = list[idx].firstimage2;

				// 				제목에 싱글쿼테이션 삭제
				if (originTitle.indexOf('"')) {
					modifyTitle = originTitle.replace(/"/g, "");
					if (modifyTitle.indexOf("'")) {
						modifyTitle.replace(/'/g, "");
						list[idx].title = modifyTitle;
					}
				}
				// 				제목에 더블쿼테이션 삭제
				if (originTitle.indexOf("'")) {
					modifyTitle = originTitle.replace(/'/g, "");
					if (modifyTitle.indexOf('"')) {
						modifyTitle.replace(/"/g, "");
						list[idx].title = modifyTitle;
					}
				}

				// 				주소 싱글쿼테이션 삭제
				if (originAddr1.indexOf('"')) {
					modifyAddr1 = originAddr1.replace(/"/g, "");
					list[idx].addr1 = modifyAddr1;
				}

				array[idx] = list[idx];
				
				html += '<article class="masonry__brick entry format-standard aos-init aos-animate data-aos="fade-up" ';
				html += '>';
				html += '<div class="entry__thumb">';
				html += '<a href="#" onclick="info(\''
						+ array[idx].title
						+ "','"
						+ array[idx].addr1
						+ "','"
						+ array[idx].firstimage
						+ "','"
						+ array[idx].contentid
						+ "','"
						+ array[idx].mapx
						+ "','"
						+ array[idx].mapy
						+ "','"						
						+ array[idx].tel
						+ '\');" class="entry__thumb-link">';
				if(li['firstimage'] == null){
					html += '<img src="${pageContext.request.contextPath}/images/new2.png" style="width: 400px; height: 200px;" alt="경로 없음"></a></div>';										
				}else{
					html += '<img src="'+li['firstimage']+'" style="width: 400px; height: 200px;" alt="경로 없음"></a></div>';
				}
				html += '<div class="entry__text">';
				html += '<div class="entry__header">';
				html += '<h1 class="entry__title">';
				html += '<a href="#" onclick="info(\''
						+ array[idx].title
						+ "','"
						+ array[idx].addr1
						+ "','"
						+ array[idx].firstimage
						+ "','"
						+ array[idx].contentid
						+ "','"
						+ array[idx].mapx
						+ "','"
						+ array[idx].mapy
						+ "','"
						+ array[idx].tel
						+ '\');">' + li["title"]+ '</a></h1></div></div></article>';
				html +=	'<br><br>';
				
			});
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

		masonryDiv.html(html);
		row.html(paging);
	}
</SCRIPT>

<section class = "s-content" id="section">

	<!-- 지역선택 -->
	<SELECT name="selectLocal">
		<OPTION value="">지역을 선택하세요</OPTION>
		<OPTION value="1">서울</OPTION>
		<OPTION value="2">인천</OPTION>
		<OPTION value="3">대전</OPTION>
		<OPTION value="4">대구</OPTION>
		<OPTION value="5">광주</OPTION>
		<OPTION value="6">부산</OPTION>
		<OPTION value="7">울산</OPTION>
		<OPTION value="8">세종</OPTION>
	</SELECT>
	
<!-- 	선택한 축제 정보를 담을 폼 -->
	<form modelAttribute="festivalVO" action="<c:url value="/festivalboard/festivalboardView.do"/>" name="festivalInfo">
		<INPUT type="hidden" name="title"></INPUT> 
		<INPUT type="hidden" name="addr1"></INPUT>
		<INPUT type="hidden" name="firstimage"></INPUT>
		<INPUT type="hidden" name="contentid"></INPUT>
		<INPUT type="hidden" name="mapx"></INPUT>
		<INPUT type="hidden" name="mapy"></INPUT>
		<INPUT type="hidden" name="tel"></INPUT>
	</form>
	
<!-- 	카드데이터를 출력해줄 부분 -->
	<div class="row masonry-wrap">
		<div class="masonry" style="position: relative; height: 300px" id="masonryDiv"></div>
	</div>
	
<!-- 	페이지 처리를 출력할 부분 -->
	<div class="row" id="row"></div>
</section>
