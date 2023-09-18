package kr.or.ddit.member.mySchedule.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.mySchedule.service.IMyScheduleService;
import kr.or.ddit.vo.MyScheduleVO;

//일정수정
//등록한 일정을 수정하기 위한 로직

@Controller
public class MyScheduleUpdateController {
	
	@Inject
	IMyScheduleService scheduleService;
	
	@RequestMapping("/member/mypage/calendarUpdate.do")
	public String getProcess(
		long myschedule_no,
		String myschedule_startdate,
		String myschedule_enddate,
		Model model
	){
		
		MyScheduleVO scheduleVO = new MyScheduleVO();
		scheduleVO.setMyschedule_no(myschedule_no);
		scheduleVO.setMyschedule_startdate(myschedule_startdate);
		scheduleVO.setMyschedule_enddate(myschedule_enddate);
		String view;
		
		ServiceResult result = scheduleService.modifyMySchedule(scheduleVO);
		
		if(ServiceResult.OK.equals(result)) {
			// POST-redirect-GET : PRG패턴
			view = "redirect:/member/mypage/scheduleList.do";
		
		}else if(ServiceResult.FAILED.equals(result)){
			model.addAttribute("message", "서버 오류");
			view = "member/mypage/scheduleList";
		}else {
			model.addAttribute("message", "비밀번호 오류");
			view = "member/mypage/scheduleList";
		}
		
		return view;
	}
}
