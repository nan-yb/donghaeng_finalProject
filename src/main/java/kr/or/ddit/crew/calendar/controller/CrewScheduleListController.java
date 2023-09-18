package kr.or.ddit.crew.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 크루 전체 일정 조회
// 모든 크루원은 크루 일정 리스트를 확인 가능하다.

@Controller
public class CrewScheduleListController {
	@RequestMapping("/crew/calendarList.do")
	public String getProcess(){
		return "";
	}
}
