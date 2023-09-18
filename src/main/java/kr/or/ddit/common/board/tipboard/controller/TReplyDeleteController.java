package kr.or.ddit.common.board.tipboard.controller;

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
import kr.or.ddit.common.board.tipboard.service.ITReplyService;
import kr.or.ddit.vo.Travel_Tip_ReplyVO;

@Controller
public class TReplyDeleteController{
	@Inject
	ITReplyService service;
	
	@RequestMapping(value="/tipboard/replyDelete.do", method=RequestMethod.POST)
	public String process(Travel_Tip_ReplyVO reply, Model model) throws IOException, ServletException {
		
		ServiceResult result = service.removeReply(reply);
		String view = null;
		Map<String, String> errors = new HashMap<>();
		view = "jsonView";
		switch(result) {
			case OK:
				view = "redirect:/tipboard/replyList.do?travel_tip_no="+reply.getTravel_tip_no();
				break;
			default: // FAILED
				errors.put("error", "true");
				errors.put("message", " 서버 오류, 쫌따 다시 ");
		}
		model.addAttribute("errors", errors);		
		return view;
	}

}

















