<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<style>
/* Style the tab */
.tab {
  overflow: hidden;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
}

/* Style the buttons inside the tab */
.tab button {
  background-color: inherit;
  float: left;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  transition: 0.3s;
  font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
  background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
  display: none;
  padding: 6px 12px;
  border: 1px solid #ccc;
  border-top: none;
}
</style>

<script>
function openCity(evt, cityName) {
	  var i, tabcontent, tablinks;
	  tabcontent = document.getElementsByClassName("tabcontent");
	  for (i = 0; i < tabcontent.length; i++) {
	    tabcontent[i].style.display = "none";
	  }
	  tablinks = document.getElementsByClassName("tablinks");
	  for (i = 0; i < tablinks.length; i++) {
	    tablinks[i].className = tablinks[i].className.replace(" active", "");
	  }
	  document.getElementById(cityName).style.display = "block";
	  evt.currentTarget.className += " active";
	}
	
// function ${pagingVO.funcName }(page){
// 	$("[name='searchForm']").find("[name='page']").val(page);
// 	$("[name='searchForm']").submit();
// }

// function makeList(data){
// 	var body ="";
// 	var memberList = data.dataList;
// 	if(memberList){
// 		$.each(memberList, function(i, member){
// 			body+="<tr><td>"+member.mem_id+"</td>";
// 			$.each(member.personList, function(i, person){
// 				body+="<td>"+person.person_name+"</td>";
// 				body+="<td>"+person.person_tel+"</td>"
// 				body+="<td>"+person.person_mail+"</td>"
// 			})
// 			body+="<td>"+member.mem_state+"</td>";
// 			body+="</tr>";
// 		})
// 	}else{
// 		body+="<tr><td colspan='4'>게시글 없음</td></tr>";
// 	}
// 	$('#navTag').html(data.pagingHTML);
// 	$('#bodyList').html(body);
// }

// $(function(){
// 	searchForm = $("[name='searchForm']");
	
// 	$(window).on("popstate",function(event){
// 		console.log(event);
// 		if (event.originalEvent.state) {
// 			var pagingVO = event.originalEvent.state;
// 			makeList(pagingVO);
// 		}else{
// 			location.reload();
// 		}
// 	});
	
// 	searchForm.ajaxForm({
// 		dataType: 'json',
// 		success: function(data){
			
// 			makeList(data);
// 			var pageNum = searchForm.find('[name="page"]').val();
// 			var queryString = searchForm.serialize();
			
// 			history.pushState(data, pageNum+"페이지", "?"+queryString);
// 			searchForm.find('[name="page"]').val("");
// 		},
// 	});
	
// 	<c:url value="/admin/travelTipReportView.do" var="travelTipReportView" />
	
// 	$("#bodyList").on("click", "tr",function(){
// 		var what = $(this).find("td:nth-child(1)").text();
// 		location.href="${travelTipReportView}?what="+what;
// 	});
	
// });
</script>

<div id="wrapper">
	<div id="page-wrapper">
	<br/><br/>
		<div class="tab">
			<button class="tablinks" onclick="openCity(event, 'travelTipBoard')">travelTipBoard</button>
			<button class="tablinks" onclick="openCity(event, 'postBoard')">postBoard</button>
			<button class="tablinks" onclick="openCity(event, 'freeBoard')">freeBoard</button>
		</div>

		<div id="travelTipBoard" class="tabcontent">
			<h4>여행TIP 게시판 신고 리스트</h4>
			<table class="table table-bordered table-striped table-hover">
				<thead class="thead-dark">
					<tr>
						<th>순번</th>
						<th>게시판 번호</th>
						<th>게시판 글쓴이(ID)</th>
<!-- 						<th>신고자(ID)</th> -->
						<th>누적 신고 수</th>
					</tr>
				</thead>
				
				<tbody id="bodyList">
					<c:choose>
						<c:when test="${not empty pagingVO.dataList }">
							<c:forEach items="${pagingVO.dataList }" var="travelTip">
								<tr>
									<td>${travelTip.rnum }</td>
									<td>${travelTip.board_no }</td>
									<c:forEach items="${travelTip.travelTipBoardList}" var="tip">
										<td>${tip.travel_tip_mem_id }</td>
									</c:forEach>
									<td>${travelTip.reportCount }</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="4">게시글이 없음</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
				
				<tfoot>
				<tr>
					<td colspan="4">
						<nav id="navTag">
						${pagingVO.pagingHTML }
						</nav>
					</td>
				</tr>
			</tfoot>
			</table>
		</div>

		<div id="postBoard" class="tabcontent">
			<h4>후기 게시판 신고 리스트</h4>
			<p>Paris is the capital of France.</p>
		</div>

		<div id="freeBoard" class="tabcontent">
			<h4>자유 게시판 신고 리스트</h4>
			<p>Tokyo is the capital of Japan.</p>
		</div>
	</div>
</div>