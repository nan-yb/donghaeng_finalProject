package kr.or.ddit.cor.partnership.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//제휴 업체 해지
//업체가 자신과의 제휴업체를 해지하는 프로그램

@Controller
public class CorPartnerDeleteController {
	@RequestMapping("/cor/partnershipDelete.do")
	public String getProcess(){
		return "";
	}
}
