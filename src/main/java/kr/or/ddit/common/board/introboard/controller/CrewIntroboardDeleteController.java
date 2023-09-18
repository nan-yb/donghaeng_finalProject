package kr.or.ddit.common.board.introboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 크루 소개 게시판 삭제
// 자신의 크루를 소개할 수 있는 게시판을 삭제함

@Controller
public class CrewIntroboardDeleteController {
	@RequestMapping("/crew/introboardDelete.do")
	public String getProcess(){
		return "/crew/introboard/crewIntroList";
	}
}
