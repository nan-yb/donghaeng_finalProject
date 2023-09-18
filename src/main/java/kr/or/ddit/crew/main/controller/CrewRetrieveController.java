package kr.or.ddit.crew.main.controller;

import java.util.List;

import javax.inject.Inject;
import javax.xml.ws.soap.AddressingFeature.Responses;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.crew.main.service.IMyCrewService;
import kr.or.ddit.vo.CrewVO;

// 전체 크루 조회
//전체 크루를 조회 하기 위한 로직

@Controller
public class CrewRetrieveController {
	
	@Inject
	IMyCrewService service;
	
	@RequestMapping("/crew/crewList.do")
	public String getProcess(){
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/crew/crewView.do" , method=RequestMethod.GET ,produces="application/json;charset=UTF-8")
	public List<CrewVO> getCrewView(@RequestParam(required = true) String mem_id , Model model) {
		List<CrewVO> crewList = service.retrieveCrewList(mem_id);
		
		CrewVO crew =  crewList.get(0);		//임시 바꿀에정
		model.addAttribute("crew", crew);
		return crewList;
	}
}
