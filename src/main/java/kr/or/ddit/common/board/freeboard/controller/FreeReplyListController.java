package kr.or.ddit.common.board.freeboard.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.common.board.freeboard.service.IFreeReplyService;
import kr.or.ddit.vo.Free_ReplyVO;
import kr.or.ddit.vo.PagingInfoVO;

@RestController
public class FreeReplyListController {
	private IFreeReplyService replyService;
	
	@Inject
	public void setReplyService(IFreeReplyService replyService){
		this.replyService = replyService;
	}
	
	@RequestMapping(value="/freeboard/replyList.do", produces="application/json;charset=UTF-8")
	public PagingInfoVO<Free_ReplyVO> process(
			@RequestParam(required=true) long board_no,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
	){
		PagingInfoVO<Free_ReplyVO> pagingVO = new PagingInfoVO<>(3,3);
		pagingVO.setCurrentPage(currentPage);
		
		Free_ReplyVO searchVO = new Free_ReplyVO();
		searchVO.setBoard_no(board_no);
		pagingVO.setSearchVO(searchVO);
		long totalRecord = replyService.retriveReplyCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<Free_ReplyVO> replyList = replyService.retriveReplyList(pagingVO);
		pagingVO.setDataList(replyList);
		
		return pagingVO;
	}
	
	@RequestMapping(value = "/freeboard/replyList.do")
	public String setProcess(
			@RequestParam(required=true) long board_no,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			Model model
	){
		PagingInfoVO<Free_ReplyVO> pagingVO = 
				process(board_no, currentPage);
		model.addAttribute("pagingVO", pagingVO);
		return "common/board/freeboard/freeboardView";
	}
}
