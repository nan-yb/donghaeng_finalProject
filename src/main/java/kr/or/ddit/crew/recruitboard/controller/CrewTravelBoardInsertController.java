package kr.or.ddit.crew.recruitboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//크루 여행 모집글 작성
//크루원은 자신의 크루의 여행 모집글을 작성 할 수 있다.

@Controller
public class CrewTravelBoardInsertController {
	@RequestMapping("/crew/travelInsert.do")
	public String getProcess(){
		return "";
	}
}
