package kr.or.ddit.common.board.festivalboard.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.utils.HttpUtils;

//지역축제 게시판 조회
//지역축제 게시글을 조회하기 위한 로직

@Controller
public class FestivalboardRetrieveController {
	
	@Value("#{appInfo.festivalKey}")
	String key;
	
	@RequestMapping(value="festival/festivalRetrieve.do", method=RequestMethod.GET)
	public String getProcess(){
		return "common/board/festivalboard/festivalboardList";
	}
	
	@ResponseBody
	@RequestMapping(value="festival/festivalRetrieve.do",method = RequestMethod.POST, 
	produces = "application/json;charset=UTF-8")
	public String postProcess(
			@RequestParam(name="areaCode")String areaCode,
			@RequestParam(name="pageNo", defaultValue="1") String pageNo
			) throws IOException {
		String tempUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "serviceKey="+key+""
				+ "&pageNo="+pageNo
				+ "&numOfRows=4"
				+ "&MobileApp=AppTest"
				+ "&MobileOS=ETC"
				+ "&arrange=D"
				+ "&contentTypeId=15"
				+ "&areaCode="+ areaCode
				+ "&listYN=Y";
		return HttpUtils.getData(tempUrl);
	}
	
	
}
