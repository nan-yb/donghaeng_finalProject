package kr.or.ddit.common.board.freeboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.freeboard.service.IFreeReplyService;
import kr.or.ddit.vo.Free_ReplyVO;

@Controller
public class FreeReplyUpdateController {
	@Inject
	IFreeReplyService service;
	
	@RequestMapping(value = "/freeboard/replyUpdate.do", method = RequestMethod.POST)
	public String process(
		Free_ReplyVO reply, Model model	
	){
		ServiceResult result = service.modifyReply(reply);
		String view = null;
		Map<String, String> errors = new HashMap<>();
		view = "jsonView";
		switch (result) {
		case OK:
			view = "redirect:/freeboard/replyList.do?board_no="+reply.getBoard_no();
			break;
//		case INVALIDPASSWORD:
//			errors.put("error", "true");
//			errors.put("message", "비밀번호 오류");
//			break;
		default:
			errors.put("error", "true");
			errors.put("message", " 서버 오류, 쫌따 다시 ");
		}
		model.addAttribute("errors", errors);
		return view;
	}
}
