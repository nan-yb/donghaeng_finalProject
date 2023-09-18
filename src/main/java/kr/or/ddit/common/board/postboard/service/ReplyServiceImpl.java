package kr.or.ddit.common.board.postboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.BoardException;
import kr.or.ddit.common.board.postboard.dao.IBoardDAO;
import kr.or.ddit.common.board.postboard.dao.IReplyDAO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;
import kr.or.ddit.vo.Review_ReplyVO;

@Service
public class ReplyServiceImpl implements IReplyService {
	@Inject
	IReplyDAO replyDAO;
	@Inject
	IBoardDAO boardDAO;
	
	@Override
	public ServiceResult createReply(Review_ReplyVO reply) {
		int rowCnt = replyDAO.insertReply(reply);
		ServiceResult result = ServiceResult.FAILED;
		if(rowCnt > 0)
			result = ServiceResult.OK;
		return result;
	}

	@Override
	public long retriveReplyCount(PagingInfoVO<Review_ReplyVO> pagingVO) {
		return replyDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<Review_ReplyVO> retriveReplyList(PagingInfoVO<Review_ReplyVO> pagingVO) {
		return replyDAO.selectReplyList(pagingVO);
	}

	private Review_ReplyVO retrieveReply(long rep_no) {
		Review_ReplyVO reply = replyDAO.selectReply(rep_no);
		if(reply==null) {
			throw new BoardException(rep_no+" 덧글 존재하지 않음. ");
		}
		return reply;
	}
	
	@Override
	public ServiceResult modifyReply(Review_ReplyVO reply) {
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
	public ServiceResult removeReply(Review_ReplyVO reply) {
//		Review_ReplyVO savedReply =  retrieveReply(reply.getReview_reply_no());
//		PersonVO savedPerson = boardDAO.selectPerson(reply.getReview_reply_mem_id());
		ServiceResult result = null;
//		if(savedPerson.getPerson_pass().equals(reply.getReview_reply_pass())) {
			int rowCnt = replyDAO.deleteReply(reply.getReview_reply_no());
			if(rowCnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
//		}else {
//			result = ServiceResult.INVALIDPASSWORD;
//		}
		return result;
	}

}





