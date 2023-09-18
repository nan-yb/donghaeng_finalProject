package kr.or.ddit.common.board.festivalboard.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.utils.HttpUtils;
import kr.or.ddit.vo.FestivalVO;


//지역축제 게시판 상세조회

@Controller
public class FestivalViewController {
	
	@Value("#{appInfo.festivalKey}")
	String key;
	
	@RequestMapping(value="festivalboard/festivalboardView.do", method = RequestMethod.GET)
	public String festivalView(@ModelAttribute(name="festivalVO") FestivalVO festivalVO, Model model){
		model.addAttribute("festivalVO", festivalVO);
		return "common/board/festivalboard/festivalboardView";
	}
	
	@ResponseBody
	@RequestMapping(value="festivalboard/festivalboardView.do",method = RequestMethod.POST, 
	produces = "application/json;charset=UTF-8")
	public String postProcess(
			@RequestParam(name="contentid") String contentId
			) throws IOException {
		String tempUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?"
				+ "serviceKey=" + key
				+ "&numOfRows=10"
				+ "&pageNo=1"
				+ "&MobileOS=ETC"
				+ "&MobileApp=AppTest"
				+ "&contentId="+contentId
				+ "&imageYN=Y"
				+ "&subImageYN=Y";
		return HttpUtils.getData(tempUrl);
	}
	
	@ResponseBody
	@RequestMapping(value="festivalboard/festivalboardView2.do",method = RequestMethod.POST, 
	produces = "application/json;charset=UTF-8")
	public String post2Process(
			@RequestParam(name="contentid") String contentId
			) throws IOException {
		String tempUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?"
				+ "serviceKey=" + key
				+ "&numOfRows=10"
				+ "&pageNo=1"
				+ "&MobileOS=ETC"
				+ "&MobileApp=AppTest"
				+ "&contentId="+contentId
				+ "&defaultYN=Y"
				+ "&firstImageYN=Y"
				+ "&areacodeYN=Y"
				+ "&catcodeYN=Y"
				+ "&addrinfoYN=Y"
				+ "&mapinfoYN=Y"
				+ "&overviewYN=Y";
		
		return HttpUtils.getData(tempUrl);
	}
	
}
