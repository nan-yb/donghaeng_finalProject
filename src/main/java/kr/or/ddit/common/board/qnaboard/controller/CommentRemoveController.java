package kr.or.ddit.common.board.qnaboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.qnaboard.service.IQnaReplyService;
import kr.or.ddit.vo.QnaReplyVO;

@Controller
public class CommentRemoveController {
	
	@Inject
	IQnaReplyService service;
	
	@RequestMapping(value="/qnaboard/qnaReplyDelete.do")
	public String process(
			QnaReplyVO qnaReply,
			Model model
			){
		ServiceResult result = service.removeQnaReply(qnaReply);
		String view = null;
		Map<String, String> errors = new HashMap<>();
		view = "jsonView";
		if (ServiceResult.OK.equals(result)) {
			view = "redirect:/qnaboard/qnaReplyList.do?qnaboard_no="+qnaReply.getQnaboard_no();
		} else {
			errors.put("error", "true");
			errors.put("message", "서버오류");
		}
		model.addAttribute("errors", errors);
		return view;
	}
	
}
