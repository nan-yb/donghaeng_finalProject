package kr.or.ddit.admin.memberManage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.admin.memberManage.service.IMemberManagementService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

//모든 회원 정보 조회
//관리자가 모든 회원 정보 리스트를 볼 수 있는 프로그램

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
public class MemberManagementListController {
	
	@Inject
	IMemberManagementService service;
	
	@ResponseBody
	@RequestMapping(value="/admin/memberManagementList.do", produces="application/json;charset=UTF-8")
	public PagingInfoVO<MemberVO> asyncProcess(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord
			){
		PagingInfoVO<MemberVO> pagingVO = new PagingInfoVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		
		long totalRecord = service.retrieveTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<MemberVO> memberManagementList = service.retrieveMemberManagementList(pagingVO);
		pagingVO.setDataList(memberManagementList);
		
		return pagingVO;
	}
	
	@RequestMapping("/admin/memberManagementList.do")
	public String syncProcess(@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			Model model 
			){
		PagingInfoVO<MemberVO> pagingVO = asyncProcess(currentPage, searchType, searchWord);
		model.addAttribute("pagingVO", pagingVO);
		return "admin/memManage/memberManagementList";
	}
}

