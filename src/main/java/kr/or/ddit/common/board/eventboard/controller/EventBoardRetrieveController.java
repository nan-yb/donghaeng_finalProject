package kr.or.ddit.common.board.eventboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//모든회원이 이벤트 조회하는 메서드

@Controller
public class EventBoardRetrieveController {

	@RequestMapping("eventboard/eventboardRetrieve.do")
	public String getProcess(){
		return "common/board/eventboard/eventboardList";
	}
	
	@RequestMapping("eventboard/eventboardView.do")
	public String getProcess(String bo_no){
		return "common/board/eventboard/eventboardView";
	}
	
}
