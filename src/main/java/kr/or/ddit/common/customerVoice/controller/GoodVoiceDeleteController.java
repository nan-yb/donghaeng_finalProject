package kr.or.ddit.common.customerVoice.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.customerVoice.service.ICustomBoardService;
import kr.or.ddit.vo.CustomerVoiceVO;

@Controller
public class GoodVoiceDeleteController{
	@Inject
	ICustomBoardService service;

	@RequestMapping(value="/customer/goodVoiceDelete.do", method=RequestMethod.POST)
	public String process(
			@RequestParam(required=true)String customvoice_no, 
			@RequestParam(required=true)String customvoice_pass,
			@RequestParam(required=true)String mem_id,
			RedirectAttributes redirectAttributes
			){
		ServiceResult result =
				service.removeBoard(new CustomerVoiceVO(customvoice_no, customvoice_pass, mem_id));
		String viewName = null;
		switch (result) {
			case OK:
				viewName = "redirect:/customer/goodVoiceRetrieve.do";
				break;
			case INVALIDPASSWORD:
				viewName = "redirect:/customer/goodVoiceView.do?what="+customvoice_no;
				redirectAttributes.addFlashAttribute("message", "사용자 ID or PASS 오류");
				break;
	
			default:
				viewName = "redirect:/customer/goodVoiceView.do?what="+customvoice_no;
				redirectAttributes.addFlashAttribute("message", "서버 오류");
				break;
		}
		return viewName;
	}

}
















