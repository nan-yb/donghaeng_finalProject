<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<link  rel="stylesheet"  href="${pageContext.request.contextPath}/css/weather.css" />

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>

<script type="text/javascript" src="<c:url value='/js/weather.js'/>"></script>


<script>
$.getContextPath = function(){
	return "${pageContext.request.contextPath}";
}
</script>

	
		
<section class="s-content">
	<div class="row masonry-wrap">
	

		<div style="height: 250px; margin-left: 50px;">
			<h1>우리 동네 정보</h1>
			<div style="float: left; width: 20%" id="myweatherDiv" >
			
			</div>
			<div style="float: left; width: 70%">
				<div>
					<button onclick="getTotalWeather()">날씨 정보</button>
					<button onclick="getAir()">미세먼지 정보</button>
					<button  id="clickForecastId" onclick="setForecast('11B10101')">중기 예보</button>
				</div>
				
				<div id="selectForecast" style="display: none;">
					<button class="clickForecast" onclick="setForecast('11B10101');">서울</button>
					<button class="clickForecast" onclick="setForecast('11B20201');">인천</button>
					<button class="clickForecast" onclick="setForecast('11H20201');">부산</button>
					<button class="clickForecast" onclick="setForecast('11H10701');">대구</button>
					<button class="clickForecast" onclick="setForecast('11F20501');">광주</button>
					<button class="clickForecast" onclick="setForecast('11F10201');">전북</button>
					<button class="clickForecast" onclick="setForecast('11F20405');">전남</button>
					<button class="clickForecast" onclick="setForecast('11C20401');">대전</button>
					<button class="clickForecast" onclick="setForecast('11C20301');">충남</button>
					<button class="clickForecast" onclick="setForecast('11C10301');">충북</button>
					<button class="clickForecast" onclick="setForecast('11D20501');">강원</button>
					<button class="clickForecast" onclick="setForecast('11G00201');">제주</button>
				</div>
			</div>
		</div>
	
		
		<div>
				<table>
					<thead  id="forecastTableHead" class="forecastTableHead">
					</thead>
					
					<tbody  id="forecastTableBody" class="forecastTableBody">
					</tbody>
				
				</table>
				
				<table>
					<thead  id="forecastTableHead1" class="forecastTableHead">
					
					</thead>
					
					<tbody  id="forecastTableBody1" class="forecastTableBody">
					
					</tbody>
				</table>
		</div>
	
	 <div class="jb-sidebar" >
	 	<div id="jbtitle">
	 		
	 	</div>
	 	
		<div id="weatherDiv">
					
		</div>
	</div>
	
	
	<div class="jb-content">
		<div id="leftContent"></div>
		<div id="chartContent"></div>
	</div>
	
	<table class = "table-bordered table-striped table-hover">
		<thead id="tHeadList">
			
		</thead>
		
		<tbody id="tBodyList">
			
		</tbody>
		
		<tfoot>
			
		</tfoot>
	</table>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cdd539404ca75df59eae1d4d6af80bc6&libraries=services"></script>
</div>
</section>
