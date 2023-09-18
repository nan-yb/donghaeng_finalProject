package kr.or.ddit.common.board.freeboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.freeboard.service.IFreeboardService;

@Controller
public class FreeboardRcmdController {
	@Inject
	IFreeboardService service;

	@RequestMapping(value = "/freeboard/freeboardRcmd.do")
	public String process(
			@RequestParam(name = "what", required = true) long board_no,
			@RequestParam(name = "auth", required=true) String authMember,
			RedirectAttributes redirectAttributes
		) {
		ServiceResult result = service.rcmdBoard(board_no, authMember);
		String viewName = null;
		switch (result) {
		case OK:
			viewName = "redirect:/freeboard/freeboardView.do?what=" + board_no;
			redirectAttributes.addFlashAttribute("message", board_no + "번 글을 추천하였습니다.");
			break;
		
		default:
			viewName = "redirect:/freeboard/freeboardView.do?what=" + board_no;
			redirectAttributes.addFlashAttribute("message", "이미 추천을 하셨습니다.");
			break;
		}
		return viewName;
	}
}
