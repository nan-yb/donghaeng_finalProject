package kr.or.ddit.common.board.freeboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.Free_ReplyVO;
import kr.or.ddit.vo.PagingInfoVO;

@Service
public interface IFreeReplyService {
	/**
	 * 덧글 작성
	 * @param reply
	 * @return OK, FAILED
	 */
	public ServiceResult createReply(Free_ReplyVO reply); 
	/**
	 * 특정 게시글의 덧글 수
	 * @param pagingVO
	 * @return 없다면, 0
	 */
	public long retriveReplyCount(PagingInfoVO<Free_ReplyVO> pagingVO);
	/**
	 * 특정 게시글의 덧글 목록
	 * @param pagingVO
	 * @return 없다면, .size()==0
	 */
	public List<Free_ReplyVO> retriveReplyList(PagingInfoVO<Free_ReplyVO> pagingVO);
	/**
	 * 덧글 수정
	 * @param reply
	 * @return BoardException, INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult modifyReply(Free_ReplyVO reply);
	/**
	 * 덧글 삭제
	 * @param reply
	 * @return BoardException, INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult removeReply(long board_reply_no);
}
