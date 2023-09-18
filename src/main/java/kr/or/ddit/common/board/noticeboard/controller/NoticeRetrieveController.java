package kr.or.ddit.common.board.noticeboard.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.board.noticeboard.servaice.INoticeboardService;
import kr.or.ddit.vo.NoticeboardVO;
import kr.or.ddit.vo.PagingInfoVO;

//공지게시판 조회 기능
//공지사항을 조회하기 위한 프로그램

@Controller
@RequestMapping("/noticeboard")
public class NoticeRetrieveController {
	
	@Inject
	INoticeboardService service;
	
	@ResponseBody
	@RequestMapping(value = "noticeboardRetrieve.do", produces = "application/json;charset=UTF-8")
	public PagingInfoVO<NoticeboardVO> processAsync(
			@RequestParam(required = false) String searchType,
			@RequestParam(required = false) String searchWord,
			@RequestParam(name = "page", required = false, defaultValue="1") int currentPage
	){
		NoticeboardVO searchVO = new NoticeboardVO();
		if(StringUtils.isNotBlank(searchWord)){
			if(StringUtils.isBlank(searchType)) {
				searchVO.setNotice_title(searchWord);
				searchVO.setNotice_content(searchWord);
			}else {
				switch (searchType) {
				case "title":
					searchVO.setNotice_title(searchWord);
					break;
				case "content":
					searchVO.setNotice_content(searchWord);	
					break;
				}
			}
		}
		PagingInfoVO<NoticeboardVO> pagingVO = new PagingInfoVO<>(5,2);
		pagingVO.setCurrentPage(currentPage);
		/* 검색 정보 */
		pagingVO.setSearchVO(searchVO);
		long totalRecord = service.retriveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<NoticeboardVO> noticeList = service.retriveBoardList(pagingVO);
		pagingVO.setDataList(noticeList);
		return pagingVO;
	}
	
	@RequestMapping(value = "noticeboardRetrieve.do")
	public String process(
			@RequestParam(required=false) String searchType,	
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			Model model
	){
		PagingInfoVO<NoticeboardVO> pagingVO = 
				processAsync(searchType, searchWord, currentPage);
		model.addAttribute("pagingVO", pagingVO);
		return "common/board/noticeboard/noticeboardList";
	}
	
	@RequestMapping("noticeboardView.do")
	public String process(
			@RequestParam(name = "what", required = true) long notice_no,
			Model model
	){
		NoticeboardVO notice = service.retriveBoard(notice_no);
		
		model.addAttribute("notice", notice);
		
		return "common/board/noticeboard/noticeboardView";
	}
}
