package kr.or.ddit.common.board.eventboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//관리자가 이벤트를 업데이트하는 메서드

@Controller
public class EventBoardUpdateController {
	@RequestMapping("/eventboard/eventboardUpdate.do")
	public String getProcess(){
		return "";
	}
}
