package kr.or.ddit.cor.partnership.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 제휴 업체 승인/미승인
// 업체가 제휴 업체 신청 리스트에서 승인/미승인 하는 프로그램

@Controller
public class CorPartnerUpdateController {
	@RequestMapping("/cor/partnershipUpdate.do")
	public String getProcess(){
		return "";
	}
}
