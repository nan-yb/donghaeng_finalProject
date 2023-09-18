package kr.or.ddit.common.board.postboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.postboard.service.IBoardService;
import kr.or.ddit.vo.ReviewVO;

@Controller
public class DeletePostBoardController{
	@Inject
	IBoardService service;

	@RequestMapping(value="/postboard/postboardDelete.do", method=RequestMethod.POST)
	public String process(
			@RequestParam(required=true)long review_no, 
			@RequestParam(required=true)String review_mem_id,
			RedirectAttributes redirectAttributes
			){
		ServiceResult result =
				service.removeBoard(new ReviewVO(review_no, review_mem_id));
		String viewName = null;
		switch (result) {
			case OK:
				viewName = "redirect:/postboard/postboardRetrieve.do";
				break;
//			case INVALIDPASSWORD:
//				viewName = "redirect:/postboard/postboardView.do?what="+review_no;
//				redirectAttributes.addFlashAttribute("message", "사용자 ID or PASS 오류");
//				break;
	
			default:
				viewName = "redirect:/postboard/postboardView.do?what="+review_no;
				redirectAttributes.addFlashAttribute("message", "서버 오류");
				break;
		}
		return viewName;
	}

}
















