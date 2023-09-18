/**
 * 
 */

	$(function(){
		contentList=[];
		chartContent = $("#chartContent");
		jbtitle = $("#jbtitle");
		tBodyList = $("#tBodyList");
		tHeadList = $("#tHeadList");
		contentTable = $("#contentTable");
		weatherDiv = $("#weatherDiv");
		myweatherDiv = $("#myweatherDiv");
		leftContent = $("#leftContent");
		getMyWeather();
		
		weatherList = 
			[
			 {name : "01d", 		value : "clearsky" ,  	weather  : "맑음"		},
			 {name : "02d", 		value : "fewclouds" , 	weather  : "약간 구름"	},
			 {name : "03d", 	value : "scatteredclouds"	,weather :	"흐림"	},
			 {name : "04d", 		value : "brokenCloud"	,weather :	"많이 흐림"	},
			 {name : "09d", 	value : "showerRain"		,weather :	"비"		},
			 {name : "10d", 	value : "showerRain"		,weather :	"비"		},
			 {name : "11d", 		value : "thunder"		,weather :	"천둥 번개"	},
			 {name : "13d", 	value : "snow"				,weather :	"눈"		},
			 {name : "50d", 	value : "mist"				,weather :	"안개"	},
			 {name : "01n", 		value : "clearsky"		,weather :	"맑음"	},
			 {name : "02n", 		value : "fewclouds"		,weather :	"약간 구름"	},
			 {name : "03n", 	value : "scatteredclouds"	,weather :	"흐림"	},
			 {name : "04n", 		value : "brokenCloud"	,weather :	"많이 흐림"	},
			 {name : "09n", 	value : "showerRain"		,weather :	"비"		},
			 {name : "10n", 	value : "showerRain"		,weather :	"비"		},
			 {name : "11n", 		value : "thunder"		,weather :	"천둥 번개"	},
			 {name : "13n", 	value : "snow"				,weather :	"눈"		},
			 {name : "50n", 	value : "mist"				,weather :	"안개"	}
			];
		
		
		weatherSidoList = 
			[
			"강원도",
			"경기도",
			"경상남도",
			"경상북도",
			"광주", 
			"대구", 
			"대전", 
			"부산", 
			"서울", 
			"공주", 
			"울산", 
			"인천", 
			"전남", 
			"전북", 
			"제주", 
			"충남", 
			"충북", 
			];
		
		$('#clickForecastId').click(function(){
			selectForecast.css('display' , 'inline')
		});
		
		
	});
		
	function getAir() {
		leftContent.html('<div id="map" style="width:500px; height :750px;"></div>');
		
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = { 
		      center: new daum.maps.LatLng(35.498692, 127.747406 ), // 지도의 중심좌표
		      level: 13 // 지도의 확대 레벨
		 };
		
		
		var map = new daum.maps.Map(mapContainer, mapOption);

		//지도 확대 X
		map.setZoomable(false); 
		
		markerSidoPositionArray = [
			new daum.maps.LatLng(37.56667,126.97806),	// 서울
			new daum.maps.LatLng(35.17944 ,129.07556),	// 부산
			new daum.maps.LatLng(36.35111, 127.38500),    // 대전
			new daum.maps.LatLng(33.50000, 126.51667),    // 제주
			new daum.maps.LatLng(36.81528, 127.11389),	// 충남
			new daum.maps.LatLng(36.787212, 127.604573),	// 충북
			new daum.maps.LatLng(35.87222 ,128.60250),	// 대구
			new daum.maps.LatLng(37.45639, 126.70528),	// 인천
			new daum.maps.LatLng(36.01944 ,129.34167),	//경북
			new daum.maps.LatLng(35.22917 ,128.67500),	//경남
			new daum.maps.LatLng(35.82500 ,127.15000),	// 전북
			new daum.maps.LatLng(35.15972 ,126.85306),	 // 전남
			new daum.maps.LatLng(37.722185, 128.852041)	// 강원
		];
		  
		imageSize = new daum.maps.Size(15, 10), // 마커이미지의 크기입니다
		imageOption = {offset: new daum.maps.Point(10, 15)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
		
		sidoList = 
			[
			 {name : "seoul", 		value : "서울"},
			 {name : "busan", 		value : "부산"},
			 {name : "daejeon", 	value : "대전"},
			 {name : "jeju", 		value : "제주"},
			 {name : "chungnam", 	value : "충남"},
			 {name : "chungbuk", 	value : "충북"},
			 {name : "daegu", 		value : "대구"},
			 {name : "incheon", 	value : "인천"},
			 {name : "gyeongbuk", 	value : "경북"},
			 {name : "gyeongnam", 	value : "경남"},
			 {name : "jeonbuk", 	value : "전북"},
			 {name : "jeonnam", 	value : "전남"},
			 {name : "gangwon", 	value : "강원"}
			];

		
		var imageSrc = "";
		$.ajax({
			url : $.getContextPath()+'/air/showAir.do',
			method: 'post',
			data : {
			},
			dataType : "json",
			success : function(resp){
				var list = resp.data1[0].response.body.items;
				list2 = resp.data2[0].respMonth.body.items;
				var tHeadHtml = "";
				var html = "";
				
				if(list){
					$.each(list,function(idx,si){
						tHeadHtml += "<tr><td>시간</td>"
						html += "<tr><td>"+si.dataTime+"</td>";
						$.each(sidoList, function(id,sido){
							var color = "";
						    
						    if( 0 < si[sido.name] && si[sido.name] <= 16){
						    	color = "b1";
						    }else if(16 < si[sido.name] && si[sido.name] <= 36){
						    	color = "g1";
						    }else if(36 < si[sido.name] && si[sido.name] <= 75){
						    	color = "y1";
						    }else{
						    	color = "r1";
						    }
						    imageSrc = $.getContextPath()+'/image/'+color+'.png'; // 마커이미지의 주소입니다  
							var content = 
							'<div class="customoverlay">' +
						    '  <a href="" target="_blank">' +
						    '    <span class="'+color+'">'+si[sido.name]+'</span>' +
						    '  </a>' +
						    '</div>';
						    contentList.push(content);
						    
						    
						    tHeadHtml += "<td>"+sido.value+"</td>"
						    html += "<td>"+si[sido.name]+"</td>";
						});
						html += "</tr>";
						tHeadHtml += "</tr>";
					});
				}
				
				//마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
				var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize, imageOption);
	
				marker = new daum.maps.Marker(null);
				marker.setMap(map);
				
				
				$.each(markerSidoPositionArray , function(idx, position){
					marker = new daum.maps.Marker({
						position: position,
						image: markerImage // 마커이미지 설정 
					});
					marker.setMap(map);
					
					
					var customOverlay = new daum.maps.CustomOverlay({
					    map: map,
					    position: position,
					    content: contentList[idx],
					    yAnchor: 1 
					});
				});
				
				tBodyList.html(html);
				tHeadList.html(tHeadHtml);
				
				
				chartSet(list2);
			},
			error : function(resp) {
				console.log(resp.status);
			}
		});
	}
	
