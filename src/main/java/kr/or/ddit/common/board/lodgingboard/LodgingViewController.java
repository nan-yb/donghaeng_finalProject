package kr.or.ddit.common.board.lodgingboard;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.utils.HttpUtils;

@Controller
public class LodgingViewController {
	@Value("#{appInfo.festivalKey}")
	String key;
	
	@RequestMapping(value="lodging/lodgingView.do", method = RequestMethod.GET)
	public String viewProcess(
			@RequestParam(name="contentid")String contentid,
			Model model
			){
		model.addAttribute("contentid", contentid);
		return "common/board/lodgingBoard/lodgingView";
	}
	
	@ResponseBody
	@RequestMapping(value="lodging/lodgingImg.do",method = RequestMethod.POST, 
	produces = "application/json;charset=UTF-8")
	public String postImgProcess(
			@RequestParam(name="contentid")String contentid
			) throws IOException {
		String tempUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?"
				+ "serviceKey="+key
				+ "&numOfRows=10"
				+ "&pageNo=1"
				+ "&MobileOS=ETC"
				+ "&MobileApp=AppTest"
				+ "&contentId="+contentid
				+ "&imageYN=Y"
				+ "&subImageYN=Y";
		return HttpUtils.getData(tempUrl);
	}
	
	@ResponseBody
	@RequestMapping(value="lodging/lodgingInfo.do",method = RequestMethod.POST, 
	produces = "application/json;charset=UTF-8")
	public String postInfoProcess(
			@RequestParam(name="contentid")String contentid
			) throws IOException {
		String tempUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?"
				+ "serviceKey="+key
				+ "&numOfRows=10"
				+ "&pageNo=1"
				+ "&MobileOS=ETC"
				+ "&MobileApp=AppTest"
				+ "&contentId="+contentid
				+ "&contentTypeId=32"
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
