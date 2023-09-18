package kr.or.ddit.common.board.qnaboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.qnaboard.service.IQnaboardService;

/**
 * @author 서신원
 * @since 2019. 1. 12.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 12.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class QnaDeleteController {
	
	@Inject
	IQnaboardService service;
	
	@RequestMapping(value="/qnaboard/qnaboardDelete.do", method=RequestMethod.POST)
	public String process(
			@RequestParam(required=true) long qnaboard_no,
			RedirectAttributes redirectAttributes
			){
		ServiceResult result = service.removeQnaboard(qnaboard_no);
		String view = null;
		switch (result) {
		case OK:
			view = "redirect:/qnaboard/qnaboardList.do";
			break;
		case FAILED:
			view = "redirect:/qnaboard/qnaboardView.do?what="+qnaboard_no;
			redirectAttributes.addFlashAttribute("message", "삭제실패");
			break;
		}
		return view;
	}
}