function getTotalWeather(){
	leftContent.html('<div id="map" style="width:500px; height :750px;"></div>');
	
	jbtitle.html('<h1 style="margin-top: 1rem;">전국 날씨 정보</h1>');
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	      center: new daum.maps.LatLng(35.498692, 127.747406 ), // 지도의 중심좌표
	      level: 13 // 지도의 확대 레벨
	 };
	
	
	var map = new daum.maps.Map(mapContainer, mapOption);
	map.setZoomable(false); 


	
	var html = "";
	imageSizes = new daum.maps.Size(50, 50), // 마커이미지의 크기입니다
	imageOptions = {offset: new daum.maps.Point(10, 15)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
	
	
	$.ajax({
		url : $.getContextPath()+'/weather/totalWeather.do',
		method: 'get',
		dataType : "json",
		success : function(resp){
			var weather = "";
			var tHeadHtml = "<tr><td>시간</td>";
			var tBodyhtml = "<tr><td></td>";
			
			$.each(resp, function(idx, tmp){
				tHeadHtml += "<td>"+weatherSidoList[idx]+"</td>";
				tBodyhtml += "<td>"+tmp.main.temp+"℃</td>";
				
				html +='<div class="node1">';
					html +='<div class="weatherContainer">';
						html +="<div class='weaLeft'>";
						$.each(weatherList, function(idx, icon){
							if(tmp.weather[0].icon == icon.name  ){
								imageSrcs = $.getContextPath()+'/image/weather/'+icon.value+'.png'; // 마커이미지의 주소입니다  
								
								html +="<img alt='' src='"+$.getContextPath()+"/image/weather/"+icon.value+".png' align='middle'>";
								weather = icon.weather;
							}
						});
						html +="</div>";
						
						html +="<div class='weaRigjt'>";
							html +='<div class="weaContent" align="right"><strong>'+tmp.main.temp+' ℃</strong></div>';
							html +='<div class="weaContent" align="right"><strong>'+weather+'</strong></div>';
							html +='<div class="weaContent" align="right"><strong>'+weatherSidoList[idx]+'</strong></div>';
						html +="</div>";
						
						html +="<div class='weaFooter'>";
						html +='<div class="weaContent2">';
						html +='<img alt="" src="'+$.getContextPath()+'/image/weather/wind.png" align="middle">';
						html +=''+tmp.wind.speed+'m/s';
						html +='</div>';
						html +='<div class="weaContent2">';
						html +='	<img alt="" src="'+$.getContextPath()+'/image/weather/humidity.png" align="middle">';
						html +=''+tmp.main.humidity+'%';
						html +='</div>';
						html +='<div class="weaContent2">';
						html +='<img alt="" src="'+$.getContextPath()+'/image/weather/cloud.png" align="middle">';
						html +=''+tmp.clouds.all+'%';
						html +='</div>';
						html +="</div>"
					
					html +="</div>";
				html +='</div>';
				var markerImages = new daum.maps.MarkerImage(imageSrcs, imageSizes, imageOptions);
				
				var positions = new daum.maps.LatLng(tmp.coord.lat, tmp.coord.lon);
				
				
				markers = new daum.maps.Marker({
					position: positions,
					image: markerImages // 마커이미지 설정 
				});
				
				markers.setMap(map); 
				
				var customOverlay = new daum.maps.CustomOverlay({
				    map: map,
				    position: positions,
				    yAnchor: 1 
				});
				
			});
          	
			tHeadHtml += "</tr>";
			tBodyhtml += "</tr>";
			
			
			weatherDiv.html(html);
			tBodyList.html(tBodyhtml);
			tHeadList.html(tHeadHtml);
			
			
		},
		error : function(resp){
			
		}
	});
}
	
