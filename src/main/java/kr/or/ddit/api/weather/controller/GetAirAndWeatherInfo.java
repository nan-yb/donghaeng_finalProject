package kr.or.ddit.api.weather.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.utils.HttpUtils;


@Controller
public class GetAirAndWeatherInfo {

	final String API_KEY = "RiRhW5gyS8VRPtTKn0oYUJwUF3JFl3rkxKBDj1XiVMzmthbnyxqLKvdedcAhXYFPtT61VQxtmRkyGcmDdaEJIw%3D%3D";

	@RequestMapping(value = "air/showAir.do", method = RequestMethod.GET)
	public String doAirGet() throws IOException {
		return "weather/airList";
	}

	@ResponseBody
	@RequestMapping(value = "air/showAir.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String doPost() throws IOException {
		String tempUrl = "http://openapi.airkorea.or.kr/openapi/"
				+ "services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst" + "?itemCode=PM10" + "&dataGubun=HOUR" // 일평균 // ,
				+ "&searchCondition=MONTH&pageNo=1&numOfRows=1" + "&ServiceKey=" + API_KEY + "";
		
		String tempUrl2 = "http://openapi.airkorea.or.kr/openapi/services/rest/"
				+ "ArpltnInforInqireSvc/getCtprvnMesureLIst?"
				+ "serviceKey=RiRhW5gyS8VRPtTKn0oYUJwUF3JFl3rkxKBDj1XiVMzmthbnyxqLKvdedcAhXYFPtT61VQxtmRkyGcmDdaEJIw%3D%3D&"
				+ "numOfRows=20&pageNo=1&itemCode=PM10"
				+ "&dataGubun=DAILY&searchCondition=MONTH";
		
		
		String data1 = HttpUtils.getData(tempUrl);
		String data2 = HttpUtils.getData(tempUrl2).replace("response", "respMonth");

		String jsonData = "{\"data1\" : ["+data1+"],"+"\"data2\" : ["+data2+"]}"; 
		return jsonData;
	}

	@RequestMapping(value = "weather/showWeather.do", method = RequestMethod.GET)
	public String doWeatherGet() throws IOException {
		return "weather/weatherList";
	}

	@ResponseBody
	@RequestMapping(value = "weather/totalWeather.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getTotalLocation() throws IOException {
		String tempUrl = "http://www.weather.go.kr/process/main-dfs-dong-json.jsp?type=WIDE&wideCode=&cityCode=";
		
		
		JSONArray jsonArray = new JSONArray(HttpUtils.jsonGetData(tempUrl,null));
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		for(int i = 0; i < jsonArray.length(); i++){
			JSONObject obj = (JSONObject)jsonArray.get(i);
			String lon = (String) obj.get("lon");
			String lat = (String) obj.get("lat");
			
			String url = "https://api.openweathermap.org/data/2.5/"
					+ "weather?"
					+ "lat=" + lat
					+ "&lon=" + lon
					+ "&appid=fbedaa69bce997e934bfccccc40e5baa&units=metric&lang=ko";
			
			if(i!=0){
				sb.append(",");
			}
			sb.append(HttpUtils.jsonPostData(url , null));
		}
		sb.append("]");
		
		
		return sb.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "weather/showWeather.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String getOneCityWeather() throws IOException {
		String key = "fbedaa69bce997e934bfccccc40e5baa";
		String json = getLocale();
		JSONObject obj = new JSONObject(json);
		String city = (String) obj.get("city");
		if(city.equals("") || city == null){
			city = "daejeon";
		}
//		https://api.openweathermap.org/data/2.5/weather?lat= &lon = &appid=
		String tempUrl = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&lang=en&appid="+key+"&units=metric";
		String jsonData =  HttpUtils.jsonGetData(tempUrl, null);
		return jsonData;
	}
	
	@ResponseBody
	@RequestMapping(value = "weather/forecastWeather.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getForecastWeather(String code) throws IOException {
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());

		
		SimpleDateFormat test = new SimpleDateFormat("yyyyMMdd");
		
		String date =  test.format(cal.getTime());
		Map<String, String> map = new HashMap<String, String>();
		map.put("11B10101","11B00000");
		map.put("11B20201","11B00000");
		map.put("11H20201","11H20000");
		map.put("11H10701","11H10000");
		map.put("11F20501","11F20000");
		map.put("11F10201","11F10000");
		map.put("11F20405","11F20000");
		map.put("11C20401","11C20000");
		map.put("11C20301","11C20000");
		map.put("11C10301","11C10000");
		map.put("11D20501","11D10000");
		map.put("11G00201","11G00000");
		map.put("11H20101","11H20000");
		map.put("11H10501","11H20000");
		
		//중기 기상
		String tempUrl = "http://newsky2.kma.go.kr/service/"
				+ "MiddleFrcstInfoService/"
				+ "getMiddleTemperature?"
				+ "ServiceKey="+API_KEY+"&"
				+ "regId="+code+"&"
				+ "tmFc="+date+"0600&"
				+ "numOfRows=1&pageNo=1";
		
		//중기 육상
		String tempUrl2 = "http://newsky2.kma.go.kr/service/MiddleFrcstInfoService/getMiddleLandWeather?ServiceKey="+API_KEY+
				"&regId="+map.get(code)
				+"&tmFc="+date+"0600"
				+"&numOfRows=1&pageNo=1";
		
		String json = HttpUtils.getData(tempUrl);
		String json2 = HttpUtils.getData(tempUrl2);
		
		String jsonData = "[" + json + "," + json2 + "]";
		
		
		return jsonData;
	}
	
	@ResponseBody
	@RequestMapping(value = "weather/MyWeather.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String getMyWeather() throws IOException {
		
		String tempUrl = "http://www.airkorea.or.kr/web/mMyTownAirInfoAjax";
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
//		params.add("tm_x", value);
		
		
		
		return HttpUtils.jsonPostData(tempUrl, null);
	}
	
	
	public String getLocale() throws IOException {
		String tempUrl = "https://ipinfo.io/";
		return HttpUtils.jsonPostData(tempUrl,null);
	}
	
	public String getAddress() throws IOException{
		String json = getLocale();
		
		JSONObject obj = new JSONObject(json);
		String loc = (String) obj.get("loc");
		
		String[] locArray = loc.split(",");
		String y = locArray[0];
		String x = locArray[1];
		
		String url = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?x="+x+"&y="+y+"";
		HttpHeaders headers = new HttpHeaders(); 
		headers.setContentType(MediaType.APPLICATION_JSON);//JSON 변환 
		headers.set("Authorization", "KakaoAK 9e21c6a1272312a98709d09c7524e7bb"); //appKey 설정 ,KakaoAK kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk 이 형식 준수
		return  HttpUtils.jsonGetData(url , headers);
	}
	
	
}
