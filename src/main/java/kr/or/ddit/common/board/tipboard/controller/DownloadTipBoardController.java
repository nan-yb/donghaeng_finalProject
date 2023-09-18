package kr.or.ddit.common.board.tipboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.board.tipboard.service.ITBoardService;
import kr.or.ddit.vo.Travel_Tip_FileVO;

@Controller
public class DownloadTipBoardController{
	@Inject
	ITBoardService service;

	@RequestMapping("/tipboard/tipboardDownload.do")
	public String process(
			@RequestParam(name="what", required=true) long travel_tip_file_no, 
			@RequestHeader("user-agent") String agent,			
			Model model){
				
		Travel_Tip_FileVO pds = null;
		pds = service.downloadPds(travel_tip_file_no);
		model.addAttribute("pds", pds);
		
		return "downloadView";
	}

}










