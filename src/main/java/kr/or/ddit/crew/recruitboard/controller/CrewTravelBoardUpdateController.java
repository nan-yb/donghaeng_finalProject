package kr.or.ddit.crew.recruitboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//크루 여행 모집글 수정
//크루원은 자기가 쓴 크루의 여행 모집글을 수정할 수 있다.

@Controller
public class CrewTravelBoardUpdateController {
	@RequestMapping("/crew/travelUpdate.do")
	public String getProcess(){
		return "";
	}
}
