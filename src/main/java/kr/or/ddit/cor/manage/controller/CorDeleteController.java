package kr.or.ddit.cor.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//업체 삭제
// 자신의 업체를 삭제하는 프로그램

@Controller
public class CorDeleteController {
	@RequestMapping("/cor/corDelete.do")
	public String getProcess(){
		return "";
	}
}
