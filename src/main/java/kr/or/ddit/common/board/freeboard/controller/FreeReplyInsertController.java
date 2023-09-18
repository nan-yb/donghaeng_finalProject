package kr.or.ddit.common.board.freeboard.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.freeboard.service.IFreeReplyService;
import kr.or.ddit.vo.Free_ReplyVO;

@Controller
public class FreeReplyInsertController {
	@Inject
	IFreeReplyService service;
	
	@RequestMapping(value="/freeboard/replyInsert.do", method=RequestMethod.POST)
	public String process(
			@ModelAttribute("reply") Free_ReplyVO reply
			, Model model
	){
		Map<String, String> errors = new LinkedHashMap<>();
		boolean valid = validate(reply, errors);
		if(valid) {
			ServiceResult result = service.createReply(reply);
			if(ServiceResult.OK.equals(result)) {
				// 성공
				return "redirect:/freeboard/replyList.do?board_no=" +reply.getBoard_no();
			}else {
				// 실패
				errors.put("error", "true");
				errors.put("message", "서버 오류, 쫌따 다시");
			}
		}else {
			// 검증 실패
			errors.put("error", "true");
			errors.put("message", "검증 실패, 데이터 오류 확인");
		}
		model.addAttribute("errors", errors);
		return "jsonView";
	}
	
	private boolean validate(Free_ReplyVO reply, Map<String, String> errors){
		boolean valid = true;
		
		if(reply.getBoard_no() == null || reply.getBoard_no() < 0){
			valid = false;
			errors.put("board_no", "게시글번호 누락");
		}
		if (StringUtils.isBlank(reply.getBoard_reply_mem_id())) {
			valid = false;
			errors.put("board_reply_mem_id", "덧글작성자 누락");
		}
		if(reply.getBoard_reply_content()!=null && reply.getBoard_reply_content().length()>100) {
			valid = false;
			errors.put("getBoard_reply_content", "내용의 길이는 100글자 미만");
		}
		return valid;
	}
}
