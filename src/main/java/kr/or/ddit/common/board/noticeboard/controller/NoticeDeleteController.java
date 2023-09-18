package kr.or.ddit.common.board.noticeboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.noticeboard.servaice.INoticeboardService;

//공지게시판 삭제 기능
//공지사항을 삭제하기 위한 프로그램

@Controller
public class NoticeDeleteController {
	
	@Inject
	INoticeboardService service;
	
	@RequestMapping(value = "/noticeboard/noticeboardDelete.do", method = RequestMethod.POST)
	public String Process(
			@RequestParam(required=true)long notice_no, 
			RedirectAttributes redirectAttributes
		){
		ServiceResult result = service.removeBoard(notice_no);
		String viewName = null;
		switch(result){
		case OK:
			viewName = "redirect:/noticeboard/noticeboardRetrieve.do";
			break;
		case INVALIDPASSWORD:
			viewName = "redirect:/noticeboard/noticeboardView.do?what="+notice_no;
			redirectAttributes.addFlashAttribute("message", "비번 오류");
			break;
		default:
			viewName = "redirect:/noticeboard/noticeboardView.do?what="+notice_no;
			redirectAttributes.addFlashAttribute("message", "서버 오류");
			break;
		}
		return viewName;
	}
}
