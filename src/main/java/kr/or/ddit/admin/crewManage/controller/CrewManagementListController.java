package kr.or.ddit.admin.crewManage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.admin.crewManage.service.ICrewManagementService;
import kr.or.ddit.vo.CrewVO;
import kr.or.ddit.vo.PagingInfoVO;

//모든 크루 조회
//관리자가 모든 크루를 조회하는 프로그램

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
public class CrewManagementListController {
	
	@Inject
	ICrewManagementService service;
	
	@ResponseBody
	@RequestMapping(value="/admin/crewManagementList.do", produces="application/json;charset=UTF-8")
	public PagingInfoVO<CrewVO> asyncProcess(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord
			){
		PagingInfoVO<CrewVO> pagingVO = new PagingInfoVO<>(5,5);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		
		long totalRecord = service.retrieveTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<CrewVO> crewList = service.retrieveCrewManagementList(pagingVO);
		pagingVO.setDataList(crewList);
		return pagingVO;
	}
	
	@RequestMapping("/admin/crewManagementList.do")
	public String syncProcess(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			Model model
			){
		PagingInfoVO<CrewVO> pagingVO = asyncProcess(currentPage, searchType, searchWord);
		model.addAttribute("pagingVO", pagingVO);
		return "admin/crewManage/crewManagementList";
	}
	
	@RequestMapping("/admin/crewManagementView.do")
	public String viewProcess(
			@RequestParam(name="what", required=true) long crew_no,
			Model model
			){
		CrewVO crew = service.retrieveCrewManagement(crew_no);
		model.addAttribute("crew", crew);
		return "admin/crewManage/crewManagementView";
	}
	
}
