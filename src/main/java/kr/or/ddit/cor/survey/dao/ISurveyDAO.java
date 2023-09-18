package kr.or.ddit.cor.survey.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.SurveyVO;
import kr.or.ddit.vo.Survey_EvaluationVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;

/**
 * @author sem
 * @since 2018. 12. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 12. 4.      작성자명       게시글 관리를 위한 Persistence Layer
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface ISurveyDAO {
	/**
	 *   게시글 작성
	 * @param Survey
	 * @param session TODO
	 * @return row count
	 */
	public int insertSurvey(SurveyVO survey);
	
	
	public int insertSurvey_eval(Survey_EvaluationVO survey_eval);
	/**
	 * 검색과 페이징 처리를 위해 검색 조건에 맞는 게시글 수 조회
	 * @param pagingVO 검색 조건을 가진 VO
	 * @return 조건에 맞는 글이 없다면, 0 반환.
	 */
	public long selectTotalERecord(PagingInfoVO<Survey_EvaluationVO> pagingVO);
	public long selectTotalRecord(PagingInfoVO<SurveyVO> pagingVO);
	/**
	 * 검색 조건에 맞는 게시글 목록 조회
	 * @param pagingVO 검색 조건과 페이징 속성을 가진 VO
	 * @return 조건에 맞는 글이 없다면, .size()==0
	 */
	public List<SurveyVO> selectSurveyList(PagingInfoVO<SurveyVO> pagingVO);
	public List<Survey_EvaluationVO> selectSurveyEList(PagingInfoVO<Survey_EvaluationVO> pagingVO);
	/**
	 *  게시글 조회
	 * @param bo_no 글번호
	 * @return 없다면, null 반환
	 */
	public SurveyVO selectSurvey(@Param("survey_no") long survey_no);
	/**
	 * 글 수정
	 * @param Survey
	 * @param session TODO
	 * @return row count
	 */
	public int updateSurvey(SurveyVO survey);
	/**
	 *  글 삭제
	 * @param bo_no
	 * @param session TODO
	 * @return row count
	 */
	public int deleteSurvey(long bo_no);
	
}























