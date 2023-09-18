package kr.or.ddit.common.board.lodgingboard;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.utils.HttpUtils;

@Controller
public class LodgingBoardRetrieveController {
	
	@Value("#{appInfo.festivalKey}")
	String key;
	
	@RequestMapping(value="lodging/lodgingRetrieve.do", method=RequestMethod.GET)
	public String getProcess(){
		return "common/board/lodgingBoard/lodgingList";
	}
	
	@ResponseBody
	@RequestMapping(value="lodging/areaCode.do",method = RequestMethod.POST, 
	produces = "application/json;charset=UTF-8")
	public String postProcess(
			@RequestParam(name="areaCode") String areaCode
			) throws IOException {
		String tempUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode"
				+ "?serviceKey="+key
				+ "&numOfRows=30"
				+ "&pageNo=1"
				+ "&MobileOS=ETC"
				+ "&MobileApp=AppTest"
				+ "&areaCode="+areaCode;
		return HttpUtils.getData(tempUrl);
	}
	
	@ResponseBody
	@RequestMapping(value="lodging/cityCode.do",method = RequestMethod.POST, 
	produces = "application/json;charset=UTF-8")
	public String postCityProcess(
			@RequestParam(name="cityCode") String cityCode,
			@RequestParam(name="areaCode") String areaCode,
			@RequestParam(name="pageNo", defaultValue="1") String pageNo
			) throws IOException {
		String tempUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchStay"
				+ "?serviceKey="+key
				+ "&numOfRows=4"
				+ "&pageNo="+pageNo
				+ "&MobileOS=ETC"
				+ "&MobileApp=AppTest"
				+ "&arrange=A"
				+ "&listYN=Y"
				+ "&areaCode="+areaCode
				+ "&sigunguCode="+cityCode;
		return HttpUtils.getData(tempUrl);
	}
	
	

}
