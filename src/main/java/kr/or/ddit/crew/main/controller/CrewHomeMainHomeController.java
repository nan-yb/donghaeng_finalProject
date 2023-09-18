package kr.or.ddit.crew.main.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.crew.main.service.IMyCrewService;
import kr.or.ddit.vo.CrewVO;

@Controller
public class CrewHomeMainHomeController {
	
	@Inject
	IMyCrewService service;
	
	@RequestMapping("/crew/crewHome.do")
	public String getProcess(@RequestParam(required = true)String what , Model model) {
		
		CrewVO crew = service.retrieveCrew(Long.parseLong(what));
		model.addAttribute("crew", crew);
		
		return "crew/MyCrewHome/crewHome";
	}
}
