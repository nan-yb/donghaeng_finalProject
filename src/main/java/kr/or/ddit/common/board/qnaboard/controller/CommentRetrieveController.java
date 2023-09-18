package kr.or.ddit.common.board.qnaboard.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.board.qnaboard.service.IQnaReplyService;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.QnaReplyVO;

/**
 * @author 서신원
 * @since 2019. 1. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 16.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class CommentRetrieveController {
	
	@Inject
	IQnaReplyService service;
	
	@ResponseBody
	@RequestMapping(value="/qnaboard/qnaReplyList.do", produces="application/json;charset=UTF-8")
	public PagingInfoVO<QnaReplyVO> getProcess(
			@RequestParam(required=true) long qnaboard_no,
			@RequestParam(name="page", required=false, defaultValue="1") long currentPage
			){
		PagingInfoVO<QnaReplyVO> pagingVO = new PagingInfoVO<>(5,5);
		pagingVO.setCurrentPage(currentPage);
		
		QnaReplyVO searchVO = new QnaReplyVO();
		searchVO.setQnaboard_no(qnaboard_no);
		pagingVO.setSearchVO(searchVO);
		long totalRecord = service.retrieveTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<QnaReplyVO> qnaReplyList = service.retrieveQnaReplyList(pagingVO);
		pagingVO.setDataList(qnaReplyList);
		
		return pagingVO;
	}
}
