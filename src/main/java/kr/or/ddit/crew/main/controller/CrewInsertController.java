package kr.or.ddit.crew.main.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.crew.main.service.IMyCrewService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.CrewVO;

//크루 생성
//일반회원이크루 생성을 하기 위한 로직

@Controller
public class CrewInsertController {
	
	@Inject
	IMyCrewService service;
	
	@RequestMapping(value = "/crew/crewInsert.do" , method=RequestMethod.GET )
	public String getProcess(){
		return "crew/crewForm";
	}
	
	@RequestMapping(value = "/crew/crewInsert.do" , method=RequestMethod.POST) 
	public String doPost(
			@Validated(InsertGroup.class) @ModelAttribute("crew") CrewVO crew,
			Errors errors,
			Model model) throws IOException{
		
		String goPage = null;
		String message = null;
		
		boolean valid = !errors.hasErrors();
		if (valid) {
			ServiceResult result = null;
			result = service.createCrew(crew);
			
			switch (result) {
			case PKDUPLICATED:
				goPage = "crew/crewForm";
				message = "아이디 중복, 바꾸셈.";
				break;
			case FAILED:
				goPage = "crew/crewForm";
				message = "서버 오류로 인한 실패, 잠시 뒤 다시 하셈.";
				break;
			case OK:
				goPage = "redirect:/crew/crewMain.do";
				break;
			}
			
			model.addAttribute("message", message);
		} else {
			goPage = "member/memberForm";
		}
		
		return goPage;
	}
}
