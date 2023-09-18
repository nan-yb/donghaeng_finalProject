package kr.or.ddit.crew.volunteerlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//크루 신청 리스트 승인/미승인
//크루장이 자신의 크루의 신청 리스트에서 승인/미승인 할 수 있다.

@Controller
public class CrewApplyUpdateController {
	@RequestMapping("/crew/applyUpdate.do")
	public String getProcess(){
		return "";
	}
}
