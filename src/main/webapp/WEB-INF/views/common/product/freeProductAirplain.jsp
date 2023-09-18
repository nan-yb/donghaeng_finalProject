<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link href="${cPath}/css/product/airPlain.css"></link>


<style>
#person{
   width: 5%;
   height: 20%;
   display: inline;
}

.airLineSelect {
   width: 47%;
   height: 20%;
   display: inline;
}

.personSelect {
   width: 15%;
   height: 20%;
   display: inline;
}
table.type09 {
   border-collapse: collapse;
   text-align: left;
   line-height: 1.5;
}

table.type09 thead th {
   padding: 10px;
   font-weight: bold;
   vertical-align: top;
   color: #369;
   border-bottom: 3px solid #036;
}

table.type09 tbody th {
   width: 150px;
   padding: 10px;
   font-weight: bold;
   vertical-align: top;
   border-bottom: 1px solid #ccc;
   background: #f3f6f7;
}

table.type09 td {
   width: 350px;
   padding: 10px;
   vertical-align: top;
   border-bottom: 1px solid #ccc;
}
</style>


<script>

$(function(){
	deptCount = 0;
	arrCount = 0;
	
	airLineSelectBox = $("#airLineSelectBox");
	
	searchBtn = ("#searchBtn");
	
	arriveSelect = $("#arriveSelect");
	deptSelect = $("#deptSelect");
	
	tBodyList = $("#tBodyList");
	tBodyList2 = $("#tBodyList2");
	
	
	adultSelect = $("#adultSelect");
	childrenSelect = $("#childrenSelect");
	infantSelect = $("#infantSelect");
	seatSelect = $("#seatSelect");
	
	searchForm= $("#searchForm");
	
	startDate = $("#startDate");
	endDate = $("#endDate");
	
	
	airLineOptionGenerate();
	
	  
	$('#deptSelect option[value="${reservationInfo.selectDept}"]').attr('selected','selected');
	   
	deptSelect.trigger("change");   
	arriveSelect.trigger("change"); 
	
	
	$('#arriveSelect option[value="${reservationInfo.selectArr}"]').attr('selected','selected');

	
	
	$('#adultSelect option[value="${reservationInfo.adult}"]').attr('selected','selected');
	$('#childrenSelect option[value="${reservationInfo.children}"]').attr('selected','selected');
	$('#infantSelect option[value="${reservationInfo.infant}"]').attr('selected','selected');
	
	
	
	
	
	
	deptInfo = new Array();
	arrInfo  = new Array();
	
	//table 클릭 이벤트 항공 코드 가져오는 함수
	$("#tBodyList").delegate("tr" ,"click", function(){
		deptInfo = new Array();
// 		var what = $(this).find("td:nth-child(1)").text();
		var tr = $(this);
		var td = tr.children();
		var html = "";
		var count = 0;
		
		td.each(function(i){
			if(i==0){
				html+= "<td>"+deptSelect.val() +"->" + arriveSelect.val() +"</td>";	
				deptInfo.push(td.eq(i).text());
				
			}else if(i==2){
				var totalDate = td.eq(i).text();
				if((td.eq(i).text()).length==3){
					totalDate = "0"+td.eq(i).text();
				}
				
				var hour = totalDate;
				var minute = totalDate;
				
				hour = hour.substring(0,2);
				minute = minute.substring(2,4);
				
				var resvDate = endDate +" " + hour + ":" + minute;
				html+= "<td>"+resvDate +"</td>";	
			
				deptInfo.push(resvDate);
			}else if(i==3){
				var totalDate = td.eq(i).text();
				if((td.eq(i).text()).length==3){
					totalDate = "0"+td.eq(i).text();
				}
				
				var hour = totalDate;
				var minute = totalDate;
				
				hour = hour.substring(0,2);
				minute = minute.substring(2,4);
				
				var resvDate = endDate +" " + hour + ":" + minute;
				
				html+= "<td>"+resvDate +"</td>";	
				deptInfo.push(resvDate);
			}else if(i==6){
				count =  parseInt(childrenSelect.val())+parseInt(infantSelect.val())+parseInt(adultSelect.val());
				html+= "<td>"+ count +"/"+td.eq(i).text()+"</td>";
				deptInfo.push(count);
			}else{
				deptInfo.push(td.eq(i).text());
				html+= "<td>"+td.eq(i).text()+"</td>";	
			}		
			
		});
		
		
		$("#deptTr").html(html);
	});
	
	$("#tBodyList2").delegate("tr" ,"click", function(){
// 		var what = $(this).find("td:nth-child(1)").text();
		arrInfo = new Array();
		
		var tr = $(this);
		var td = tr.children();

		var html = "";
		var count = 0;
		
		td.each(function(i){
			if(i==0){
				html+= "<td>"+arriveSelect.val() +"->" + deptSelect.val() +"</td>";	
				arrInfo.push(td.eq(i).text());
			}else if(i==2){
				var totalDate = td.eq(i).text();
				if((td.eq(i).text()).length==3){
					totalDate = "0"+td.eq(i).text();
				}
				
				var hour = totalDate;
				var minute = totalDate;
				
				hour = hour.substring(0,2);
				minute = minute.substring(2,4);
				
				var resvDate = endDate +" " + hour + ":" + minute;
				
				html+= "<td>"+resvDate +"</td>";	
				arrInfo.push(resvDate);
			}else if(i==3){
				var totalDate = td.eq(i).text();
				if((td.eq(i).text()).length==3){
					totalDate = "0"+td.eq(i).text();
				}
				
				var hour = totalDate;
				var minute = totalDate;
				hour = hour.substring(0,2);
				minute = minute.substring(2,4);
				
				var resvDate = endDate +" " + hour + ":" + minute;
				
				html+= "<td>"+resvDate +"</td>";	
				arrInfo.push(resvDate);
			}else if(i==6){
				count =  parseInt(childrenSelect.val())+parseInt(infantSelect.val())+parseInt(adultSelect.val());
				arrInfo.push(count);
				html+= "<td>"+ count +"/"+td.eq(i).text()+"</td>";	
			}else{
				arrInfo.push(td.eq(i).text());
				html+= "<td>"+td.eq(i).text()+"</td>";	
			}
			
			
		});
		
		$("#arrTr").html(html);
	});
	
	
	
	$("#searchBtn").on("click", function(){
		event.preventDefault();
		dept = deptSelect.val();
		arr = arriveSelect.val();
		startDate = $("#startDate").val();
		endDate = $("#endDate").val();
		adult = adultSelect.val();
		children = childrenSelect.val(); 
		infant = infantSelect.val();
		seat = seatSelect.val();
		
		startDate = replaceAll(startDate , "-" ,".");
		endDate = replaceAll(endDate , "-" ,".");
		

		var test = "";
		$("input:checkbox[name='airLineList']:checked").each(function(){
			test = $(this).val();
		});
		
		var search = {
			airline : test,
			domestic_round_way : "RT",
			domestic_start : dept,
			domestic_end : arr,
			domestic_startDate : startDate,
			domestic_endDate : endDate,
			domestic_ea_adult : adult,
			domestic_ea_children : children,
			domestic_ea_infant : infant,
			domestic_seat : seat,
			domestic_airline : test
		};
		
	
		getAirport(search);
	});
	
	
		
		
});
	
