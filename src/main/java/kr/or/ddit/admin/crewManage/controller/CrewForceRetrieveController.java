package kr.or.ddit.admin.crewManage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//상세 크루 조회
//관리자가 선택한 크루의 상세 정보를 조회할 수 있는 프로그램

@Controller
public class CrewForceRetrieveController {
	@RequestMapping("/admin/crewRetrieve.do")
	public String getProcess(){
		return "";
	}
}
