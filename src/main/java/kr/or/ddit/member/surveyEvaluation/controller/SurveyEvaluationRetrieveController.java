package kr.or.ddit.member.surveyEvaluation.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.surveyEvaluation.service.ISurveyEvaluationService;
import kr.or.ddit.vo.SurveyVO;

@Controller
@RequestMapping("/member")
public class SurveyEvaluationRetrieveController {
	
	@Inject
	ISurveyEvaluationService service;
	
	@RequestMapping(value = "surveyEvaluationList.do")
	public String process(
			@RequestParam(required = false) String searchType,
			@RequestParam(required = false) String searchWord,
			Model model
	){
		List<SurveyVO> survey = service.retrieveSurveyEvaluationList();
		
		model.addAttribute("survey", survey);
		
		return "member/surveyEvaluation/surveyList";
	}
	
	@RequestMapping(value ="surveyEvaluationView.do")
	public String process(
			@RequestParam(name = "what", required = true) long survey_no,
			Model model
	){
		
		SurveyVO surveyVO = new SurveyVO();
		
		surveyVO = service.retrieveSurvey(survey_no);
		
		List<SurveyVO> servey =  service.retrieveSurveyEvaluation(surveyVO);
		
		model.addAttribute("survey", servey);
		
		return "member/surveyEvaluation/surveyView";
	}
}
