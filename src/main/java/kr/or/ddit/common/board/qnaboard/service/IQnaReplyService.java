package kr.or.ddit.common.board.qnaboard.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.QnaReplyVO;

public interface IQnaReplyService {
	/**
	 * QNA게시글의 전체 댓글수
	 * @param pagingVO 검색 조건과 페이징 속성을 가진 VO
	 * @return 조건에 맞는 글이 없다면 size()==0
	 */
	public List<QnaReplyVO> retrieveQnaReplyList(PagingInfoVO<QnaReplyVO> pagingVO);
	
	/**
	 * QNA게시글의 댓글 목록조회
	 * @param pagingVO 검색조건을 가진 VO
	 * @return 조건에 맞는 글이 없다면 0 반환
	 */
	public long retrieveTotalRecord(PagingInfoVO<QnaReplyVO> pagingVO);
	
	/**
	 * QNA게시글에 댓글을 추가
	 * @param qnaReply 등록할 정보를 가진 VO
	 * @return 성공(>0) 실패
	 */
	public ServiceResult createQnaReply(QnaReplyVO qnaReply);
	
	/**
	 * QNA게시글의 댓글을 수정
	 * @param qnaReply 등록할 정보를 가진 VO
	 * @return 성공(>0) 실패
	 */
	public ServiceResult modifyQnaReply(QnaReplyVO qnaReply);
	
	
	/**
	 * QNA게시글의 댓글을 삭제
	 * @param qnaReply 등록할 정보를 가진 VO
	 * @return 성공(>0) 실패
	 */
	public ServiceResult removeQnaReply(QnaReplyVO qnaReply);
}
