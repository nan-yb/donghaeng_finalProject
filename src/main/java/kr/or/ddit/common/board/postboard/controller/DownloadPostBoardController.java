package kr.or.ddit.common.board.postboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.board.postboard.service.IBoardService;
import kr.or.ddit.vo.Review_FileVO;

@Controller
public class DownloadPostBoardController{
	@Inject
	IBoardService service;

	@RequestMapping("/postboard/postboardDownload.do")
	public String process(
			@RequestParam(name="what", required=true) long review_file_no, 
			@RequestHeader("user-agent") String agent,			
			Model model){
				
		Review_FileVO pds = null;
		pds = service.downloadPds(review_file_no);
		model.addAttribute("pds", pds);
		
		return "downloadView";
	}

}










