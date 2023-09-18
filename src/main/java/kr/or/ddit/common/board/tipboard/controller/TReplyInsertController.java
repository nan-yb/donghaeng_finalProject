package kr.or.ddit.common.board.tipboard.controller;

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
import kr.or.ddit.common.board.tipboard.service.ITReplyService;
import kr.or.ddit.vo.Travel_Tip_ReplyVO;

@Controller
public class TReplyInsertController{
	@Inject
	ITReplyService service;

	@RequestMapping(value="/tipboard/replyInsert.do", method=RequestMethod.POST)
	public String process(@ModelAttribute("reply") Travel_Tip_ReplyVO reply, Model model){
		Map<String, String> errors = new LinkedHashMap<>();
		boolean valid = validate(reply, errors);
		if(valid) {
			ServiceResult result = service.createReply(reply);
			if(ServiceResult.OK.equals(result)) {
				// 성공
				return "redirect:/tipboard/replyList.do?travel_tip_no=" +reply.getTravel_tip_no();
			}else {
				// 실패
				errors.put("error", "true");
				errors.put("message", "서버 오류, 쫌따 다시");
			}
		}else {
			// 검증 실패
			errors.put("error", "true");
			errors.put("message", "로그인이 필요합니다.");
		}
		model.addAttribute("errors", errors);
		return "jsonView";
	}

	private boolean validate(Travel_Tip_ReplyVO reply, Map<String, String> errors) {
		boolean valid = true;

		if (reply.getTravel_tip_no() == null || reply.getTravel_tip_no() < 0) {
			valid = false;
			errors.put("travel_tip_no", "게시글번호 누락");
		}
		if (StringUtils.isBlank(reply.getTravel_tip_mem_id())) {
			valid = false;
			errors.put("travel_tip_reply_no", "덧글작성자 누락");
		}
		if(reply.getTravel_tip_reply_content()!=null && reply.getTravel_tip_reply_content().length()>100) {
			valid = false;
			errors.put("travel_tip_reply_content", "내용의 길이는 100글자 미만");
		}
		return valid;
	}

}

















