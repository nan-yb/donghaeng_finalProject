package kr.or.ddit.member.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//내 글 목록 조회
//내가 작성한 글 조회를 위한 로직

@Controller
public class MyBoardListController {
	@RequestMapping("/member/mypage/myBoard.do")
	public String getProcess(){
		return "";
	}
}
