package kr.or.ddit.cor.travlePlace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TravlePlaceUpdateController {
	// 여행사가 자신의 여행지 수정하는 메서드
	
	@RequestMapping("/travlePlace/travlePlaceUpdate.do")
	public String getProcess(){
		return "common/board/tipboard/tipboardList";
	}
}
