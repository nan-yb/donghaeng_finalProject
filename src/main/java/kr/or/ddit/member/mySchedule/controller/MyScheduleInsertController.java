package kr.or.ddit.member.mySchedule.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.mySchedule.service.IMyScheduleService;
import kr.or.ddit.vo.MyScheduleVO;

//일정등록
//회원의 일정을 추가하기 위한 로직

/**
 * @author 서신원
 * @since 2019. 1. 15.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 15.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class MyScheduleInsertController {
	
	@Inject
	IMyScheduleService service;
	
	@RequestMapping(value="/member/mypage/scheduleInsert.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public String postProcess(
			@ModelAttribute("schedule") MyScheduleVO schedule,
			Errors errors, Model model
			){
		String view = null;

		ServiceResult result = service.createMySchedule(schedule);
		if (ServiceResult.OK.equals(result)) {
			return "redirect:/member/mypage/scheduleList.do";
		} else {
			model.addAttribute("message", "서버오류");
			view = "member/mypage/scheduleList";
		}
		return view;
	}
}
