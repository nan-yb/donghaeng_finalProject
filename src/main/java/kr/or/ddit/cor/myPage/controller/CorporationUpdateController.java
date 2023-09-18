package kr.or.ddit.cor.myPage.controller;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Update;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.cor.myPage.service.ICorMyPageService;
import kr.or.ddit.vo.PersonVO;

//개인정보 수정
//로그인한 회원의 개인정보를 수정하기 위한 로직

/**
 * @author 서신원
 * @since 2019. 1. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 22.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class CorporationUpdateController {
	
	@Inject
	ICorMyPageService service;
	
	@RequestMapping(value="/common/mypage/corUpdateInfo.do", method=RequestMethod.POST)
	public String postProcess(
			@Validated(Update.class) @ModelAttribute("person") PersonVO person,
			BindingResult errors,
			Model model
			){
		String view = null;
		String message = null;
		boolean valid = !errors.hasErrors();
		if (valid) {
			ServiceResult result = service.modifyCorMyPage(person);
			switch (result) {
			case OK:
				view = "redirect:/common/mypage/corporationViewInfo.do";
				break;
			case FAILED:
				view = "member/mypage/corporationViewInfo";
				message = "서버 오류로 인한 실패, 다시 해주세요";
				break;
			}
			model.addAttribute("message", message);
		} else {
			view = "member/mypage/corporationViewInfo";
		}
		return view;
	}
}
