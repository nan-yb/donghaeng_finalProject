package kr.or.ddit.common.board.eventboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//관리자가 이벤트를 등록하는 메서드

@Controller
public class EventBoardInsertController {
	@RequestMapping("/eventboard/eventboardInsert.do")
	public String getProcess() {
		return "common/board/eventboard/eventboardForm";
	}
}
