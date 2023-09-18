package kr.or.ddit.common.board.freeboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.BoardException;
import kr.or.ddit.common.board.freeboard.dao.IFreeReplyDAO;
import kr.or.ddit.common.board.freeboard.dao.IFreeboardDAO;
import kr.or.ddit.vo.Free_ReplyVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;

@Service
public class FreeReplyServiceImpl implements IFreeReplyService {

	@Inject
	IFreeReplyDAO replyDAO;
	@Inject
	IFreeboardDAO boardDAO;

	@Override
	public ServiceResult createReply(Free_ReplyVO reply) {
		int rowCnt = replyDAO.insertReply(reply);
		ServiceResult result = ServiceResult.FAILED;
		if (rowCnt > 0)
			result = ServiceResult.OK;
		return result;
	}

	@Override
	public long retriveReplyCount(PagingInfoVO<Free_ReplyVO> pagingVO) {
		return replyDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<Free_ReplyVO> retriveReplyList(PagingInfoVO<Free_ReplyVO> pagingVO) {
		return replyDAO.selectReplyList(pagingVO);
	}

	private Free_ReplyVO retrieveReply(long rep_no) {
		Free_ReplyVO reply = replyDAO.selectReply(rep_no);
		if (reply == null) {
			throw new BoardException(rep_no + " 덧글이 존재하지 않음. ");
		}
		return reply;
	}

	@Override
	public ServiceResult modifyReply(Free_ReplyVO reply) {
		Free_ReplyVO savedReply = retrieveReply(reply.getBoard_reply_no());
		PersonVO savedPerson = boardDAO.selectPerson(reply.getBoard_reply_mem_id());
		ServiceResult result = null;
		int rowCnt = replyDAO.updateReply(reply);
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult removeReply(long board_reply_no) {
		Free_ReplyVO savedReply = retrieveReply(board_reply_no);
		PersonVO savedPerson = boardDAO.selectPerson(savedReply.getBoard_reply_mem_id());
		ServiceResult result = null;
		int rowCnt = replyDAO.deleteReply(board_reply_no);
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
