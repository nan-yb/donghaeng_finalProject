package kr.or.ddit.common.board.noticeboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.NoticeboardVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;
import kr.or.ddit.vo.ReviewVO;

@Repository
public interface INoticeboardDAO {
	public PersonVO selectPerson(String mem_id);
	/**
	 *   게시글 작성
	 * @param board
	 * @param session TODO
	 * @return row count
	 */
	public int insertBoard(NoticeboardVO nbVO);
	/**
	 * 검색과 페이징 처리를 위해 검색 조건에 맞는 게시글 수 조회
	 * @param pagingVO 검색 조건을 가진 VO
	 * @return 조건에 맞는 글이 없다면, 0 반환.
	 */
	public long selectTotalRecord(PagingInfoVO<NoticeboardVO> pagingVO);
	/**
	 * 검색 조건에 맞는 게시글 목록 조회
	 * @param pagingVO 검색 조건과 페이징 속성을 가진 VO
	 * @return 조건에 맞는 글이 없다면, .size()==0
	 */
	public List<NoticeboardVO> selectBoardList(PagingInfoVO<NoticeboardVO> pagingVO);
	/**
	 *  게시글 조회
	 * @param notice_no 글번호
	 * @return 없다면, null 반환
	 */
	public NoticeboardVO selectBoard(@Param("notice_no") long notice_no);
	
	/**
	 * 글 조회수 증가
	 * @param notice_no
	 */
	public void incrementHit(long notice_no);
	
	/**
	 *  글 추천수 증가
	 * @param notice_no
	 */
	public void incrementRcmd(long notice_no);
	/**
	 * 글 수정
	 * @param board
	 * @param session TODO
	 * @return row count
	 */
	public int updateBoard(NoticeboardVO nbVO);
	/**
	 *  글 삭제
	 * @param notice_no
	 * @param session TODO
	 * @return row count
	 */
	public int deleteBoard(long notice_no);
}
