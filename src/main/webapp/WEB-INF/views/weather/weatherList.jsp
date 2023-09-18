<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://jqueryui.com/resources/demos/style.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>   
<script src="http://malsup.github.com/jquery.form.js"></script> 
<script>
	$(function(){
		tBodyList = $("#tBodyList");
		tBodyList2 = $("#tBodyList2");
		getAirport();
	});
	
	function airInfoList(resp,searchType){
		console.log(searchType);
		var html = "";
		
		var dataList = resp.data;
		
		dataList.sort(customSort);
		
		$.each(dataList,function(idx,temp){
				var charge =  parseInt(temp.fare) + parseInt(temp.airTax) + parseInt(temp.fuelChg);
				html += "<tr>";
				html += "<td>"+temp.carDesc+"</td>";
				html += "<td>"+temp.depTime+"</td>";
				html += "<td>"+temp.arrTime+"</td>";
				html += "<td>"+temp.classDesc+"</td>";
				html += "<td>"+charge+"</td>";
				html += "<td>"+temp.seat+"석</td>";
				html += "</tr>";
		});
		if(searchType == 'dep'){
			tBodyList.html(html);
		}else{
			tBodyList2.html(html);
		}
	}
	
	
	function customSort(a, b) {
		var c = parseInt(a.depTime); 
		var d = parseInt(b.depTime); 
		
		if (c == d) {
			return 0
		}
		return c > d ? 1 : -1;
	}

	function getAirport() {
		var array = ["KE","OZ","7C","LJ","TW","ZE","BX"];
		
		$.ajax({
			url : "${pageContext.request.contextPath}/airLine/air.do",
			method : 'post',
			dataType : "json",
			data : {
				searchType : "dep",
				airline : "KE",
				domestic_round_way : "RT",
				domestic_start : "GMP",
				domestic_end : "CJU",
				domestic_startDate : "2019.01.24",
				domestic_endDate : "2019.01.25",
				domestic_ea_adult : "1",
				domestic_ea_children : "0",
				domestic_ea_infant : "0",
				domestic_seat : "A",
				domestic_airline : array
				
			},
			success : function(resp) {
				var searchType = "dep";
				airInfoList(resp, searchType)
			},
			error : function(resp) {
				console.log(resp.status);
			}
		});

		$.ajax({
			url : "${pageContext.request.contextPath}/airLine/air.do",
			method : 'post',
			dataType : "json",
			data : {
				searchType : "arr",
				airline : "KE",
				domestic_round_way : "RT",
				domestic_start : "GMP",
				domestic_end : "CJU",
				domestic_startDate : "2019.01.24",
				domestic_endDate : "2019.01.25",
				domestic_ea_adult : "1",
				domestic_ea_children : "0",
				domestic_ea_infant : "0",
				domestic_seat : "A",
				domestic_airline : array
			},
			success : function(resp) {
				var searchType = "arr";
				airInfoList(resp, searchType)
			},
			error : function(resp) {
				console.log(resp.status);
			}
		});
	}

	// 	function getTotalWeather() {
	// 		$.ajax({
	// 			url : "${pageContext.request.contextPath}/weather/showWeather.do",
	// 			method: 'post',
	// 			dataType : "json",
	// 			success : function(resp){
	// 				console.log(resp);
	// 				var html = "";
	// 				tBodyList.html(html);
	// 			},
	// 			error : function(resp) {
	// 				console.log(resp.status);
	// 			}
	// 		});
	// 	}

	function getTodayWeather() {

		$.ajax({
			url : "${pageContext.request.contextPath}/weather/showWeather.do",
			method : 'post',
			data : {
				date : new Date()
			},
			dataType : "json",
			success : function(resp) {
				console.log(resp);
				var html = "";
				tBodyList.html(html);
			},
			error : function(resp) {
				console.log(resp.status);
			}
		});
	}

	function getCityWeather(city) {
		var city = city;
		var appkey = "fbedaa69bce997e934bfccccc40e5baa";

		$.ajax({
			url : "https://api.openweathermap.org/data/2.5/weather?q=" + city
					+ "&lang=en&appid=" + appkey + "&units=metric",
			method : 'get',
			dataType : "json",
			success : function(resp) {
				var html = "";
				if (resp.list) {
					console.log(resp.list);

				}
				tBodyList.html(html);
			},
			error : function(resp) {
				console.log(resp.status);
			}
		});
	}
</script>
</head>
<body>
	<div style="display: inline-block;">
		<table class="table" style="float: left; width: 48%">
			<thead>
				<tr>
					<td>항공편</td>
					<td>출발일시</td>
					<td>도착일시</td>
					<td>좌석</td>
					<td>기준요금</td>
					<td>좌석수</td>
				</tr>
			</thead>
			
			<tbody id="tBodyList">
				
			</tbody>
		</table>
		
		<table class="table"  style="float: right; width: 48%">
			<thead>
				<tr>
					<td>항공편</td>
					<td>출발일시</td>
					<td>도착일시</td>
					<td>좌석</td>
					<td>기준요금</td>
					<td>좌석수</td>
				</tr>
			</thead>
			
			<tbody id="tBodyList2">
				
			</tbody>
		</table>
	</div>
</body>
</html>