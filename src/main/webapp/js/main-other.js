/**
 * 
 */


$(function(){

	var currentTime = Date.now()
	var GMT = -(new Date()).getTimezoneOffset()/60;
	var totalSeconds = Math.floor(currentTime/1000);
	seconds = ('0' + totalSeconds % 60).slice(-2);
	var totalMinutes = Math.floor(totalSeconds/60);
	minutes = ('0' + totalMinutes % 60).slice(-2);
	var totalHours = Math.floor(totalMinutes/60);
	hours = ('0' + (totalHours+GMT) % 24).slice(-2);
	var timeDisplay = hours + ":" + minutes + ":" + seconds;
	
	if(!WebSocket){
		alert("웹 소켓 지원 안되는 브라우저");
		return;
	}
	
	var url = "ws://"+location.host;
	if(location.port) url += ":"+location.port;
	url += $.getContextPath() + "/chatting";
	var ws ;
	$("#enterBtn").on("click", function(){
		var nickName = $.getName();
		if(!nickName) nickName="익명";
		url += "/"+ encodeURIComponent(nickName);
		
		ws = new WebSocket(url);
		ws.onopen=function(event){ console.log(event); }
		ws.onclose=function(event){ console.log(event); }
		ws.onmessage=function(event){
			try{
				var chattingMessage = JSON.parse(event.data);
				var name = chattingMessage.sender.name;
				var message = chattingMessage.message;
				
				var html ="";
				html += "<li style='list-style:none;' class='left clearfix'>                                                                                                                ";
		        html += "    <span class='chat-img pull-left'>                                                                                                     ";   
		        html += "        <img src='http://placehold.it/50/55C1E7/fff' alt='User Avatar' class='img-circle' />                                              ";   
		        html += "    </span>                                                                                                                               ";   
		        html += "       <div class='chat-body clearfix'>                                                                                                   ";   
		        html += "                <small class='pull-right text-muted'>                                                                                     ";   
		        html += "                    <i class='fa fa-clock-o fa-fw'></i>"+timeDisplay+"                                                                     ";   
		        html += "                </small>                                                                                                               ";   
		        html += "                <strong style='padding-left:5px; font-weight: bold;' class='primary-font'>"+name+"</strong>                                                                        ";   
		        html +="<div class='from-them'>";
		        html += "            <p>                                                                                                                           ";   
		        html += "               "+message+"";   
		        html += "            </p>                                                                                                                          ";   
		        html +=" </div>";
		        html += "        </div>                                                                                                                            ";
		        html += "   </li>    ";
		        
				$("#messageArea").append(
						html
				);
				
			}catch(e){
				console.log(event.data);
				$("#messageArea").append(
					$("<div />").text(event.data)
				);		
			}
		}
	});

	$("#sendBtn").on("click", function(){
		var msg = $('#message').val();
		ws.send(msg);
		$("#message").val("");
	});
	
	$("#message").on("keydown", function(event){
		if(event.keyCode!=13) return true;
		var msg = $(this).val();
		ws.send(msg);
		$(this).val("");
	});
	
	$("#outBtn").on("click", function(){
		ws.close();
	});
		    
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
	
	
	myweatherDiv = $("#myweatherInfoDiv");
	getMyWeather();
})

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