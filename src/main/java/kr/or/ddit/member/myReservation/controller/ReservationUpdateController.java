package kr.or.ddit.member.myReservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//여행 상품 예약 수정
//회원이 예약한 상품을 수정하는 프로그램

@Controller
public class ReservationUpdateController {
	@RequestMapping("/member/reservationUpdate.do")
	public String getProcess(){
		return "";
	}
}
