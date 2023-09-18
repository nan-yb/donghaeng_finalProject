package kr.or.ddit.common.board.postboard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.postboard.service.IReplyService;
import kr.or.ddit.vo.Review_ReplyVO;

@Controller
public class ReplyDeleteController{
	@Inject
	IReplyService service;
	
	@RequestMapping(value="/postboard/replyDelete.do", method=RequestMethod.POST)
	public String process(Review_ReplyVO reply, Model model) throws IOException, ServletException {
		
		ServiceResult result = service.removeReply(reply);
		String view = null;
		Map<String, String> errors = new HashMap<>();
		view = "jsonView";
		switch(result) {
			case OK:
				view = "redirect:/postboard/replyList.do?review_no="+reply.getReview_no();
				break;
			case INVALIDPASSWORD:
				errors.put("error", "true");
				errors.put("message", "사용자 ID or PASS 오류");
				break;
			default: // FAILED
				errors.put("error", "true");
				errors.put("message", " 서버 오류, 쫌따 다시 ");
		}
		model.addAttribute("errors", errors);		
		return view;
	}

}

















