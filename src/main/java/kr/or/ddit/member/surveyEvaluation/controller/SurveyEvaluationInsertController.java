package kr.or.ddit.member.surveyEvaluation.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.surveyEvaluation.service.ISurveyEvaluationService;
import kr.or.ddit.vo.Survey_EvaluationVO;

@Controller
public class SurveyEvaluationInsertController{
	
	@Inject
	ISurveyEvaluationService service;
	
	@RequestMapping("/member/surveyEvaluationForm.do")
	public String process(
			@RequestParam(name = "survey_no", required = true) long survey_no,
			Model model
		){
		
		List<Survey_EvaluationVO> survey = service.surveyEvaluationList(survey_no);
		model.addAttribute("survey", survey);
		
		return "member/surveyEvaluation/surveyForm";
	}
}
