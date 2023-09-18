package kr.or.ddit.common.board.qnaboard.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.qnaboard.service.IQnaboardService;
import kr.or.ddit.vo.QnaboardVO;

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
public class QnaUpdateController {
	
	@Inject
	IQnaboardService service;
	
	@RequestMapping(value="/qnaboard/qnaboardUpdate.do", method=RequestMethod.GET)
	public String getProcess(
			@RequestParam(name="what", required=true) long qnaboard_no,
			Model model
			){
		QnaboardVO qnaboard = service.retrieveQnaboard(qnaboard_no);
		model.addAttribute("qnaboard", qnaboard);
		return "common/board/qnaboard/qnaboardForm";
	}
	
	@RequestMapping(value="/qnaboard/qnaboardUpdate.do", method=RequestMethod.POST)
	public String postProcess(
			@Validated(Update.class) @ModelAttribute("qnaboard") QnaboardVO qnaboard,
			BindingResult errors, Model model
			){
		boolean valid = !errors.hasErrors();
		String view = null;
		if (valid) {
			ServiceResult result = service.modifyQnaboard(qnaboard);
			if (ServiceResult.OK.equals(result)) {
				view = "redirect:/qnaboard/qnaboardView.do?what="+qnaboard.getQnaboard_no();
			} else {
				model.addAttribute("message", "서버오류");
				view = "common/board/qnaboard/qnaboardForm";
			}
		} else {
			view = "common/board/qnaboard/qnaboardForm";
		}
		return view;
	}
}
