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
import kr.or.ddit.vo.Package_Reserve_ListVO;
import kr.or.ddit.vo.PagingInfoVO;

//설문조사 참여 기능
//설문조사 참여를 위한 로직

@Controller
@RequestMapping("/cor")
public class ReservationMemberViewController {
	@Inject
	IManageService service;
	@ResponseBody
	@RequestMapping(value="reservationMemberView.do", produces="application/json;charset=UTF-8")
	public PagingInfoVO<Package_Reserve_ListVO> processAsync(
			@RequestParam(required=false) String searchType,	
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(name="what", required=true) long pack_no
	){
		Package_Reserve_ListVO searchVO = new Package_Reserve_ListVO();
		if(StringUtils.isNotBlank(searchWord)) {
			if(StringUtils.isBlank(searchType)) {
				searchVO.setPackage_reservation_list_name(searchWord);
			}else {
				switch (searchType) {
				case "list_name":
					searchVO.setPackage_reservation_list_name(searchWord);
					break;
				}
			}			
		}
		searchVO.setPackage_reservation_no(pack_no);
		PagingInfoVO<Package_Reserve_ListVO> pagingVO = new PagingInfoVO<>(5,3);
		pagingVO.setCurrentPage(currentPage);
		/* 검색 정보 */
		pagingVO.setSearchVO(searchVO);
		long totalRecord = service.retrivePackageReserveCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<Package_Reserve_ListVO> packList = service.retrivePackageReserveList(pagingVO);
		pagingVO.setDataList(packList);
		return pagingVO;
	}
	
	@RequestMapping("reservationMemberView.do")
	public String getProcess(
			@RequestParam(required=false) String searchType,	
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(name="what", required=true) long pack_no,
				Model model
			){
		PagingInfoVO<Package_Reserve_ListVO> pagingVO =
				processAsync(searchType, searchWord, currentPage, pack_no);
		model.addAttribute("pagingVO", pagingVO);
		return "corporation/product/reservationProductView";
	}
}



