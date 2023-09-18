package kr.or.ddit.member.surveyEvaluation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.SurveyVO;
import kr.or.ddit.vo.Survey_EvaluationVO;

@Service
public interface ISurveyEvaluationService {
	/**
	 * 설문조사 전체 조회
	 * @param pagingVO
	 * @return
	 */
	public List<SurveyVO> retrieveSurveyEvaluationList();
	
	/**
	 * 선택한 설문조사 상세 조회
	 * @param surveyVO
	 * @return
	 */
	public List<SurveyVO> retrieveSurveyEvaluation(SurveyVO surveyVO);
	
	/**
	 * 선택한 설문조사의 설문 문제들 조회
	 * @param survey_no
	 * @return
	 */
	public List<Survey_EvaluationVO> surveyEvaluationList(long survey_no);
	
	/**
	 * 설문조사 참가
	 * @param surveyVO
	 * @return
	 */
	public ServiceResult createSurveyEvaluation(SurveyVO surveyVO);
	
	public SurveyVO retrieveSurvey(long survey_no);
	
	
}
