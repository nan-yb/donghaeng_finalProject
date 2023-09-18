package kr.or.ddit.common.board.postboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.postboard.service.IBoardService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.ReviewVO;

@Controller
@RequestMapping("/postboard/postboardInsert.do")
public class InsertPostBoardController{
	@Inject
	IBoardService service;

	@RequestMapping(method=RequestMethod.GET)
	public String getProcess() {
		return "common/board/postboard/postboardForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postProcess(
			@Validated(InsertGroup.class) @ModelAttribute("review") ReviewVO review,
			Errors errors,
			Model model) {
		boolean valid = !errors.hasErrors();
		String view = null;
		if(valid) {
			ServiceResult result = service.createBoard(review);
			if(ServiceResult.OK.equals(result)) {
				view = "redirect:/postboard/postboardRetrieve.do";
			}else if(ServiceResult.PKNOTFOUND.equals(result)){
				model.addAttribute("message", "로그인이 필요합니다");
				view = "login/loginForm";
			}
			else {
				model.addAttribute("message", "서버 오류");
				view = "common/board/postboard/postboardForm";
			}
		}else {
			view = "common/board/postboard/postboardForm";
		}
		return view;
	}

}


