function airLineOptionGenerate(){
	
	var airport =[
		{name:"서울" , airport:"GMP" , deptAir:[ {name : "대구" , airLine : "TAE"},
		                                      	{name : "부산" , airLine : "PUS"},
		                                      	{name : "사천" , airLine : "HIN"},
		                                      	{name : "여수" , airLine : "RSU"},
				                                {name : "울산" , airLine : "USN"},
				                                {name : "광주" , airLine : "KWJ"},
									            {name : "제주" , airLine : "CJU"},
									            {name : "포항" , airLine : "KPO"}]},
		
		{name:"부산" , airport:"PUS", deptAir:[ {name : "서울" , airLine :  "GMP"},
									            {name : "제주" , airLine : "CJU"}]},
									            
		{name:"제주" , airport:"CJU", deptAir:[ {name : "서울" , airLine :  "GMP"},
		                                       	{name : "대구" , airLine : "TAE"},
		                                      	{name : "부산" , airLine : "PUS"},
		                                      	{name : "사천" , airLine : "HIN"},
		                                      	{name : "여수" , airLine : "RSU"},
				                                {name : "울산" , airLine : "USN"},
									            {name : "광주" , airLine : "KWJ"},
									            {name : "군산" , airLine : "KUV"},
									            {name : "청주" , airLine : "CJJ"},
									            {name : "포항" , airLine : "KPO"}]},
									            
		{name:"대구" , airport:"TAE", deptAir:[ {name : "서울" , airLine :  "GMP"},
									            {name : "제주" , airLine : "CJU"}]},
									            
		{name:"울산" , airport:"USN", deptAir:[ {name : "서울" , airLine :  "GMP"},
									            {name : "제주" , airLine : "CJU"}]},
									            
		{name:"청주" , airport:"CJJ", deptAir:[ {name : "제주" , airLine : "CJU"}]},
		
		{name:"광주" , airport:"KWJ", deptAir:[ {name : "서울" , airLine :  "GMP"},
									            {name : "제주" , airLine : "CJU"}]},
									            
		{name:"여수" , airport:"RSU", deptAir:[ {name : "서울" , airLine :  "GMP"},
									            {name : "제주" , airLine : "CJU"}]},
									            
		{name:"포항" , airport:"KPO", deptAir:[ {name : "서울" , airLine :  "GMP"}]},
		
		{name:"사천" , airport:"HIN", deptAir:[ {name : "서울" , airLine :  "GMP"},
									            {name : "제주" , airLine : "CJU"}]},
									            
		{name:"군산" , airport:"KUV", deptAir:[{name : "제주" , airLine : "CJU"}]}
	];
	
	var pattern = "<option value='%V'>%T</option>";
	
	deptSelect.on("change",function(){
		var dv = $(this).val();
		var options = pattern.replace("%V", "")
					 .replace("%T", "도착 공항 선택");
		$.each(airport, function(idx, temp){
			if(dv == temp.airport){
				$.each(temp.deptAir , function(idx,tmp){
					options += pattern.replace("%V", tmp.airLine)
							  .replace("%T", tmp.name);
				});
			}
		});
		arriveSelect.html(options);
	});

	var adultList =[
	      {name:"성인 0" , person:"0"},
	      {name:"성인 1" , person:"1"},
	      {name:"성인 2" , person:"2"},
	      {name:"성인 3" , person:"3"},
	      {name:"성인 4" , person:"4"},
	      {name:"성인 5" , person:"5"},
	      {name:"성인 6" , person:"6"},
	      {name:"성인 7" , person:"7"},
	      {name:"성인 8" , person:"8"},
	      {name:"성인 9" , person:"9"}
	];
	var childrenList =[
	      {name:"소아 0" , person:"0"},
	      {name:"소아 1" , person:"1"},
	      {name:"소아 2" , person:"2"},
	      {name:"소아 3" , person:"3"},
	      {name:"소아 4" , person:"4"},
	      {name:"소아 5" , person:"5"},
	      {name:"소아 6" , person:"6"},
	      {name:"소아 7" , person:"7"},
	      {name:"소아 8" , person:"8"},
	      {name:"소아 9" , person:"9"}
	];
	var infantList =[
	      {name:"유아 0" , person:"0"},
	      {name:"유아 1" , person:"1"},
	      {name:"유아 2" , person:"2"},
	      {name:"유아 3" , person:"3"},
	      {name:"유아 4" , person:"4"},
	      {name:"유아 5" , person:"5"},
	      {name:"유아 6" , person:"6"},
	      {name:"유아 7" , person:"7"},
	      {name:"유아 8" , person:"8"},
	      {name:"유아 9" , person:"9"}
	];
	
	var seatList =[
	      {name:"전체" , type:"A"},
	      {name:"일반석" , type:"Y"},
	      {name:"비즈니스석" , type:"C"},
	      {name:"할인석" , type:"S"},
	      {name:"특가석" , type:"T"}
	];
	
	$.each(airport,function(idx,temp){
		deptSelect.append($('<option>',{
			value : temp.airport,
			text : temp.name
		}));
	});
	
	
	$.each(adultList,function(idx,temp){
		adultSelect.append($('<option>',{
			value : temp.person,
			text : temp.name
		}));
	});
	
	$.each(childrenList,function(idx,temp){
		childrenSelect.append($('<option>',{
			value : temp.person,
			text : temp.name
		}));
	});
	
	$.each(infantList,function(idx,temp){
		infantSelect.append($('<option>',{
			value : temp.person,
			text : temp.name
		}));
	});
	
	$.each(seatList,function(idx,temp){
		seatSelect.append($('<option>',{
			value : temp.type,
			text : temp.name
		}));
	});
};
	
