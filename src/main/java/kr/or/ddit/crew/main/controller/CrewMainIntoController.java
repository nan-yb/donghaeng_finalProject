package kr.or.ddit.crew.main.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.crew.main.service.IMyCrewService;
import kr.or.ddit.vo.CrewVO;
import kr.or.ddit.vo.PagingInfoVO;

@Controller
public class CrewMainIntoController {
	
	@Inject
	IMyCrewService service;
	
	@RequestMapping("/crew/crewMain.do")
	public String getProcess(){
		return "crew/crewMain";
	}
	
	@ResponseBody
	@RequestMapping(value = "/crew/crewMain.do" , method=RequestMethod.GET ,produces="application/json;charset=utf8")
	public List<CrewVO> getCrewView(@RequestParam(required = true) String mem_id , Model model) {
		List<CrewVO> crewList = service.retrieveCrewList(mem_id);
		return crewList;
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/crew/crewMainList.do" , method=RequestMethod.GET ,produces="application/json;charset=utf8")
//	public PagingInfoVO<CrewVO> getCrewList(
//			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
//			) {
//		PagingInfoVO<CrewVO> pagingVO = new PagingInfoVO<>();
//		pagingVO.setCurrentPage(currentPage);
//		
//		long totalRecord = service.retrieveCrewCount(pagingVO);
//		pagingVO.setTotalRecord(totalRecord);
//		
//		List<CrewVO> crewList = service.retrieveAllCrewList(pagingVO);
//		pagingVO.setDataList(crewList);
//		return pagingVO;
//	}
}
