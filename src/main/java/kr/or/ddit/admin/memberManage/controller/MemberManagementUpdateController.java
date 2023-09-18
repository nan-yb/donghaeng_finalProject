package kr.or.ddit.admin.memberManage.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.admin.memberManage.service.MemberManagementServiceImpl;
import kr.or.ddit.vo.MemberVO;

// 등급 부여
// 관리자가 모든 회원의 등급을 부여할 수 있는 프로그램

@Controller
@RequestMapping("/admin/memberUpdate.do")
public class MemberManagementUpdateController {
	
	@Inject
	MemberManagementServiceImpl service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getProcess(
		@RequestParam(name="what" , required = true) String mem_id,
		Model model
	){
		MemberVO member = service.retrieveMemberManagement(mem_id);
		model.addAttribute("member", member);
		return "admin/memManage/memberView";
	}
}
