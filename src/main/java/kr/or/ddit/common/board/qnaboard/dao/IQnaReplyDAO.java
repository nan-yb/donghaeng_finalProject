package kr.or.ddit.common.board.qnaboard.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.QnaReplyVO;

/**
 * @author 서신원
 * @since 2019. 1. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 16.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IQnaReplyDAO {
	/**
	 * QNA게시글의 전체 댓글수
	 * @param pagingVO 검색 조건과 페이징 속성을 가진 VO
	 * @return 조건에 맞는 글이 없다면 size()==0
	 */
	public List<QnaReplyVO> selectQnaReplyList(PagingInfoVO<QnaReplyVO> pagingVO);
	
	/**
	 * QNA게시글의 댓글 목록조회
	 * @param pagingVO 검색조건을 가진 VO
	 * @return 조건에 맞는 글이 없다면 0 반환
	 */
	public long selectTotalRecord(PagingInfoVO<QnaReplyVO> pagingVO);
	
	/**
	 * QNA게시글에 댓글을 추가
	 * @param qnaReply 등록할 정보를 가진 VO
	 * @return 성공(>0) 실패
	 */
	public int insertQnaReply(QnaReplyVO qnaReply);
	
	/**
	 * QNA게시글의 댓글을 수정
	 * @param qnaReply 등록할 정보를 가진 VO
	 * @return 성공(>0) 실패
	 */
	public int updateQnaReply(QnaReplyVO qnaReply);
	
	/**
	 * QNA게시글의 댓글을 삭제
	 * @param qnaboard_reply_no 댓글번호
	 * @return 성공(>0) 실패
	 */
	public int deleteQnaReply(long qnaboard_reply_no);
}
