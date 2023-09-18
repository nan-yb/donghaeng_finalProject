package kr.or.ddit.admin.crewManage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//크루 강제 해체
//관리자가 크루를 강제로 해체하는 프로그램

@Controller
public class CrewForceDeleteController {
	@RequestMapping("/admin/crewDelete.do")
	public String getProcess(){
		return "";
	}
}
