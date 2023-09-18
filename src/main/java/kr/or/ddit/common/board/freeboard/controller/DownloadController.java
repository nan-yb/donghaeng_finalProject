package kr.or.ddit.common.board.freeboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.board.freeboard.service.IFreeboardService;
import kr.or.ddit.vo.Free_FileVO;

@Controller
public class DownloadController {
	@Inject
	IFreeboardService service;
	
	@RequestMapping("/freeboard/freeboardDownload.do")
	public String process(
			@RequestParam(name = "what", required = true) long board_file_no,
			@RequestHeader("user-agent") String agent,
			Model model
	){
		Free_FileVO board = null;
		board = service.downloadFreeFile(board_file_no);
		model.addAttribute("free", board);
		
		return "downloadFree";
	}
}
