package kr.or.ddit.cor.partnership.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 업체에서 제휴 업체 신청 리스트

@Controller
public class CorPartnerApplyListController {
	@RequestMapping("/cor/partnershipApplyList.do")
	public String getProcess(){
		return "";
	}
}
