package kr.or.ddit.crew.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//크루 삭제
//크루장이 자신의 크루를 삭제 하기 위한 로직

@Controller
public class CrewDeleteController {
	@RequestMapping("/crew/crewDelete.do")
	public String getProcess(){
		return "";
	}
}
