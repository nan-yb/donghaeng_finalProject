package kr.or.ddit.common.board.freeboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.freeboard.service.IFreeboardService;
import kr.or.ddit.vo.FreeboardVO;

//자유게시판 삭제 기능
//자유게시판의 게시글을 삭제하기 위한 로직

@Controller
public class FreeboardDeleteController {
	
	@Inject
	IFreeboardService service;
	
	@RequestMapping(value = "/freeboard/freeboardDelete.do", method=RequestMethod.POST)
	public String proecess(
			@RequestParam(required=true)long board_no, 
			RedirectAttributes redirectAttributes
	){
		FreeboardVO boardVO = service.retrieveBoard(board_no);
		ServiceResult result = service.removeBoard(boardVO);
		String viewName = null;
		
		switch (result) {
		case OK:
			viewName = "redirect:/freeboard/freeboardRetrieve.do";
			break;
//		case INVALIDPASSWORD:
//			viewName = "redirect:/freeboard/freeboardView.do?what="+board_no;
//			redirectAttributes.addFlashAttribute("message", "비번 오류");
//			break;

		default:
			viewName = "redirect:/freeboard/freeboardView.do?what="+board_no;
			redirectAttributes.addFlashAttribute("message", "서버 오류");
			break;
	}
	return viewName;
	}
}
