package kr.or.ddit.cor.travlePlace.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TravlePlaceRetrieveController {
	// 여행사가 자신의 여행지 조회하는 메서드
	
	@Inject
	
	@ResponseBody
	@RequestMapping(value="travlePlace/travlePlaceRetrieve.do", produces="application/json;charset=UTF-8")
	public String getProcess(){
		return "corporation/travlePlace/travlePlaceList";
	}
	
}
