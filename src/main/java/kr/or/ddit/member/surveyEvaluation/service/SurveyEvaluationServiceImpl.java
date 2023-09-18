package kr.or.ddit.member.surveyEvaluation.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.surveyEvaluation.dao.ISurveyEvaluationDAO;
import kr.or.ddit.vo.SurveyVO;
import kr.or.ddit.vo.Survey_EvaluationVO;

@Service
public class SurveyEvaluationServiceImpl implements ISurveyEvaluationService{

	@Inject
	ISurveyEvaluationDAO dao;
	
	@Override
	public List<SurveyVO> retrieveSurveyEvaluationList() {
		return dao.selectSurveyEvaluationList();
	}

	@Override
	public List<SurveyVO> retrieveSurveyEvaluation(SurveyVO surveyVO) {
		return dao.selectSurveyEvaluation(surveyVO);
	}

	@Override
	public List<Survey_EvaluationVO> surveyEvaluationList(long survey_no) {
		return dao.surveyEvaluationList(survey_no);
	}

	@Override
	public ServiceResult createSurveyEvaluation(SurveyVO surveyVO) {
		ServiceResult result = ServiceResult.FAILED;
		int cnt  = dao.insertSurveyEvaluation(surveyVO);
		if(cnt > 0){
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public SurveyVO retrieveSurvey(long survey_no) {
		return dao.selectSurvey(survey_no);
	}

}
