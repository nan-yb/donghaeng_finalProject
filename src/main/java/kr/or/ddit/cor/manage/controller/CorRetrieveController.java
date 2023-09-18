package kr.or.ddit.cor.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 업체 조회
// 자신의 업체를 상세 조회하는 프로그램

@Controller
public class CorRetrieveController {
	@RequestMapping("/cor/corRetrieve.do")
	public String getProcess(){
		return "corporation/mypage/corporationViewInfo";
	}
}
