package kr.or.ddit.member.myPage.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.admin.memberManage.service.IMemberManagementService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.PersonVO;

//개인정보 수정
// 로그인한 회원의 개인정보를 수정하기 위한 로직

@Controller
@RequestMapping("/member/mypage/updateInfo.do")
public class MemberUpdateController {

	@Inject
	IMemberManagementService service;


	@RequestMapping(method = RequestMethod.POST)
	public String postProcess(@Validated(UpdateGroup.class) @ModelAttribute("person") PersonVO person,
			BindingResult errors, Model model) {
		String view = "";
		ServiceResult result = service.modyfyMyInfo(person);
		if (ServiceResult.OK.equals(result)) {
			// POST-redirect-GET : PRG패턴
			view = "redirect:/member/mypage/myInfo.do";
		} else if (ServiceResult.FAILED.equals(result)) {
			model.addAttribute("message", "서버 오류");
			view = "member/mypage/myInfo";
		} else {
			model.addAttribute("message", "비밀번호 오류");
			view = "member/mypage/myInfo";
		}

		return view;
	}
}
