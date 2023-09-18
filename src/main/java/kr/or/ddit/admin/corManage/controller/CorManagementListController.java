package kr.or.ddit.admin.corManage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.admin.corManage.service.ICorManagementService;
import kr.or.ddit.vo.CorporationVO;
import kr.or.ddit.vo.PagingInfoVO;

//모든 기업을 조회
//관리자가 모든 기업을 조회하는 프로그램

/**
 * @author 서신원
 * @since 2019. 1. 18.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 18.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class CorManagementListController {
	
	@Inject
	ICorManagementService service;
	
	@ResponseBody
	@RequestMapping(value="/admin/corManagementList.do", produces="application/json;charset=UTF-8")
	public PagingInfoVO<CorporationVO> asyncProcess(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord
			){
		PagingInfoVO<CorporationVO> pagingVO = new PagingInfoVO<>(3,5);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		
		long totalRecord  =  service.retrieveTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<CorporationVO> corManagementList = service.retrieveCorManagementList(pagingVO);
		pagingVO.setDataList(corManagementList);
		
		return pagingVO;
	}
	
	@RequestMapping("/admin/corManagementList.do")
	public String syncProcess(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			Model model
			){
		PagingInfoVO<CorporationVO> pagingVO = asyncProcess(currentPage, searchType, searchWord);
		model.addAttribute("pagingVO", pagingVO);
		return "admin/corManage/corManagementList";
	}
	
	@RequestMapping("/admin/corManagementView.do")
	public String viewProcess(
			@RequestParam(name="what", required=true) String company_id,
			Model model
			){
		CorporationVO cor = service.retrieveCorManagement(company_id);
		model.addAttribute("cor", cor);
		return "admin/corManage/corManagementView";
	}
}
