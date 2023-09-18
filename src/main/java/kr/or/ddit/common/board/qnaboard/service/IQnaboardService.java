package kr.or.ddit.common.board.qnaboard.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.QnaboardVO;


/**
 * @author 서신원
 * @since 2019. 1. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 11.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public interface IQnaboardService {
	
	
	/**
	 * QNA글을 작성
	 * @param qnaboard 등록할 게시글의 정보를 가진 VO
	 * @return OK, FAILED
	 */
	public ServiceResult createQnaboard(QnaboardVO qnaboard);
	
	/**
	 * 검색과 페이징을 위한 전체 게시글 수 조회
	 * @param pagingVO 검색조건을 가진 VO
	 * @return 조건에 맞는 글이 없다면 0 반환
	 */
	public long retrieveTotalRecord(PagingInfoVO<QnaboardVO> pagingVO);
	
	/**
	 * 검색조건에 맞는 게시글 목록 조회
	 * @param pagingVO 검색 조건과 페이징 속성을 가진 VO
	 * @return 조건에 맞는 글이 없다면 size()==0
	 */
	public List<QnaboardVO> retrieveQnaboardList(PagingInfoVO<QnaboardVO> pagingVO);
	
	/**
	 * 게시글 상세조회
	 * @param qnaboard_no 글번호
	 * @return 없다면 null 반환
	 */
	public QnaboardVO retrieveQnaboard(long qnaboard_no);
	
	/**
	 * 게시글 수정
	 * @param qnaboard 등록할 게시글의 정보를 가진 VO
	 * @return OK FAILED INVALIDPASSWORD
	 */
	public ServiceResult modifyQnaboard(QnaboardVO qnaboard);
	
	
	/**
	 * 게시글 삭제
	 * @param qnaboard_no 글번호
	 * @return OK FAILED INVALIDPASSWORD
	 */
	public ServiceResult removeQnaboard(long qnaboard_no);
}
