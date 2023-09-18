package kr.or.ddit.crew.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//크루원 추방
//크루장은 크루원 리스트에서 자신의 크루원을 강제 퇴출 시킬 수 있다.

@Controller
public class CrewMemberExileController {
	@RequestMapping("/crew/memberExile.do")
	public String getProcess(){
		return "";
	}
}
