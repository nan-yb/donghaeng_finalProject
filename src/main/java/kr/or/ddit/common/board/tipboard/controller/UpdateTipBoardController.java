package kr.or.ddit.common.board.tipboard.controller;

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
import kr.or.ddit.common.board.tipboard.service.ITBoardService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.Travel_TipVO;

@Controller
@RequestMapping("/tipboard/tipboardUpdate.do")
public class UpdateTipBoardController{
	@Inject
	ITBoardService service;

	@RequestMapping(method=RequestMethod.GET)
	public String getProcess(@RequestParam(name="what", required=true) long travel_tip_no,
			Model model ){
		Travel_TipVO travel_tip = service.retriveBoard(travel_tip_no);
		
		model.addAttribute("travel_tip", travel_tip);
		
		return "common/board/tipboard/tipboardForm";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String postProcess(
			@Validated(UpdateGroup.class) @ModelAttribute("travel_tip") Travel_TipVO travel_tip,
			BindingResult errors,
			Model model
		) {
		
		boolean valid = !errors.hasErrors();
		String view = null;
		if(valid) {
			ServiceResult result = service.modifyBoard(travel_tip);
			if(ServiceResult.OK.equals(result)) {
				// POST-redirect-GET : PRG패턴
				view = "redirect:/tipboard/tipboardView.do?what="+travel_tip.getTravel_tip_no();
			}else if(ServiceResult.FAILED.equals(result)){
				model.addAttribute("message", "서버 오류");
				view = "common/board/tipboard/tipboardForm";
			}else {
				model.addAttribute("message", "사용자 ID or PASS 오류");
				view = "common/board/tipboard/tipboardForm";
			}
		}else {
			view = "common/board/tipboard/tipboardForm";
		}
		return view;
	}
}









