package kr.or.ddit.admin.memberManage.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.admin.memberManage.service.IMemberManagementService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

//블랙리스트 수정
//관리자가 블랙리스트 회원을 수정하는 프로그램

@Controller
public class BlackListRetrieveController {
	
	@Inject
	IMemberManagementService service;
	

	@ResponseBody
	@RequestMapping(value="/admin/blackListRetrieve.do", produces="application/json;charset=UTF-8")
	public PagingInfoVO<MemberVO> asyncProcess(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord
			){
		
		MemberVO searchVO = new MemberVO();
		if(StringUtils.isNotBlank(searchWord)) {
			if(StringUtils.isBlank(searchType)) {
				searchVO.setMem_id(searchWord);
			}else {
				switch (searchType) {
				case "writer":
					searchVO.setMem_id(searchWord);
					break;
				}
			}
		}
		
		PagingInfoVO<MemberVO> pagingVO = new PagingInfoVO<>(5,5);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		long totalRecord = service.retrieveTotalBlackListRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<MemberVO> blackList = service.retrieveBlackList(pagingVO);
		pagingVO.setDataList(blackList);
		
		return pagingVO;
	}
	
	@RequestMapping("/admin/blackListRetrieve.do")
	public String syncProcess(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			Model model 
	){
		PagingInfoVO<MemberVO> pagingVO = asyncProcess(currentPage, searchType, searchWord);
		model.addAttribute("pagingVO", pagingVO);
		return "admin/memManage/blackList";
	}
	
	@RequestMapping("/admin/blackListView.do")
	public String process(
			@RequestParam(name = "what", required = true) String mem_id,
			Model model
	){
		MemberVO member = service.retrieveMemberManagement(mem_id);
		model.addAttribute("member", member);
		return "admin/memManage/blackListView";
	}
	
}
