package kr.or.ddit.cor.survey.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.SurveyVO;
import kr.or.ddit.vo.Survey_EvaluationVO;

/**
 * @author sem
 * @since 2018. 12. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 12. 4.      작성자명       게시글 관리를 위한 Business Logic Layer
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
public interface ISurveyService {
	public ServiceResult createSurvey(SurveyVO survey);
	/**
	 * 검색 조건에 맞는 게시글 수
	 * @param pagingVO 검색 조건을 가진 VO
	 * @return 없다면, 0  반환
	 */
	public long retriveSurveyCount(PagingInfoVO<SurveyVO> pagingVO);
	public long retriveSurveyECount(PagingInfoVO<Survey_EvaluationVO> pagingVO);
	/**
	 * 검색 조건에 맞는 게시글 목록
	 * @param pagingVO 검색 조건과 페이징 속성을 가진 VO
	 * @return 없다면, .size()==0
	 */
	public List<SurveyVO> retriveSurveyList(PagingInfoVO<SurveyVO> pagingVO);
	public List<Survey_EvaluationVO> retriveSurveyEList(PagingInfoVO<Survey_EvaluationVO> pagingVO);
	/**
	 * 글 조회
	 * @param bo_no 글번호
	 * @return 없으면, SurveyException (unchecked exception) 발생
	 */
	public SurveyVO retriveSurvey(long survey_no);
	/**
	 * 글 수정
	 * @param Survey
	 * @return SurveyException, INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult modifySurvey(SurveyVO survey);
	/**
	 * 글 삭제
	 * @param Survey
	 * @return SurveyException, INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult removeSurvey(SurveyVO survey);
	
	
}


















