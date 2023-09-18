package kr.or.ddit.common.board.suggestboard.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.board.suggestboard.service.ISuggestBoardService;
import kr.or.ddit.vo.FreeboardVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.SuggestBoardVO;

//건의 게시판 조회 기능
//건의게시판의 게시글을 조회하는 로직

@Controller
public class SuggestboardRetrieveController {
	
	@Inject
	ISuggestBoardService service;
	
	@ResponseBody
	@RequestMapping(value="/suggest/suggestList.do", produces="application/json;charset=UTF-8")
	public PagingInfoVO<SuggestBoardVO> getProcess(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord
			){
		SuggestBoardVO searchVO = new SuggestBoardVO();
		if(StringUtils.isNotBlank(searchWord)) {
			if(StringUtils.isBlank(searchType)) {
				searchVO.setMem_id(searchWord);
				searchVO.setSuggest_board_title(searchWord);
				searchVO.setSuggest_board_content(searchWord);
			}else {
				switch (searchType) {
				case "writer":
					searchVO.setMem_id(searchWord);
					break;
				case "title":
					searchVO.setSuggest_board_title(searchWord);
					break;
				case "content":
					searchVO.setSuggest_board_content(searchWord);	
					break;
				}
			}
		}
		
		PagingInfoVO<SuggestBoardVO> pagingVO = new PagingInfoVO<>(5,5);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		long totalRecord = service.retrieveTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);;
		
		List<SuggestBoardVO> boardList = service.suggestList(pagingVO);
		pagingVO.setDataList(boardList);
		
		System.out.println(pagingVO.getDataList());
		return pagingVO;
	}
	
	
	
	@RequestMapping("/suggest/suggestList.do")
	public String Process(Model model,
			@RequestParam(name="page", required=false,defaultValue="1") int currentPage,
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord
			){
		
		PagingInfoVO<SuggestBoardVO> pagingVO = getProcess(currentPage, searchType, searchWord);
		model.addAttribute("pagingVO", pagingVO);
		return "common/board/suggestboard/suggestboardList";
	}
	

	@RequestMapping("/suggest/suggestView.do")
	public String getProcess(
			Model model,
			@RequestParam(name="what", required=true)long bo_no
			){
		SuggestBoardVO suggest =  service.suggestSelect(bo_no);
		model.addAttribute("suggest", suggest);
		return "common/board/suggestboard/suggestboardView";
	}
	
}
