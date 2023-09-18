package kr.or.ddit.crew.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//크루 회원 조회
//크루장이 자신의 크루의 전체 리스트를 보기 위한 프로그램

@Controller
public class CrewMemberListController {
	@RequestMapping("/crew/crewMemberList.do")
	public String getProcess(){
		return "";
	}
}