function getMyWeather(){
	$.ajax({
		url : $.getContextPath()+'/weather/showWeather.do',
		method: 'post',
		data : {
		},
		dataType : "json",
		success : function(resp){
			var html = "";
			html +='<div class="node1">';
			html +='<div class="weatherContainer">';
				html +="<div class='weaLeft'>";
				$.each(weatherList, function(idx, icon){
					if(resp.weather[0].icon == icon.name  ){
						imageSrcs = $.getContextPath()+'/image/weather/'+icon.value+'.png'; // 마커이미지의 주소입니다  
						
						html +="<img alt='' src='"+$.getContextPath()+"/image/weather/"+icon.value+".png' align='middle'>";
						weather = icon.weather;
					}
				});
				html +="</div>";
				
				html +="<div class='weaRigjt'>";
					html +='<div class="weaContent" align="right"><strong>'+resp.main.temp+' ℃</strong></div>';
					html +='<div class="weaContent" align="right"><strong>'+weather+'</strong></div>';
					html +='<div class="weaContent" align="right"><strong>'+resp.name+'</strong></div>';
				html +="</div>";
				
				html +="<div class='weaFooter'>";
				html +='<div class="weaContent2">';
				html +='<img alt="" src="'+$.getContextPath()+'/image/weather/wind.png" align="middle">';
				html +=''+resp.wind.speed+'m/s';
				html +='</div>';
				html +='<div class="weaContent2">';
				html +='	<img alt="" src="'+$.getContextPath()+'/image/weather/humidity.png" align="middle">';
				html +=''+resp.main.humidity+'%';
				html +='</div>';
				html +='<div class="weaContent2">';
				html +='<img alt="" src="'+$.getContextPath()+'/image/weather/cloud.png" align="middle">';
				html +=''+resp.clouds.all+'%';
				html +='</div>';
				html +="</div>"
					html +="</div>";
				html +='</div>';
				myweatherDiv.html(html);
		},
		error : function(resp){
			
		}
	});
 }
	
function custom_sort(a, b) {
	   return new Date(a.dataTime).getTime() - new Date(b.dataTime).getTime();
}

	
function chartSet(list2){

	chartContent.html('<canvas id="myChart" width="400" height="200"></canvas>');
	
	var labels = [];
	var datasets = [];	
	var color = ["#dd7268","#ffaf54","#f9ec5e","#54db4a","#47d8c0",
	             "#6A92E4" , "#d466e8" ,"#f948b2","#dd7268","#ffaf54",
	             "#f9ec5e","#54db4a","#47d8c0",];	
	
	var array= list2.item;

	array.sort(custom_sort);
	         	
	
	for (i = 0; i < list2.item.length; i++) { 
		labels.push(list2.item[i].dataTime);
	}
	
	$.each(sidoList, function(id,sido){
		
		
		var datas = {
			label : sido.value,
			fill  : false,
			backgroundColor : color[id],
			borderColor: color[id]
		}
		data = [];
		
		for (i = 0; i < list2.item.length; i++) {
			data.push(list2.item[i][sido.name]);
		}
		datas.data = data;
		
		datasets.push(datas);
	});
	
	
	var ctx = document.getElementById('myChart').getContext('2d');
	var chart = new Chart(ctx, {
	    // The type of chart we want to create
	    type: 'line',

	    // The data for our dataset
	    data: {
			labels: labels,
			datasets: datasets
	    },

	    // Configuration options go here
	    options: {}
	});
}




