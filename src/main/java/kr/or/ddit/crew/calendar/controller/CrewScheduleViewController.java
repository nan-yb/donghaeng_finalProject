package kr.or.ddit.crew.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//크루 상세 일정 조회
//모든 크루원은 크루 일정 리스트를 클릭하게 되면 해당일정의 자세한 상세 내용을
//확인할 수 있다.

@Controller
public class CrewScheduleViewController {
	@RequestMapping("/crew/calendarView.do")
	public String getProcess(){
		return "";
	}
}
