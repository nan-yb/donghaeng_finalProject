package kr.or.ddit.cor.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//업체 수정
//자신의 업체를 수정하는 프로그램

@Controller
public class CorUpdateController {
	@RequestMapping("/cor/corUpdate.do")
	public String getProcess(){
		return "";
	}
}
