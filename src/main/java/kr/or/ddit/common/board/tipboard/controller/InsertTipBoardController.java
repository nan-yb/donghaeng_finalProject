package kr.or.ddit.common.board.tipboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.tipboard.service.ITBoardService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.Travel_TipVO;

@Controller
@RequestMapping("/tipboard/tipboardInsert.do")
public class InsertTipBoardController{
	@Inject
	ITBoardService service;

	@RequestMapping(method=RequestMethod.GET)
	public String getProcess() {
		return "common/board/tipboard/tipboardForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postProcess(
			@Validated(InsertGroup.class) @ModelAttribute("travel_tip") Travel_TipVO travel_tip,
			Errors errors,
			Model model) {
		boolean valid = !errors.hasErrors();
		String view = null;
		if(valid) {
			ServiceResult result = service.createBoard(travel_tip);
			if(ServiceResult.OK.equals(result)) {
				view = "redirect:/tipboard/tipboardRetrieve.do";
			}else if(ServiceResult.PKNOTFOUND.equals(result)){
				model.addAttribute("message", "로그인이 필요합니다");
				view = "login/loginForm";
			}else {
				model.addAttribute("message", "서버 오류");
				view = "tipboard/tipboardForm";
			}
		}else {
			view = "tipboard/tipboardForm";
		}
		return view;
	}

}


















