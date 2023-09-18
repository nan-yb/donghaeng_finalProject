package kr.or.ddit.cor.manage.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.cor.manage.service.IManageService;
import kr.or.ddit.vo.PackageVO;
import kr.or.ddit.vo.Package_Reserve_ListVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ReviewVO;

//설문조사 참여 기능
//설문조사 참여를 위한 로직

@Controller
@RequestMapping("/cor")
public class ReservationMemberListController {
	@Inject
	IManageService service;
	@ResponseBody
	@RequestMapping(value="reservationMemberList.do", produces="application/json;charset=UTF-8")
	public PagingInfoVO<PackageVO> processAsync(
			@RequestParam(required=false) String searchType,	
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(name="company_id", required=true) String authorMember
	){
		PackageVO searchVO = new PackageVO();
		if(StringUtils.isNotBlank(searchWord)) {
			if(StringUtils.isBlank(searchType)) {
				searchVO.setPackage_name(searchWord);
			}else {
				switch (searchType) {
				case "package_name":
					searchVO.setPackage_name(searchWord);
					break;
				}
			}			
		}
		searchVO.setCompany_id(authorMember);
		PagingInfoVO<PackageVO> pagingVO = new PagingInfoVO<>(5,3);
		pagingVO.setCurrentPage(currentPage);
		/* 검색 정보 */
		pagingVO.setSearchVO(searchVO);
		long totalRecord = service.retrivePackageCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<PackageVO> packList = service.retrivePackageList(pagingVO);
		pagingVO.setDataList(packList);
		return pagingVO;
	}
	
	@RequestMapping("reservationMemberList.do")
	public String getProcess(
			@RequestParam(required=false) String searchType,	
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(name="company_id", required=true) String authorMember,
				Model model
			){
		PagingInfoVO<PackageVO> pagingVO =
				processAsync(searchType, searchWord, currentPage, authorMember);
		model.addAttribute("pagingVO", pagingVO);
		return "corporation/product/reservationProductList";
	}
}



