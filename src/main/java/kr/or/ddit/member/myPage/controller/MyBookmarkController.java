package kr.or.ddit.member.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//즐겨찾기 목록 조회
//회원이 즐겨찾기한 글목록을 보여주기 위한 로직

@Controller
public class MyBookmarkController {
	@RequestMapping("/member/mypage/myBookmark.do")
	public String getProcess(){
		return "";
	}
}
