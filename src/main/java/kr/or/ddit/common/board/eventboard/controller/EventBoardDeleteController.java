package kr.or.ddit.common.board.eventboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//관리자가 이벤트를 삭제하는 메서드

@Controller
public class EventBoardDeleteController {
	@RequestMapping("/eventboard/eventboardDelete.do")
	public String getProcess(){
		return "";
	}
}
