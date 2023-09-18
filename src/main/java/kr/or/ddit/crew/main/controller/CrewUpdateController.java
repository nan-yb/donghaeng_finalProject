package kr.or.ddit.crew.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//크루 수정
//크루장이 자신의 크루를 수정 하기 위한 로직

@Controller
public class CrewUpdateController {
	
	@RequestMapping("/crew/crewUpdate.do")
	public String getProcess(){
		return "";
	}
	
}
