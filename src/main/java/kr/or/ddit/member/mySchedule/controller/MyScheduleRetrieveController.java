package kr.or.ddit.member.mySchedule.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.member.mySchedule.service.IMyScheduleService;
import kr.or.ddit.vo.MyScheduleVO;

//일정조회
//등록한 일정을 조회하기 위한 로직

/**
 * @author 서신원
 * @since 2019. 1. 14.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 14.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class MyScheduleRetrieveController {
	
	@Inject
	IMyScheduleService service;
	
	
	@RequestMapping(value="/member/mypage/scheduleList.do", method=RequestMethod.GET)
	public String getProcess(){
		return "member/mypage/scheduleList";
	}
	
	@ResponseBody
	@RequestMapping(value="/member/mypage/scheduleList.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public List<MyScheduleVO> postProcess(
			Model model,
			@RequestParam(required = true) String mem_id
			){
		List<MyScheduleVO> scheduleList = service.retrieveMyScheduleList(mem_id);
		return scheduleList;
	}
	
	@ResponseBody
	@RequestMapping(value="/member/mypage/scheduleView.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public MyScheduleVO postViewProcess(
			Model model,
			@RequestParam(required = true)String myschedule_no
			){
		MyScheduleVO schedule = service.retrieveMySchedule(Long.parseLong(myschedule_no));
		return schedule;
	}
	
	
}
