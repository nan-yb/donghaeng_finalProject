package kr.or.ddit.common.board.postboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.ReviewVO;
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
public interface IBoardDAO {
	public PersonVO selectPerson(String mem_id);
	/**
	 *   게시글 작성
	 * @param board
	 * @param session TODO
	 * @return row count
	 */
	public int insertBoard(ReviewVO review);
	/**
	 * 검색과 페이징 처리를 위해 검색 조건에 맞는 게시글 수 조회
	 * @param pagingVO 검색 조건을 가진 VO
	 * @return 조건에 맞는 글이 없다면, 0 반환.
	 */
	public long selectTotalRecord(PagingInfoVO<ReviewVO> pagingVO);
	/**
	 * 검색 조건에 맞는 게시글 목록 조회
	 * @param pagingVO 검색 조건과 페이징 속성을 가진 VO
	 * @return 조건에 맞는 글이 없다면, .size()==0
	 */
	public List<ReviewVO> selectBoardList(PagingInfoVO<ReviewVO> pagingVO);
	/**
	 *  게시글 조회
	 * @param bo_no 글번호
	 * @return 없다면, null 반환
	 */
	public ReviewVO selectBoard(@Param("review_no") long review_no);
	
	/**
	 * 글 조회수 증가
	 * @param bo_no
	 */
	public void incrementHit(long bo_no);
	
	/**
	 *  글 추천수 증가
	 * @param bo_no
	 */
	public void incrementRcmd(long bo_no);
	/**
	 * 글 수정
	 * @param board
	 * @param session TODO
	 * @return row count
	 */
	public int updateBoard(ReviewVO review);
	/**
	 *  글 삭제
	 * @param bo_no
	 * @param session TODO
	 * @return row count
	 */
	public int deleteBoard(long bo_no);
	
	// 추천했는지 안했는지 확인
	public int selectPostRcmd( Map<String, Object> map);

	// 추천테이블에 입력
	public int insertPostRcmd( Map<String, Object> map);

	public int deletePostRcmd(long board_no);
	
	//신고했는지 안했는지 확인
	public int selectPostReport(Map<String, Object> map);
		
	//신고테이블에 입력
	public int insertPostReport(Map<String, Object> map);
}























