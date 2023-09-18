package kr.or.ddit.common.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.product.service.IFreeProductService;
import kr.or.ddit.utils.HttpUtils;
import kr.or.ddit.vo.TestHotelVO;

@Controller
public class FreeProductRetrieveController {
	
	@Inject
	IFreeProductService service;
	
	@Value("#{appInfo.festivalKey}")
	String key;
	String globalAreaCode;
	String globalPersonnel;
	String globalMem_id;
	
	@RequestMapping("/common/product/freeProduct.do")
	public String goFreeProductFormProcess(){
		return "common/product/freeProductList";
	}
	
	@RequestMapping( value="/common/product/freeProduct.do", method = RequestMethod.POST)
	public String goFreeProductListProcess(
		Model model,
		String startDate,
		String endDate,
		String selectDept,
		String selectArr,
		String adult,
		String children,
		String infant,
		String traffic
	){
		
		Map<String, String> reservationInfo = new HashMap<>();
		
		reservationInfo.put("startDate",startDate);
		reservationInfo.put("endDate",endDate);
		reservationInfo.put("selectDept",selectDept);
		reservationInfo.put("selectArr",selectArr);
		reservationInfo.put("adult",adult);
		reservationInfo.put("infant",infant);
		reservationInfo.put("children",children);
		
		String view = "";
		
		if(traffic.equals("train")){
			view = "common/product/freeProductTrain";
		}else{
			view = "common/product/freeProductAirplain";
		}
		model.addAttribute("reservationInfo", reservationInfo);
		
		return view;
	}
	
	@RequestMapping("/common/package/selectHotel.do")
	public String selectHotelProcess(
			@RequestParam(name="selectLocal", required=true)String areaCode,
			@RequestParam(name="personnel", required=true)String personnel
			) throws IOException{
		
		globalAreaCode = areaCode;
		globalPersonnel = personnel;				
		return "common/product/selectHotel";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/common/package/selectBookList.do",method = RequestMethod.POST, 
	produces = "application/json;charset=UTF-8")
	public List<TestHotelVO> postSelectBookProcess(
			) throws IOException {		
		
		List<TestHotelVO> bookList = service.selectBook();		
		return bookList;
	}
	
	@ResponseBody
	@RequestMapping(value="/common/package/getSigugun.do",method = RequestMethod.POST, 
	produces = "application/json;charset=UTF-8")
	public String postProcess(
			) throws IOException {
		String tempUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode"
				+ "?serviceKey="+key
				+ "&numOfRows=30"
				+ "&pageNo=1"
				+ "&MobileOS=ETC"
				+ "&MobileApp=AppTest"
				+ "&areaCode="+globalAreaCode;
		
		return HttpUtils.getData(tempUrl);
	}
	
	@ResponseBody
	@RequestMapping(value="/common/package/getHotelList.do",method = RequestMethod.POST, 
	produces = "application/json;charset=UTF-8")
	public String postCityProcess(
			@RequestParam(name="cityCode") String cityCode,
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
				+ "&areaCode="+globalAreaCode
				+ "&sigunguCode="+cityCode;
		
		return HttpUtils.getData(tempUrl);
	}
	
	@ResponseBody
	@RequestMapping(value="/common/package/getHotelInfo.do",method = RequestMethod.POST, 
	produces = "application/json;charset=UTF-8")
	public String getInfoProcess(
			@RequestParam(name="contentid") String contentid,
			Model model
			) throws IOException {
		String tempUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?"
				+ "serviceKey="+key
				+ "&numOfRows=10"
				+ "&pageNo=1"
				+ "&MobileOS=ETC"
				+ "&MobileApp=AppTest"
				+ "&contentId="+contentid
				+ "&contentTypeId=32";
		return HttpUtils.getData(tempUrl);
	}
	
	@RequestMapping("/common/package/CheckfreeProductForm.do")
	public String goCheckFreeProductFormProcess(
			@RequestParam(name="contentid") String contentid,
			@RequestParam(name="hotelName") String hotelName,
			Model model
	){
		int roomCnt = 0;
		int people = Integer.parseInt(globalPersonnel);
		if(people <= 4){
			roomCnt = 1;
		}else if(people <= 8){
			roomCnt = 2;
		}else if(people <= 12){
			roomCnt = 3;
		}else if(people <= 16){
			roomCnt = 4;
		}else{
			roomCnt = 5;			
		}
		
		model.addAttribute("mem_id", "3");
		model.addAttribute("hotelCode", contentid);
		model.addAttribute("hotelName", hotelName);
		model.addAttribute("globalPersonnel", globalPersonnel);
		model.addAttribute("bookRoom", roomCnt);
		
		return "common/product/checkFreeProductForm";
	}
	
	
}
