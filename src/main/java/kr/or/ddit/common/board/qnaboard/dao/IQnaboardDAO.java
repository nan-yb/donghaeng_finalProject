package kr.or.ddit.common.board.qnaboard.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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
@Repository
public interface IQnaboardDAO {
	
	/**
	 * 게시글을 작성
	 * @param qnaboard 등록할 게시글의 정보를 가진 VO
	 * @return 성공(>0) 실패
	 */
	public int insertQnaboard(QnaboardVO qnaboard);
	
	/**
	 * 검색과 페이징을 위한 전체 게시글 수 조회
	 * @param pagingVO 검색조건을 가진 VO
	 * @return 조건에 맞는 글이 없다면 0 반환
	 */
	public long selectTotalRecord(PagingInfoVO<QnaboardVO> pagingVO);
	
	/**
	 * 검색조건에 맞는 게시글 목록 조회
	 * @param pagingVO 검색 조건과 페이징 속성을 가진 VO
	 * @return 조건에 맞는 글이 없다면 size()==0
	 */
	public List<QnaboardVO> selectQnaboardList(PagingInfoVO<QnaboardVO> pagingVO);
	
	/**
	 * 게시글 상세조회
	 * @param qnaboard_no 글번호
	 * @return 없다면 null 반환
	 */
	public QnaboardVO selectQnaboard(long qnaboard_no);
	
	/**
	 * 게시글 수정
	 * @param qnaboard 등록할 게시글의 정보를 가진 VO
	 * @return 성공(>0) 실패
	 */
	public int updateQnaboard(QnaboardVO qnaboard);
	
	/**
	 * 게시글 삭제
	 * @param qnaboard_no 글번호
	 * @return 성공(>0) 실패
	 */
	public int deleteQnaboard(long qnaboard_no);
	
}
