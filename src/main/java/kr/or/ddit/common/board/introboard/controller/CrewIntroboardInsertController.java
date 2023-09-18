package kr.or.ddit.common.board.introboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//크루 소개 게시판 작성
//자신의 크루를 소개할 수 있는 게시판을 작성하는 프로그램입니다.

@Controller
public class CrewIntroboardInsertController {
	@RequestMapping("/crew/introboardInsert.do")
	public String getProcess(){
		return "/crew/introboard/crewIntroForm";
	}
}
