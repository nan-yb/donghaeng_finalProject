package kr.or.ddit.common.board.postboard.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.common.board.postboard.service.IReplyService;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.Review_ReplyVO;

@RestController
public class ReplyListController{
	private IReplyService replyService;
	@Inject
	public void setReplyService(IReplyService replyService) {
		this.replyService = replyService;
	}
	
	@RequestMapping(value="/postboard/replyList.do", produces="application/json;charset=UTF-8")
	public PagingInfoVO<Review_ReplyVO> process(
			@RequestParam(required=true) long review_no,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			){
		PagingInfoVO<Review_ReplyVO> pagingVO = new PagingInfoVO<>(3,3);
		pagingVO.setCurrentPage(currentPage);
		
		Review_ReplyVO searchVO = new Review_ReplyVO();
		searchVO.setReview_no(review_no);
		pagingVO.setSearchVO(searchVO);
		long totalRecord = replyService.retriveReplyCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<Review_ReplyVO> replyList = replyService.retriveReplyList(pagingVO);
		pagingVO.setDataList(replyList);
				
		return pagingVO;
	}
	
	@RequestMapping(value = "/postboard/replyList.do")
	public String setProcess(
			@RequestParam(required=true) long review_no,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			Model model
	){
		PagingInfoVO<Review_ReplyVO> pagingVO = process(review_no, currentPage);
		model.addAttribute("pagingVO", pagingVO);
		
		return "common/board/postboard/postboardView";
	}

}













