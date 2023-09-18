package kr.or.ddit.common.customerVoice.controller;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.customerVoice.service.ICustomBoardService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.CompanyVO;
import kr.or.ddit.vo.CustomerVoiceVO;
import kr.or.ddit.vo.ReviewVO;

@Controller
@RequestMapping("/customer/goodVoiceUpdate.do")
public class GoodVoiceUpdateController{
	@Inject
	ICustomBoardService service;

	@RequestMapping(method=RequestMethod.GET)
	public String getProcess(@RequestParam(name="what", required=true) String customvoice_no,
			Model model ){
		CustomerVoiceVO customvoice = service.retriveBoard(customvoice_no);
		List<CompanyVO> company = service.getCompanyName();
		
		model.addAttribute("company",company);
		model.addAttribute("customvoice", customvoice);
		
		return "member/customerVoice/voiceUpdateForm";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String postProcess(
			@Validated(UpdateGroup.class) @ModelAttribute("customvoice") CustomerVoiceVO customvoice,
			BindingResult errors,
			Model model
		) {
		
		boolean valid = !errors.hasErrors();
		String view = null;
		if(valid) {
			ServiceResult result = service.modifyBoard(customvoice);
			if(ServiceResult.OK.equals(result)) {
				// POST-redirect-GET : PRG패턴
				view = "redirect:/customer/goodVoiceView.do?what="+customvoice.getCustomvoice_no();
			}else if(ServiceResult.FAILED.equals(result)){
				model.addAttribute("message", "서버 오류");
				view = "member/customerVoice/voiceUpdateForm";
			}else {
				model.addAttribute("message", "사용자 ID or PASS 오류");
				view = "member/customerVoice/voiceUpdateForm";
			}
		}else {
			view = "member/customerVoice/voiceUpdateForm";
		}
		return view;
	}
}









