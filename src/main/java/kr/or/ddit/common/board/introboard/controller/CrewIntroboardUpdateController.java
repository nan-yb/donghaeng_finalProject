package kr.or.ddit.common.board.introboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//크루 소개 게시판 수정
//자신의 크루를 소개할 수 있는 게시판을 수정하는 프로그램입니다.

@Controller
public class CrewIntroboardUpdateController {
	@RequestMapping("/crew/introboardUpdate.do")
	public String getProcess(){
		return "";
	}
}
