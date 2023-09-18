package kr.or.ddit.common.board.qnaboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.qnaboard.service.IQnaboardService;
import kr.or.ddit.vo.QnaboardVO;

//QnA게시판 질문 등록
//QnA게시판의 질문을 작성하기 위한 프로그램이다.

/**
 * @author 서신원
 * @since 2019. 1. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 11.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class QuestionInsertController {
	
	@Inject
	IQnaboardService service;
	
	@RequestMapping(value="/qnaboard/qnaboardForm.do", method=RequestMethod.GET)
	public String getProcess(){
		return "common/board/qnaboard/qnaboardForm";
	}
	
	@RequestMapping(value="/qnaboard/qnaboardForm.do", method=RequestMethod.POST)
	public String postProcess(
			@ModelAttribute("qnaboard") QnaboardVO qnaboard,
			Errors errors, Model model
			){
		boolean valid = !errors.hasErrors();
		String view = null;
		if (valid) {
			ServiceResult result = service.createQnaboard(qnaboard);
			if (ServiceResult.OK.equals(result)) {
				view = "redirect:/qnaboard/qnaboardList.do";
			}else {
				model.addAttribute("message", "서버 오류");
				view = "common/board/qnaboard/qnaboardForm";
			}
		} else {
			view = "common/board/qnaboard/qnaboardForm";
		}
		return view;
	}
}
