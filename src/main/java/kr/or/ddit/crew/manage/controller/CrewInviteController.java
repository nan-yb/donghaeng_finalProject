package kr.or.ddit.crew.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//크루 회원 초대
//크루장이나 크루원 소속원이 크루원이 아닌 다른 회원들에게 초대를 하는 프로그램

@Controller
public class CrewInviteController {
	@RequestMapping("/crew/crewInvite.do")
	public String getProcess(){
		return "";
	}
}
