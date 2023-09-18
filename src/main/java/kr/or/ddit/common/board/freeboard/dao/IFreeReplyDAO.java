package kr.or.ddit.common.board.freeboard.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.Free_ReplyVO;
import kr.or.ddit.vo.PagingInfoVO;

@Repository
public interface IFreeReplyDAO {
	public int insertReply(Free_ReplyVO replyVO);
	
	public long selectTotalRecord(PagingInfoVO<Free_ReplyVO> pagingVO);
	
	public List<Free_ReplyVO> selectReplyList(PagingInfoVO<Free_ReplyVO> pagingVO);
	
	public Free_ReplyVO selectReply(long reply_no);
	
	public int updateReply(Free_ReplyVO replyVO);
	
	public int deleteReply(long reply_no);
	
	public int deleteReplyList(long board_no);
}
