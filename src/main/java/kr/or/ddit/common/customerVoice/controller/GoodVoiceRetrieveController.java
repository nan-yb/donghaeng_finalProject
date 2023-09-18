package kr.or.ddit.common.customerVoice.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.customerVoice.service.ICustomBoardService;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.CustomerVoiceVO;

//칭찬 소리글 조회 기능
//칭찬게시판의 칭찬글을 조회하기 위한 로직
@Controller
@RequestMapping("/customer")
public class GoodVoiceRetrieveController {
	@Inject
	ICustomBoardService service;
	@ResponseBody
	@RequestMapping(value="goodVoiceRetrieve.do", produces="application/json;charset=UTF-8")
	public PagingInfoVO<CustomerVoiceVO> processAsync(
			@RequestParam(required=false) String searchType,	
			@RequestParam(required=false) String searchWord,
			@RequestParam(required=false ,defaultValue="G") String Type,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
	){
		CustomerVoiceVO searchVO = new CustomerVoiceVO();
		searchVO.setType(Type);
		if(StringUtils.isNotBlank(searchWord)) {
			if(StringUtils.isBlank(searchType)) {
				searchVO.setCustomvoice_title(searchWord);
				searchVO.setCustomvoice_no(searchWord);
				searchVO.setCompany_id(searchWord);
			}else {
				switch (searchType) {
				case "company_id":
					searchVO.setCompany_id(searchWord);
					break;
				case "title":
					searchVO.setCustomvoice_title(searchWord);
					break;
				}
			}			
		}
		
		PagingInfoVO<CustomerVoiceVO> pagingVO = new PagingInfoVO<>(5,3);
		pagingVO.setCurrentPage(currentPage);
		/* 검색 정보 */
		pagingVO.setSearchVO(searchVO);
		long totalRecord = service.retriveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<CustomerVoiceVO> reviewList = service.retriveBoardList(pagingVO);
		pagingVO.setDataList(reviewList);
		return pagingVO;
	}
	
	@RequestMapping(value="goodVoiceRetrieve.do")
	public String process(
			@RequestParam(required=false) String searchType,	
			@RequestParam(required=false) String searchWord,
			@RequestParam(required=false ,defaultValue="G") String Type,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			Model model
		) {
		PagingInfoVO<CustomerVoiceVO> pagingVO =
				processAsync(searchType, searchWord, Type,currentPage);
		model.addAttribute("pagingVO", pagingVO);
		return "member/customerVoice/voiceList";
	}
	
	
	@RequestMapping("goodVoiceView.do")
	public String process(
			@RequestParam(name="what", required=true) String customvoice_no,
			Model model ){
		
		CustomerVoiceVO customvoice = service.retriveBoard(customvoice_no);
		
		model.addAttribute("customvoice", customvoice);
		
		return "member/customerVoice/voiceView";
	}
	
}
