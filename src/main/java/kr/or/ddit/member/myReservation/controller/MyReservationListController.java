package kr.or.ddit.member.myReservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//여행거래 내역 및 예약 내역 조회
//아이디를 자동입력 받아서 여행거래 내역 및 예약내역 처리를 위한 로직

@Controller
public class MyReservationListController {
	@RequestMapping("/member/mypage/myReservation.do")
	public String getProcess(){
		return "";
	}
}
