package kr.or.ddit.common.board.tipboard.controller;


import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.vo.Travel_TipVO;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.tipboard.service.ITBoardService;
import kr.or.ddit.vo.PagingInfoVO;


//후기게시판 조회
//후기 게시판의 모든 후기조회를 위한 로직

@Controller
@RequestMapping("/tipboard")
public class SelectTipBoardController {
	@Inject
	ITBoardService service;
	@ResponseBody
	@RequestMapping(value="tipboardRetrieve.do", produces="application/json;charset=UTF-8")
	public PagingInfoVO<Travel_TipVO> processAsync(
			@RequestParam(required=false) String searchType,	
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
	){
		Travel_TipVO searchVO = new Travel_TipVO();
		if(StringUtils.isNotBlank(searchWord)) {
			if(StringUtils.isBlank(searchType)) {
				searchVO.setTravel_tip_mem_id(searchWord);
				searchVO.setTravel_tip_title(searchWord);
				searchVO.setTravel_tip_content(searchWord);
			}else {
				switch (searchType) {
				case "mem_id":
					searchVO.setTravel_tip_mem_id(searchWord);
					break;
				case "title":
					searchVO.setTravel_tip_title(searchWord);
					break;
				case "content":
					searchVO.setTravel_tip_content(searchWord);	
					break;
				}
			}			
		}
		
		PagingInfoVO<Travel_TipVO> pagingVO = new PagingInfoVO<>(5,5);
		pagingVO.setCurrentPage(currentPage);
		/* 검색 정보 */
		pagingVO.setSearchVO(searchVO);
		long totalRecord = service.retriveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<Travel_TipVO> Travel_tipList = service.retriveBoardList(pagingVO);
		pagingVO.setDataList(Travel_tipList);
		return pagingVO;
	}
	
	@RequestMapping(value="tipboardRetrieve.do")
	public String process(
			@RequestParam(required=false) String searchType,	
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			Model model
		) {
		PagingInfoVO<Travel_TipVO> pagingVO =
				processAsync(searchType, searchWord, currentPage);
		model.addAttribute("pagingVO", pagingVO);
		return "common/board/tipboard/tipboardList";
	}
	
	
	@RequestMapping("tipboardView.do")
	public String process(
			@RequestParam(name="what", required=true) long travel_tip_no,
			Model model ){
		
		Travel_TipVO travel_tip = service.retriveBoard(travel_tip_no);
		
		model.addAttribute("travel_tip", travel_tip);
		
		return "common/board/tipboard/tipboardView";
	}
	
	@RequestMapping("tipboardReport.do")
	public String getReport(
			@RequestParam(name = "what", required = true) long travel_tip_no,
			@RequestParam(name = "auth", required = true) String authMember,
			RedirectAttributes redirectAttributes
	){
		ServiceResult result = service.reportBoard(travel_tip_no, authMember);
		String viewName = null;
		switch(result){
		case OK:
			viewName = "redirect:/tipboard/tipboardView.do?what=" + travel_tip_no;
			redirectAttributes.addFlashAttribute("message", travel_tip_no + "번 글을 신고하였습니다.");
			break;
		
		default:
			viewName = "redirect:/tipboard/tipboardView.do?what=" + travel_tip_no;
			redirectAttributes.addFlashAttribute("message", "이미 신고를 하셨습니다.");
			break;
		}
		return viewName;
	}
	
}
