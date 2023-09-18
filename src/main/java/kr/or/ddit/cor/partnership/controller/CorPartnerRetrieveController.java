package kr.or.ddit.cor.partnership.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//제휴 업체 조회
//자신의 업체와 제휴관계인 업체들을 조회하는 프로그램

@Controller
public class CorPartnerRetrieveController {
	@RequestMapping("/cor/partnershipRetrieve.do")
	public String getProcess(){
		return "";
	}
}