function setForecast(code){
	selectForecast = $("#selectForecast");
	
	
	$('.clickForecast').click(function(){
		$('.clickForecast').css('background-color' , '#f2f2f2');
	    $(this).css('background-color' , 'red');
	});
	
	var weatherForeCasthtml = "";
	
	tBodyList.html("");
	tHeadList.html("");
	contentTable.html("");
	weatherDiv.html("");
	leftContent.html("");
	chartContent.html("");
	
	forecastTableHead = $("#forecastTableHead");
	forecastTableBody = $("#forecastTableBody");
	forecastTableHead1 = $("#forecastTableHead1");
	forecastTableBody1 = $("#forecastTableBody1");
	
	var codeArray = 
				[{code :"wf3Am",value : "3일 후 오전" },
	            {code : "wf3Pm",value : "3일 후 오전" },
	            {code : "wf4Am",value : "4일 후 오전" },
	            {code : "wf4Pm",value : "4일 후 오후" },
	            {code : "wf5Am",value : "5일 후 오전" },
	            {code : "wf5Pm",value : "5일 후 오후" },
	            {code : "wf6Am",value : "6일 후 오전" },
	            {code : "wf6Pm",value : "6일 후 오후" },
	            {code : "wf7Am",value : "7일 후 오전" },
	            {code : "wf7Pm",value : "7일 후 오후" },
	            {code : "wf8",  value : "8일 후" },
	            {code : "wf9",  value : "9일 후" },
	            {code : "wf10", value : "10일 후" }]
	
	var codeArray1 = 
	    [
	            {code : "taMin3", value : "3일 후 최저 온도" },
	            {code : "taMax3", value : "3일 후 최고 온도" },
				{code :"taMin4",  value : "4일 후 최저 온도 " },
	            {code : "taMax4", value : "4일 후 최고 온도" },
				{code : "taMin5", value : "5일 후 최저 온도" },
	            {code : "taMax5", value : "5일 후 최고 온도" },
	            {code : "taMin6", value : "6일 후 최저 온도" },
	            {code : "taMax6", value : "6일 후 최고 온도" },
	            {code : "taMin7", value : "7일 후 최저 온도" },
	            {code : "taMax7", value : "7일 후 최고 온도" },
	            {code : "taMin8", value : "8일 후 최저 온도" },   
	            {code : "taMax8", value : "8일 후 최고 온도" },   
	            {code : "taMin9", value : "9일 후 최저 온도" },  
	            {code : "taMax9", value : "9일 후 최고 온도" },
	            {code : "taMin10", value :"10일 후 최저 온도"},
	            {code : "taMax10", value :"10일 후 최고 온도"}
	  ];
	            
	  $.ajax({           
		url : $.getContextPath()+'/weather/forecastWeather.do',
		method: 'get',  
		data : {
			code : code
		},
		dataType : "json",
		success : function(resp){
			var list0 = resp[0].response.body.items;
			var list1 = resp[1].response.body.items;
			
			var list0head = "";
			var list1head = "";
			
			var list0body = "";
			var list1body = "";
			
			$.each(codeArray, function(idx,tmp){
				
				list0head += "<th>"+tmp.value+"</th>";
				
			});
			
			$.each(codeArray1, function(idx,tmp){
			    list1head += "<th>"+tmp.value+"</th>";
			});
			
			$.each(list0, function(idx,tmp){
				$.each(codeArray1, function(idx,temp){
					list1body += "<td>"+tmp[temp.code]+"</td>";
				});
			});
			
			$.each(list1, function(idx,tmp){
				$.each(codeArray, function(idx,temp){
					list0body += "<td>"+tmp[temp.code]+"</td>";
				});
			});
			
			
			forecastTableHead.html(list0head);
			forecastTableBody.html(list0body);
			forecastTableHead1.html(list1head);
			forecastTableBody1.html(list1body);
		},
		error : function(resp){
			alert('실패');
		}
	});
}