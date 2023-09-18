package kr.or.ddit.common.board.qnaboard.controller;


import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.qnaboard.service.IQnaReplyService;
import kr.or.ddit.vo.QnaReplyVO;
import kr.or.ddit.vo.Review_ReplyVO;

//QnA게시판 답글 등록
//QnA게시판을 작성하기 위한 프로그램이다.

/**
 * @author 서신원
 * @since 2019. 1. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 16.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class CommentInsertController {
	
	@Inject
	IQnaReplyService service;
	
	@RequestMapping(value="/qnaboard/replyInsert.do", method=RequestMethod.POST)
	public String getProcess(
			@ModelAttribute("qnaReply") QnaReplyVO qnaReply,
			Model model
			){
		Map<String, String> errors = new LinkedHashMap<>();
		boolean valid = validate(qnaReply, errors);
		if(valid) {
			ServiceResult result = service.createQnaReply(qnaReply);
			if(ServiceResult.OK.equals(result)) {
				// 성공
				return "redirect:/qnaboard/qnaReplyList.do?qnaboard_no="+qnaReply.getQnaboard_no();
			}else {
				// 실패
				errors.put("error", "true");
				errors.put("message", "서버 오류");
			}
		}else {
			// 검증 실패
			errors.put("error", "true");
			errors.put("message", "검증 실패, 데이터 오류 확인");
		}
		model.addAttribute("errors", errors);
		return "jsonView";
	}
	
	private boolean validate(QnaReplyVO qnaReply, Map<String, String> errors) {
		boolean valid = true;

		if(qnaReply.getQnaboard_reply_content() !=null && qnaReply.getQnaboard_reply_content().length()>100) {
			valid = false;
			errors.put("qnaboard_reply_content", "내용의 길이는 100글자 미만");
		}
		return valid;
	}
	
	
}
