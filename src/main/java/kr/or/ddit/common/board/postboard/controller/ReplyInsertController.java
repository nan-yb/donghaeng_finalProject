package kr.or.ddit.common.board.postboard.controller;

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
import kr.or.ddit.common.board.postboard.service.IReplyService;
import kr.or.ddit.vo.Review_ReplyVO;

@Controller
public class ReplyInsertController{
	@Inject
	IReplyService service;

	@RequestMapping(value="/postboard/replyInsert.do", method=RequestMethod.POST)
	public String process(
		@ModelAttribute("reply") Review_ReplyVO reply,
		Model model
	){
		Map<String, String> errors = new LinkedHashMap<>();
		boolean valid = validate(reply, errors);
		if(valid) {
			ServiceResult result = service.createReply(reply);
			if(ServiceResult.OK.equals(result)) {
				// 성공
				return "redirect:/postboard/replyList.do?review_no=" +reply.getReview_no();
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

	private boolean validate(Review_ReplyVO reply, Map<String, String> errors) {
		boolean valid = true;

		if (reply.getReview_no() == null || reply.getReview_no() < 0) {
			valid = false;
			errors.put("review_no", "게시글번호 누락");
		}
		if (StringUtils.isBlank(reply.getReview_reply_mem_id())) {
			valid = false;
			errors.put("review_reply_mem_id", "덧글작성자 누락");
		}
		if(reply.getReview_reply_content()!=null && reply.getReview_reply_content().length()>100) {
			valid = false;
			errors.put("review_reply_content", "내용의 길이는 100글자 미만");
		}
		return valid;
	}

}

















