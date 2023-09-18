package kr.or.ddit.common.board.qnaboard.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.board.qnaboard.service.IQnaboardService;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;
import kr.or.ddit.vo.QnaboardVO;

//QnA게시판 조회
//QnA게시판을 조회하기 위한 프로그램이다.

/**
 * @author 서신원
 * @since 2019. 1. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 11.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class QnARetrieveController {
	
	@Inject
	IQnaboardService service;
	
	@ResponseBody
	@RequestMapping(value="/qnaboard/qnaboardList.do", produces="application/json;charset=UTF-8")
	public PagingInfoVO<QnaboardVO> getProcess(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord
			){
		
		PagingInfoVO<QnaboardVO> pagingVO = new PagingInfoVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		
		
		long totalRecord = service.retrieveTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<QnaboardVO> qnaboardList = service.retrieveQnaboardList(pagingVO);
		pagingVO.setDataList(qnaboardList);
		
		return pagingVO;
	}
	
	@RequestMapping("/qnaboard/qnaboardList.do")
	public String process(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			Model model
			){
		PagingInfoVO<QnaboardVO> pagingVO = getProcess(currentPage, searchType, searchWord);
		model.addAttribute("pagingVO", pagingVO);
		return "common/board/qnaboard/qnaboardList";
	}
	
	@RequestMapping("/qnaboard/qnaboardView.do")
	public String viewProcess(
			@RequestParam(name="what", required=true) long qnaboard_no,
			Model model){
		QnaboardVO qnaboard = service.retrieveQnaboard(qnaboard_no);
		model.addAttribute("qnaboard", qnaboard);
		return "common/board/qnaboard/qnaboardView";
	}
}
