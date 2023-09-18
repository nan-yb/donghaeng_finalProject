package kr.or.ddit.crew.volunteerlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//크루 신청 리스트 조회
//크루장이 자신의 크루의 신청 리스트를 조회

@Controller
public class CrewApplyController {
	@RequestMapping("/crew/ApplyList.do")
	public String getProcess(){
		return "";
	}
}
