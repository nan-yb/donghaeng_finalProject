package kr.or.ddit.common.board.rankboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//랭킹게시판 조회
//일정 개수 이상의 추천을 받은 게시글을 모아서 보여주기 위한 로직

@Controller
public class RankRetrieveController {
	@RequestMapping("/rankboard/rankRetrieve.do")
	public String getProcess(){
		return "";
	}
}
