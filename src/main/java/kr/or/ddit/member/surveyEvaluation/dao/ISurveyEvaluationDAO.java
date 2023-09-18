package kr.or.ddit.member.surveyEvaluation.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.SurveyVO;
import kr.or.ddit.vo.Survey_EvaluationVO;

@Repository
public interface ISurveyEvaluationDAO {
	
	/**
	 * 설문조사 전체 조회
	 * @param pagingVO
	 * @return
	 */
	public List<SurveyVO> selectSurveyEvaluationList();
	
	/**
	 * 선택한 설문조사 상세 조회
	 * @param surveyVO
	 * @return
	 */
	public List<SurveyVO> selectSurveyEvaluation(SurveyVO surveyVO);
	
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
	public int insertSurveyEvaluation(SurveyVO surveyVO);
	
	/**
	 * 설문조사 번호로 해당 설문조사 검색
	 * @param survey_no
	 * @return
	 */
	public SurveyVO selectSurvey(long survey_no);
	
		
}
