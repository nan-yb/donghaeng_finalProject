package kr.or.ddit.member.myReservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//여행 상품 예약 삭제
// 회원이 예약한 상품을 삭제하는 프로그램

@Controller
public class ReservationDeleteController {
	@RequestMapping("/member/reservationDelete.do")
	public String getProcess(){
		return "";
	}
}
