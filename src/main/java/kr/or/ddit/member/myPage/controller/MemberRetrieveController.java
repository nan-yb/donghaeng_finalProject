package kr.or.ddit.member.myPage.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.admin.memberManage.service.MemberManagementServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PersonVO;

//개인정보 조회
//일반회원 개인정보조회를 위한 로직

@Controller
public class MemberRetrieveController {
	
	@Inject
	MemberManagementServiceImpl service;
	
	@RequestMapping("common/mypage/viewInfo.do")
	public String process(Model model, @RequestParam(required = true) String who){
//		
//		MemberVO member = service.retrieveMemberManagement(who);
//		model.addAttribute("member", member);
//		
		return "member/mypage/memberViewInfo";
	}
	
	@RequestMapping("/member/mypage/myInfo.do")
	public String getProcess(
		HttpServletRequest req, HttpServletResponse resp,
		Model model
	) throws IOException{
		HttpSession session = req.getSession(false);
		if (session==null||session.isNew()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "로그인 한 이후에 마이페이지 요청가능합니다.");
			return null;
		}
		PersonVO authMember = (PersonVO) session.getAttribute("authMember");
		PersonVO person = service.retrievePerson(authMember.getPerson_id());
	
		req.setAttribute("person", person);
		return "member/mypage/myInfo";
	}
}
