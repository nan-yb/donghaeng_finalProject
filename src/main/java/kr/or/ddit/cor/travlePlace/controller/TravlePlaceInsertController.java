package kr.or.ddit.cor.travlePlace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TravlePlaceInsertController {
	// 여행사가 자신의 여행지 등록하는 메서드
	@RequestMapping("/travlePlace/travlePlaceInsert.do")
	public String getProcess(){
		return "corporation/travlePlace/travlePlaceForm";
	}
}