function replaceAll(str, searchStr, replaceStr) {
    return str.split(searchStr).join(replaceStr);
}

function customSort(a, b) {
	var c = parseInt(a.depTime); 
	var d = parseInt(b.depTime); 
	
	if (c == d) {
		return 0
	}
	return c > d ? 1 : -1;
}

	function getAirport(Object) {
		
		console.log(Object.airline);
		
		var depthtml = "";
		
			$.ajax({
				url : "${pageContext.request.contextPath}/traffic/airDept.do",
				method : 'post',
				dataType : "json",
				data : {
					searchType : "dep",
					airline : Object.airline,
					domestic_round_way : "RT",
					domestic_start : Object.domestic_start,
					domestic_end : Object.domestic_end,
					domestic_startDate : Object.domestic_startDate,
					domestic_endDate : Object.domestic_endDate,
					domestic_ea_adult : Object.domestic_ea_adult,
					domestic_ea_children : Object.domestic_ea_children,
					domestic_ea_infant : Object.domestic_ea_infant,
					domestic_seat : Object.domestic_seat,
					domestic_airline : Object.airline
				},
				
				success : function(resp) {
					var array = resp.data;
					array.sort(customSort);
					
					$.each(array,function(idx,tmp){
						var charge =  parseInt(tmp.fare) + parseInt(tmp.airTax) + parseInt(tmp.fuelChg);
						depthtml += "<tr>";
						depthtml += "<td style='display:none;'>"+tmp.code+"</td>";
						depthtml += "<td>"+tmp.carDesc+"</td>";
						depthtml += "<td>"+tmp.depTime+"</td>";
						depthtml += "<td>"+tmp.arrTime+"</td>";
						depthtml += "<td>"+tmp.classDesc+"</td>";
						depthtml += "<td>"+charge+"</td>";
						depthtml += "<td>"+tmp.seat+"석</td>";
						depthtml += "</tr>";
					});
					tBodyList.html(depthtml);
				},
				
				error : function(resp) {
					console.log(resp.status);
				}
			});
	
			
			var html = "";
			
			$.ajax({
				url : "${pageContext.request.contextPath}/traffic/airArrive.do",
				method : 'post',
				dataType : "json",
				data : {
					searchType : "arr",
					airline : Object.airline,
					domestic_round_way : "RT",
					domestic_start : Object.domestic_start,
					domestic_end : Object.domestic_end,
					domestic_startDate : Object.domestic_startDate,
					domestic_endDate : Object.domestic_endDate,
					domestic_ea_adult : Object.domestic_ea_adult,
					domestic_ea_children : Object.domestic_ea_children,
					domestic_ea_infant : Object.domestic_ea_infant,
					domestic_seat : Object.domestic_seat,
					domestic_airline : Object.airline
				},
						
				success : function(resp) {
					var array = resp.data;
					array.sort(customSort);
					
					$.each(array,function(idx,tmp){
						var charge =  parseInt(tmp.fare) + parseInt(tmp.airTax) + parseInt(tmp.fuelChg);
						html += "<tr>";
						html += "<td style='display:none;'>"+tmp.code+"</td>";
						html += "<td>"+tmp.carDesc+"</td>";
						html += "<td>"+tmp.depTime+"</td>";
						html += "<td>"+tmp.arrTime+"</td>";
						html += "<td>"+tmp.classDesc+"</td>";
						html += "<td>"+charge+"</td>";
						html += "<td>"+tmp.seat+"석</td>";
						html += "</tr>";
					});
					
					tBodyList2.html(html);
				},
						
				error : function(resp) {
					console.log(resp.status);
				}		
			});
	};

	
	function goResv(type){
		if(arrInfo.length==0){
			alert("도착지를 선택해주세요 ");	
			return;
		}
		
		if(deptInfo .length==0){
			alert("출발지를 선택해주세요 ");	
			return;
		}
		
		var Iairline_book_no = $("#Iairline_book_no"); 
		var Iairline_airline =$("#Iairline_airline");
		var Iairline_startdate =$("#Iairline_startdate");
		var Iairline_enddate =$("#Iairline_enddate");
		var Iairline_type =$("#Iairline_type");
		var Iairline_charge =$("#Iairline_charge");
		var Iairline_seat =$("#Iairline_seat");
		var Iairline_routetype =$("#Iairline_routetype");
		var Itype =$("#Itype");          	 
		
		Iairline_book_no.val(deptInfo[0]);
		Iairline_airline.val(deptInfo[1]);
		Iairline_startdate.val(deptInfo[2]);
		Iairline_enddate.val(deptInfo[3]);
		Iairline_type.val(deptInfo[4]);
		Iairline_charge.val(deptInfo[5]);
		Iairline_seat.val(deptInfo[6]);
		Iairline_routetype.val("dept");
		Itype.val(type);
		
		$("#submitForm").submit();
// 		$.ajax({
// 			url : "${pageContext.request.contextPath}/traffic/trafficReservation.do",
// 			method : 'post',
// 			dataType : "json",
// 			data : {
// 				airline_book_no 	: deptInfo[0],
// 				airline_airline 	: deptInfo[1],
// 				airline_startdate 	: deptInfo[2],
// 				airline_enddate 	: deptInfo[3],
// 				airline_type	    : deptInfo[4],
// 				airline_charge 		: deptInfo[5],
// 				airline_seat 		: deptInfo[6],
// 				airline_routetype   : "dept",
// 				type 				: type
// 			},
			
// 			success : function(resp) {
// 				console.log(resp);
// 			},
			
// 			error : function(resp) {
// 				console.log(resp.status);
// 			}
// 		});

		
// 		var html = "";
		
// 		$.ajax({
// 			url : "${pageContext.request.contextPath}/traffic/trafficReservation.do",
// 			method : 'post',
// 			dataType : "json",
// 			data : {
// 				airline_book_no 	: arrInfo[0],
// 				airline_airline 	: arrInfo[1],
// 				airline_startdate 	: arrInfo[2],
// 				airline_enddate 	: arrInfo[3],
// 				airline_type	    : arrInfo[4],
// 				airline_charge 		: arrInfo[5],
// 				airline_seat 		: arrInfo[6],
// 				airline_routetype   : "arr",
// 				type 				: type
// 			},
					
// 			success : function(resp) {
// 				console.log(resp);
// 			},
					
// 			error : function(resp) {
// 				console.log(resp.status);
// 			}		
// 		});
	}

