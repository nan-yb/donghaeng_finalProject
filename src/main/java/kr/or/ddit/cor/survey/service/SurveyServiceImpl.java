package kr.or.ddit.cor.survey.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.cor.survey.dao.ISurveyDAO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.SurveyVO;
import kr.or.ddit.vo.Survey_EvaluationVO;

@Service
public class SurveyServiceImpl implements ISurveyService {
	
	@Inject
	ISurveyDAO surveyDAO;

	@Override
	@Transactional
	public ServiceResult createSurvey(SurveyVO survey) {
		int rowCnt = surveyDAO.insertSurvey(survey);
		
		if(survey.getEvaluation_content().length>0 && survey.getEvaluation_content()!= null ){
			int cnt = 0;
			for(String temp : survey.getEvaluation_content()){
				Survey_EvaluationVO vo = new Survey_EvaluationVO();
				vo.setSurvey_no(survey.getSurvey_no());
				vo.setEvaluation_content(temp);
				cnt += surveyDAO.insertSurvey_eval(vo);
			}
		}
		
		int check = 1;
		ServiceResult result = ServiceResult.FAILED;
		if(rowCnt>=check) {
			result = ServiceResult.OK;
		}
		return result;
	}
	
	@Override
	public long retriveSurveyCount(PagingInfoVO<SurveyVO> pagingVO) {
		return surveyDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<SurveyVO> retriveSurveyList(PagingInfoVO<SurveyVO> pagingVO) {
		return surveyDAO.selectSurveyList(pagingVO);
	}
	@Override
	public long retriveSurveyECount(PagingInfoVO<Survey_EvaluationVO> pagingVO) {
		return surveyDAO.selectTotalERecord(pagingVO);
	}
	@Override
	public List<Survey_EvaluationVO> retriveSurveyEList(PagingInfoVO<Survey_EvaluationVO> pagingVO) {
		return surveyDAO.selectSurveyEList(pagingVO);
	}

	@Override
	public SurveyVO retriveSurvey(long survey_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult modifySurvey(SurveyVO survey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeSurvey(SurveyVO survey) {
		// TODO Auto-generated method stub
		return null;
	}
	

	

	
}










