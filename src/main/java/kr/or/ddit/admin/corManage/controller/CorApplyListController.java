package kr.or.ddit.admin.corManage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.admin.corManage.service.ICorApplyService;
import kr.or.ddit.vo.CorporationVO;
import kr.or.ddit.vo.PagingInfoVO;

//신청 리스트 조회 기능
//관리자가 업체 등록을 원하는 기업의 신청을 조회하는 프로그램

/**
 * @author 서신원
 * @since 2019. 1. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 19.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class CorApplyListController {
	
	@Inject
	ICorApplyService service;
	
	@ResponseBody
	@RequestMapping(value="/admin/corApplyList.do", produces="application/json;charset=UTF-8")
	public PagingInfoVO<CorporationVO> asyncProcess(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord
			){
		PagingInfoVO<CorporationVO> pagingVO = new PagingInfoVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		
		long totalRecord = service.retrieveTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<CorporationVO> corApplyList =  service.retrieveCorApplyList(pagingVO);
		pagingVO.setDataList(corApplyList);
		
		return pagingVO;
	}
	
	
	@RequestMapping("/admin/corApplyList.do")
	public String syncProcess(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			Model model
			){
		PagingInfoVO<CorporationVO> pagingVO = asyncProcess(currentPage, searchType, searchWord);
		model.addAttribute("pagingVO", pagingVO);
		return "admin/corManage/corApplyList";
	}
}
