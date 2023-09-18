package kr.or.ddit.common.board.introboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//크루 소개 게시판 조회
//자신의 크루를 소개할 수 있는 게시판이며 게시판의 모든 리스트를 출력한다.

@Controller
public class CrewIntroboardListController {
	@RequestMapping("/crew/introboardList.do")
	
	public String getProcess(){
		return "common/board/introboard/crewIntroList";
	}
}
