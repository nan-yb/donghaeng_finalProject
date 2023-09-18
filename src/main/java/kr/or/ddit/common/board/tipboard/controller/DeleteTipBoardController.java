package kr.or.ddit.common.board.tipboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.tipboard.service.ITBoardService;
import kr.or.ddit.vo.Travel_TipVO;

@Controller
public class DeleteTipBoardController{
	@Inject
	ITBoardService service;

	@RequestMapping(value="/tipboard/tipboardDelete.do", method=RequestMethod.POST)
	public String process(
			@RequestParam(required=true)long travel_tip_no, 
			@RequestParam(required=true)String travel_tip_pass,
			@RequestParam(required=true)String travel_tip_mem_id,
			RedirectAttributes redirectAttributes
			){
		ServiceResult result =
				service.removeBoard(new Travel_TipVO(travel_tip_no, travel_tip_mem_id));
		String viewName = null;
		switch (result) {
			case OK:
				viewName = "redirect:/tipboard/tipboardRetrieve.do";
				break;
			case INVALIDPASSWORD:
				viewName = "redirect:/tipboard/tipboardView.do?what="+travel_tip_no;
				redirectAttributes.addFlashAttribute("message", "사용자 ID or PASS 오류");
				break;
	
			default:
				viewName = "redirect:/tipboard/tipboardView.do?what="+travel_tip_no;
				redirectAttributes.addFlashAttribute("message", "서버 오류");
				break;
		}
		return viewName;
	}

}
















