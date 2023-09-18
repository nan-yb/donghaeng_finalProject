package kr.or.ddit.common.board.freeboard.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.freeboard.service.IFreeboardService;
import kr.or.ddit.vo.FreeboardVO;
import kr.or.ddit.vo.PagingInfoVO;

//자유게시판 조회 기능
//자유게시판의 게시글을 조회할 수 있는 로직

@Controller
@RequestMapping("/freeboard")
public class FreeboardRetrieveController {
	
	@Inject
	IFreeboardService service;
	
	@ResponseBody
	@RequestMapping(value="freeboardRetrieve.do", produces="application/json;charset=UTF-8")
	public PagingInfoVO<FreeboardVO> processAsync(
			@RequestParam(required=false) String searchType,	
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
	){
		FreeboardVO searchVO = new FreeboardVO();
		if(StringUtils.isNotBlank(searchWord)) {
			if(StringUtils.isBlank(searchType)) {
				searchVO.setMem_id(searchWord);
				searchVO.setBoard_title(searchWord);
				searchVO.setBoard_content(searchWord);
			}else {
				switch (searchType) {
				case "mem_id":
					searchVO.setMem_id(searchWord);
					break;
				case "title":
					searchVO.setBoard_title(searchWord);
					break;
				case "content":
					searchVO.setBoard_content(searchWord);	
					break;
				}
			}
		}
		PagingInfoVO<FreeboardVO> pagingVO = new PagingInfoVO<>(5,3);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		long totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);;
		
		List<FreeboardVO> boardList = service.retrieveBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		return pagingVO;
	}
	
	@RequestMapping(value="freeboardRetrieve.do")
	public String setProcess(
			@RequestParam(required=false) String searchType,	
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			Model model
			){
		PagingInfoVO<FreeboardVO> pagingVO =
				processAsync(searchType, searchWord, currentPage);
		model.addAttribute("pagingVO", pagingVO);
		return "common/board/freeboard/freeboardList";
	}
	
	@RequestMapping("freeboardView.do")
	public String process(
			@RequestParam(name = "what", required = true) long board_no,
			Model model
	){
		FreeboardVO board = service.retrieveBoard(board_no);
		
		model.addAttribute("board", board);
		
		return "common/board/freeboard/freeboardView";
	}
	
	@RequestMapping(value = "freeboardReport.do")
	public String getReport(
		@RequestParam(name = "what", required = true) long board_no,
		@RequestParam(name = "auth", required = true) String authMember,
		RedirectAttributes redirectAttributes
	){
		ServiceResult result = service.reportBoard(board_no, authMember);
		String viewName = null;
		switch(result){
		case OK:
			viewName = "redirect:/freeboard/freeboardView.do?what=" + board_no;
			redirectAttributes.addFlashAttribute("message", board_no + "번 글을 신고하였습니다.");
			break;
		
		default:
			viewName = "redirect:/freeboard/freeboardView.do?what=" + board_no;
			redirectAttributes.addFlashAttribute("message", "이미 신고를 하셨습니다.");
			break;
		}
		return viewName;
	}
}
