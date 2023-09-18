package kr.or.ddit.cor.travlePlace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TravlePlaceDeleteController {
	// 여행사가 자신의여행지 삭제하는 메서드
	
	@RequestMapping("/travlePlace/travlePlaceDelete.do")
	public String getProcess(){
		return "common/board/tipboard/tipboardList";
	}
	
}
