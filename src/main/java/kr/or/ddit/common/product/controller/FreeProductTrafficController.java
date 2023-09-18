package kr.or.ddit.common.product.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.common.product.service.ITrafficService;
import kr.or.ddit.vo.AirPlainVO;
import kr.or.ddit.vo.FreeProductVO;
import kr.or.ddit.vo.FreeTrafficVO;
import kr.or.ddit.vo.TrainVO;

@Controller
public class FreeProductTrafficController {

	@Inject
	ITrafficService service;
	
	@RequestMapping(value="/traffic/trafficReservation.do", method=RequestMethod.POST)
	public String postProcess(AirPlainVO airVO,
							  String type,			// 바로 예약 2 , 호텔 예약 1 
							  TrainVO trainVO,
							  FreeTrafficVO resvVO
    ){
		String view = null;
		
		System.out.println(type);
		
		if(type.equals("1")){
			String selectLocal = "4";
			String personnel = "4";
			return "redirect:/common/package/selectHotel.do"+
					"?selectLocal="+selectLocal+
					"&personnel="+personnel;
		}else{
			
		}
		
		return "";
	}
}
