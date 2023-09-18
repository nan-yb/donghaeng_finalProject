<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>

<link rel="stylesheet" href="${cPath }/css/reservation/tabMenu.css">

<style>
	.selectAirLine{
	   width: 30%;
	   height: 20%;
	   display: inline;
	}

</style>
    
    
<script>
	
	$(function(){
		String.prototype.replaceAll = function(org, dest) {
		    return this.split(org).join(dest);
		}
		
		valid = true;
		startDate = $("#startDate");
		endDate = $("#endDate");
		
		startDate.flatpickr({
			minDate : new Date()
		});
		
		endDate.flatpickr({
			minDate : $("#startDate").val()
		});
		
		startDate1 = $("#startDate1");
		endDate1 = $("#endDate1");
		
		startDate1.flatpickr({
			minDate : new Date()
		});
		
		endDate1.flatpickr({
			minDate : new Date()
		});
	     
		var selectDept = $('[name="selectDept"]');
		var selectArr = $('[name="selectArr"]');
			
			
		deptSelect    = $("#selectDept");
		arriveSelect = $("#selectArr");
		
		
		airLineOptionGenerate();
		
		
		$('#deptSelect option[value="서울"]').attr('selected','selected');
		   
		deptSelect.trigger("change");   
		arriveSelect.trigger("change"); 
		
		
		$("#nextBtn").on('click',function(){
			var message = "";
			var sd = startDate.val().replaceAll("-", ""); 
			var ed = endDate.val().replaceAll("-", ""); 
			
			if(parseInt(sd) > parseInt(ed)){
				valid = false;
				message = "날짜 확인!";
			}
			
			
			var dept = $("select[name='selectDept']").val();
			var arr = $("select[name='selectArr']").val();
			var adult = $("#iDadult").val();
			var infant = $("#iDinfant").val();
			var children = $("#iDchildren").val();
			
			if(dept == arr || dept.length==0 || arr.length==0){
				message = "도시 선택 확인";
				valid = false;
			}
			
			var count = parseInt(adult) + parseInt(infant) + parseInt(children);
			
			if(count <= 0){
				message = "인원 확인";
				valid = false;
			}
			
			if(adult==0 && infant>0 || children>0){
				message = "성인 1명 이상 필수";
				valid = false;
			}
			
			if(valid==false){
				alert(message);
				event.preventDefault();
			}
			
		});
		
		$("#trainSubmit").on('click',function(){
			var message = "";
			var sd = startDate1.val().replaceAll("-", ""); 
			var ed = endDate1.val().replaceAll("-", ""); 
			
			if(parseInt(sd) > parseInt(ed)){
				valid = false;
				message = "날짜 확인!";
			}
			
			var deptSelect    = $("#selectDept1").val();
			var arriveSelect = $("#selectArr1").val();
			var adult = $("#iD2adult").val();
			var children = $("#iD2children").val();
			
			if(deptSelect == arriveSelect){
				message = "도시 선택 확인";
				valid = false;
			}
			
			var count = parseInt(adult) + parseInt(children);
			
			if(count <= 0){
				message = "인원 확인";
				valid = false;
			}
			
			if(adult==0 && children>0){
				message = "성인 1명 이상 필수";
				valid = false;
			}
			
			if(valid==false){
				alert(message);
				event.preventDefault();
			}
			
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
		
		$.each(airport,function(idx,temp){
			deptSelect.append($('<option>',{
				value : temp.airport,
				text : temp.name
			}));
		});
		
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
		
		
	}
	
</script>

<section class="s-content" >
   <div class="row masonry-wrap">
		<div class="container">
		    <ul class="tabs">
		        <li><a href="#tab1">비행기</a></li>
		        <li><a href="#tab2">기차</a></li>
		    </ul>
		    
		    <div class="tab_container">
		        <div id="tab1" class="tab_content">
		            <h2>비행기</h2>
		           	
					<div class="col-full s-content__main">
						<form action="" id="airLineForm" method="post">
							<table>
								<tr>
									<th>출발 날짜</th>
									<td><input type="text" id="startDate" name="startDate"></td>
								</tr>
								<tr>
									<th>도착 날짜</th>
									<td><input type="text" id="endDate" name="endDate"></td>
								</tr>
								
								<tr>
									<th>출발 도시</th>
									<td>
										<select name="selectDept" id="selectDept" class="selectAirLine">
										
										</select>
									</td>
								</tr>
								<tr>
									<th>도착 도시</th>
									<td>
										<select name="selectArr" id="selectArr" class="selectAirLine">
										
										</select>
									</td>
								</tr>
								
								<tr>
									<th>여행인원 선택</th>
									<td>
										성인&nbsp;&nbsp;:<input type="number" id="iDadult" name="adult" value="1" min="1">(최대 ?명)<br>
										어린이&nbsp;:<input type="number" id="iDchildren" name="children" value="0" min="0">(최대 ?명)<br>
										소아&nbsp;&nbsp;:<input type="number" id="iDinfant" name="infant" value="0" min="0">(최대 ?명)<br>
									</td>
								</tr>
								
									
							</table>
							<input type="hidden" name="traffic" value="airplain"><br>
							<input type="submit" value="다음" id="nextBtn">
						</form>
					</div>
		        </div>
		        
		        
		        <div id="tab2" class="tab_content">
		            <h2>기차</h2>
		           	
		           	<div class="col-full s-content__main">
						<form action="" id="trainForm" method="post">
							<table>
								<tr>
									<th>출발 날짜</th>
									<td><input type="text" id="startDate1" name="startDate"></td>
								</tr>
								<tr>
									<th>도착 날짜</th>
									<td><input type="text" id="endDate1" name="endDate"></td>
								</tr>
								
								<tr>
									<th>출발 도시</th>
									<td>
										<select name="selectDept" id="selectDept1">
											<OPTION value="">지역을 선택하세요</OPTION>
											<OPTION value="11">서울</OPTION>
											<OPTION value="12">세종</OPTION>
											<OPTION value="21">부산</OPTION>
											<OPTION value="22">대구</OPTION>
											<OPTION value="23">인천</OPTION>
											<OPTION value="24">광주</OPTION>
											<OPTION value="25">대전</OPTION>
											<OPTION value="26">울산</OPTION>
										</select>
									</td>
								</tr>
								<tr>
									<th>도착 도시</th>
									<td>
										<select name="selectArr" id="selectArr1">
											<OPTION value="">지역을 선택하세요</OPTION>
											<OPTION value="11">서울</OPTION>
											<OPTION value="12">세종</OPTION>
											<OPTION value="21">부산</OPTION>
											<OPTION value="22">대구</OPTION>
											<OPTION value="23">인천</OPTION>
											<OPTION value="24">광주</OPTION>
											<OPTION value="25">대전</OPTION>
											<OPTION value="26">울산</OPTION>
										</select>
									</td>
								</tr>
								
								<tr>
									<th>여행인원 선택</th>
									<td>
										성인&nbsp;&nbsp;:<input type="number" id="iD2adult" name="adult" value="1" min="1">(최대 ?명)<br>
										어린이&nbsp;:<input type="number" id="iD2children" name="children" value="0" min="0">(최대 ?명)<br>
									</td>
								</tr>
							</table>
							
							 <input type="hidden" name="traffic" value="train"><br>
							<input type="button" id="trainSubmit" value="다음" >
						</form>
					</div>
					
		        </div>
		    </div>
		</div>


<!-- 		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>  -->
		<script type="text/javascript">
			
			$(document).ready(function() {
			
				//Default Action
				$(".tab_content").hide(); //Hide all content
				$("ul.tabs li:first").addClass("active").show(); //Activate first tab
				$(".tab_content:first").show(); //Show first tab content
				
				//On Click Event
				$("ul.tabs li").click(function() {
					$("ul.tabs li").removeClass("active"); //Remove any "active" class
					$(this).addClass("active"); //Add "active" class to selected tab
					$(".tab_content").hide(); //Hide all tab content
					var activeTab = $(this).find("a").attr("href"); //Find the rel attribute value to identify the active tab + content
					$(activeTab).fadeIn(); //Fade in the active content
					return false;
				});
			
			});
		</script>
	</div>
</section>