package kr.or.ddit.common.customerVoice.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.customerVoice.service.ICustomBoardService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.CompanyVO;
import kr.or.ddit.vo.CustomerVoiceVO;

@Controller
@RequestMapping("/customer/goodVoiceInsert.do")
public class GoodVoiceInsertController{
	@Inject
	ICustomBoardService service;

	@RequestMapping(method=RequestMethod.GET)
	public String getProcess(
			Model model
			) {
		List<CompanyVO> company = service.getCompanyName();
		
		model.addAttribute("company",company);
		
		return "member/customerVoice/voiceForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postProcess(
			@Validated(InsertGroup.class) @ModelAttribute("customvoice") CustomerVoiceVO customvoice,
			Errors errors,
			Model model) {
		boolean valid = !errors.hasErrors();
		String view = null;
		if(valid) {
			ServiceResult result = service.createBoard(customvoice);
			if(ServiceResult.OK.equals(result)) {
				view = "redirect:/customer/goodVoiceRetrieve.do";
			}else if(ServiceResult.INVALIDPASSWORD.equals(result)){
				model.addAttribute("message", "비밀번호 오류");
				view = "member/customerVoice/voiceForm";
			}else if(ServiceResult.PKNOTFOUND.equals(result)){
				model.addAttribute("message", "로그인이 필요합니다");
				view = "login/loginForm";
			}
			else {
				model.addAttribute("message", "서버 오류");
				view = "member/customerVoice/voiceForm";
			}
		}else {
			view = "member/customerVoice/voiceForm";
		}
		return view;
	}

}


















