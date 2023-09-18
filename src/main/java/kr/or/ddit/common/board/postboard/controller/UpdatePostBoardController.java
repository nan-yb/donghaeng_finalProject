package kr.or.ddit.common.board.postboard.controller;

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
import kr.or.ddit.common.board.postboard.service.IBoardService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.ReviewVO;

@Controller
@RequestMapping("/postboard/postboardUpdate.do")
public class UpdatePostBoardController{
	@Inject
	IBoardService service;

	@RequestMapping(method=RequestMethod.GET)
	public String getProcess(@RequestParam(name="what", required=true) long review_no,
			Model model ){
		ReviewVO review = service.retriveBoard(review_no);
		
		model.addAttribute("review", review);
		
		return "common/board/postboard/postboardForm";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String postProcess(
			@Validated(UpdateGroup.class) @ModelAttribute("review") ReviewVO review,
			BindingResult errors,
			Model model
		) {
		
		boolean valid = !errors.hasErrors();
		String view = null;
		if(valid) {
			ServiceResult result = service.modifyBoard(review);
			if(ServiceResult.OK.equals(result)) {
				// POST-redirect-GET : PRG패턴
				view = "redirect:/postboard/postboardView.do?what="+review.getReview_no();
			}else if(ServiceResult.FAILED.equals(result)){
				model.addAttribute("message", "서버 오류");
				view = "common/board/postboard/postboardForm";
//			}else {
//				model.addAttribute("message", "사용자 ID or PASS 오류");
//				view = "common/board/postboard/postboardForm";
			}
		}else {
			view = "common/board/postboard/postboardForm";
		}
		return view;
	}
}









