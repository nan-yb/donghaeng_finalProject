package kr.or.ddit.cor.survey.controller;

import java.util.Arrays;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.cor.survey.service.ISurveyService;
import kr.or.ddit.vo.SurveyVO;
import kr.or.ddit.vo.Survey_EvaluationVO;

@Controller
@RequestMapping("/survey/surveyInsert.do")
public class SurveyInsertController{
	@Inject
	ISurveyService service;

	@RequestMapping(method=RequestMethod.GET)
	public String getProcess() {
		return "corporation/survey/surveyForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postProcess(
			SurveyVO survey,
			String[] evaluation_content,
			Errors errors,
			Model model) {
		boolean valid = !errors.hasErrors();
		String view = null;
		
		
		service.createSurvey(survey);
//		if(valid) {
//			ServiceResult result = service.createSurvey(survey);
//				if(ServiceResult.OK.equals(result)) {
//						view = "redirect:/survey/surveyList.do";
//				}else {
//					model.addAttribute("message", "설문제목 오류");
//					view = "survey/surveyForm2";
//				}
//		}else {
//			view = "survey/surveyForm2";
//		}
		return view;
	}

}


















