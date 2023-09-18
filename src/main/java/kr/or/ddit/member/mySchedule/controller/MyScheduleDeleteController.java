package kr.or.ddit.member.mySchedule.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.mySchedule.service.IMyScheduleService;

//일정삭제
//일정을 삭제하기 위한 로직

@Controller
public class MyScheduleDeleteController {

	@Inject
	IMyScheduleService service;

	@RequestMapping( value="/member/mypage/calendarDelete.do", method=RequestMethod.POST)
	public String postProcess(
			@RequestParam(required=true)long myschedule_no,
			Model model
	){
		
		String view = null;
		ServiceResult result = service.removeMySchedule(myschedule_no);
		
		if (ServiceResult.OK.equals(result)) {
			view = "redirect:/member/mypage/scheduleList.do";
		} else {
			model.addAttribute("message", "서버오류");
			view = "member/mypage/scheduleForm";
		}
		return view;
	}
}
