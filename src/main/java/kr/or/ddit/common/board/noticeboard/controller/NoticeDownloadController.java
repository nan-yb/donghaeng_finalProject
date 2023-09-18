package kr.or.ddit.common.board.noticeboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.board.noticeboard.servaice.INoticeboardService;
import kr.or.ddit.vo.Notice_FileVO;

@Controller
public class NoticeDownloadController {
	@Inject
	INoticeboardService service;
	
	@RequestMapping("/noticeboard/noticeboardDownload.do")
	public String process(
			@RequestParam(name="what", required=true) long notice_file_no, 
			@RequestHeader("user-agent") String agent,			
			Model model
	){
		Notice_FileVO notice = null;
		notice = service.downloadPds(notice_file_no);
		model.addAttribute("notice", notice);
		
		return "downloadNotice";
	}
}