</script>


<section class="s-content">
	<div class="row masonry-wrap">
	  
	  	<form id="submitForm" action="${pageContext.request.contextPath}/traffic/trafficReservation.do" method="post">
	  		<input id="Iairline_book_no"    type="hidden" value="" name="airline_book_no">           		 
	  		<input id="Iairline_airline"   type="hidden" value="" name="airline_airline">            		
	  		<input id="Iairline_startdate"  type="hidden" value="" name="airline_startdate">            		
	  		<input id="Iairline_enddate"    type="hidden" value="" name="airline_enddate">            		
	  		<input id="Iairline_type"      type="hidden" value="" name="airline_type">            		
	  		<input id="Iairline_charge"     type="hidden" value="" name="airline_charge">            		
	  		<input id="Iairline_seat"    type="hidden" value="" name="airline_seat">            		
	  		<input id="Iairline_routetype"  type="hidden" value="" name="airline_routetype">            		
	  		<input id="Itype"          	 type="hidden" value="" name="type">      
	  	</form>
	  
		<form id="searchForm" action="">
			<div style="height: 250px;" >
				<div style="margin :auto; width: 85%; height : 15%; background-color: #7787c7; border: 1px solid ; border-radius: 15px 15px 0px 0px;">
					<div style="color: white; float: right; margin-right: 15px;">
						왕복<input type="radio" name="way"/>
						편도<input type="radio" name="way"/>
					</div>
				</div>
				
				<div style="padding: 10px; margin : auto; width: 85%; height: 75%; background-color: #ffffff; border: 1px solid; border-radius: 0px 0px 15px 15px;">
					<div style="margin : auto; text-align:center; width: 90%;  display: inline-block;">
						
						<select class="airLineSelect" id="deptSelect" >
	
						</select> 
						
						<select class="airLineSelect" id="arriveSelect" >
	
						</select>
					</div>
					
					<div style="margin : auto; text-align:center; width: 90%;  display: inline-block;">
						<input type="date" id="startDate" value="${reservationInfo.startDate}" disabled="disabled">
						<input type="date" id="endDate" value="${reservationInfo.endDate}" disabled="disabled">
						<select class="personSelect"  id="adultSelect"></select>
						<select class="personSelect"  id="childrenSelect"></select>
						<select class="personSelect"  id="infantSelect"></select>
						<select class="personSelect"  id="seatSelect"></select>
					</div>
					
					<div style="margin : auto; text-align:center; width: 90%;  display: inline-block;">
						<img alt="" src="${cPath}/image/productReservation/KE.gif"><input type="checkbox" checked="checked" name= "airLineList" value="KE">대한항공
						<img alt="" src="${cPath}/image/productReservation/OZ.gif"><input type="checkbox"  name= "airLineList" value="OZ">아시아나
						<img alt="" src="${cPath}/image/productReservation/7C.gif"><input type="checkbox"  name= "airLineList" value="7C">제주항공
						<img alt="" src="${cPath}/image/productReservation/LJ.gif"><input type="checkbox"  name= "airLineList" value="LJ">진에어
						<img alt="" src="${cPath}/image/productReservation/TW.gif"><input type="checkbox"  name= "airLineList" value="TW">티웨이
						<img alt="" src="${cPath}/image/productReservation/ZE.gif"><input type="checkbox"  name= "airLineList" value="ZE">이스타항공
						<img alt="" src="${cPath}/image/productReservation/BX.gif"><input type="checkbox"  name= "airLineList" value="BX">에어부산
						<input type="submit" id="searchBtn" value="검색"  style="height: 45px; width: 20%;">
					</div>
					
				</div>
			</div>
		</form>
		
		
		

		<div style="display: inline-block; text-align: center; width: 100%; margin-left: 110px;">
			<div style="overflow:auto; padding: 10px; margin : auto; width: 43%; float:left; height: 75%; background-color: #ffffff; border: 1px solid; border-radius: 0px 0px 15px 15px;">
				
				<table class="type09" style="float: left; width: 100%; overflow: auto;">
					<tbody id="tBodyList">
						
					</tbody>
				</table>
			</div>
		
		
			<div style="overflow : auto; padding: 10px; margin : auto; width: 43%; float:left; height: 75%; background-color: #ffffff; border: 1px solid; border-radius: 0px 0px 15px 15px;">
				<table class="type09" style="float: left; width: 100%;" >
					
					<tbody id="tBodyList2">
							
					</tbody>
				</table>
				
			</div>
		</div>
		
		
		<div style="height: 250px; margin-top: 100px;" >
				<div style="color: white; margin :auto; width: 85%; height : 15%; background-color: #7787c7; border: 1px solid ; border-radius: 15px 15px 0px 0px;">
					선택한 항공 조회
				</div>
				
				<div style="padding: 10px; margin : auto; width: 85%; height: 75%; background-color: #ffffff; border: 1px solid; border-radius: 0px 0px 15px 15px;">
					<div>
						<table class="type09" style="float: left; width: 100%;">
							<thead>
								<tr>
									<th>여정</th>
									<th>항공편</th>
									<th>출발일시</th>
									<th>도착일시</th>
									<th>좌석구분</th>
									<th>기준요금(1인/편도)</th>
									<th>요청/잔여좌석</th>
								</tr>
							</thead>
						
							<tbody>
								<tr id="deptTr"></tr>
								<tr id="arrTr"></tr>
							</tbody>
						</table>
					</div>
				</div>
				
		</div>
		<div style="bottom: 0; float: right; margin-right: 150px;" >
				<button onclick="goResv(1);" >호텔 예약하러가기</button>
				<button onclick="goResv(2);" >바로 예약</button>
		</div>
	</div>
</section>
