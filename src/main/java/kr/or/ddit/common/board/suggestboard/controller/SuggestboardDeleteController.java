package kr.or.ddit.common.board.suggestboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.suggestboard.service.ISuggestBoardService;

//건의 게시판 삭제 기능
//건의게시판의 게시글을 삭제하는 로직

@Controller
public class SuggestboardDeleteController {
	@Inject
	ISuggestBoardService service;
	
	
	
	@RequestMapping(value="/suggestboard/suggestboardDelete.do", method=RequestMethod.POST)
	public String getProcess(
			@RequestParam(required=true) long bo_no,
			RedirectAttributes redirectAttributes
			){
		ServiceResult result = service.suggestDelete(bo_no);
		String view = null;
		switch (result) {
		case OK:
			view="redirect:/suggest/suggestList.do";
			break;

		case FAILED:
			view="redirect:/suggest/suggestView.do?what="+bo_no;
			redirectAttributes.addFlashAttribute("message","실패");
			break;
		}
		
		
		
		
		return view;
	}
}
