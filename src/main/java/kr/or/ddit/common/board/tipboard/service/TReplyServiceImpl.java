package kr.or.ddit.common.board.tipboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.BoardException;
import kr.or.ddit.common.board.tipboard.dao.ITBoardDAO;
import kr.or.ddit.common.board.tipboard.dao.ITReplyDAO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;
import kr.or.ddit.vo.Travel_Tip_ReplyVO;

@Service
public class TReplyServiceImpl implements ITReplyService {
	@Inject
	ITReplyDAO replyDAO;
	@Inject
	ITBoardDAO boardDAO;
	
	@Override
	public ServiceResult createReply(Travel_Tip_ReplyVO reply) {
		PersonVO savedPerson = boardDAO.selectPerson(reply.getTravel_tip_mem_id());
		if(savedPerson == null) return ServiceResult.PKNOTFOUND;
		int rowCnt = replyDAO.insertReply(reply);
		ServiceResult result = ServiceResult.FAILED;
		if(rowCnt > 0) result = ServiceResult.OK;
		return result;
	}

	@Override
	public long retriveReplyCount(PagingInfoVO<Travel_Tip_ReplyVO> pagingVO) {
		return replyDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<Travel_Tip_ReplyVO> retriveReplyList(PagingInfoVO<Travel_Tip_ReplyVO> pagingVO) {
		return replyDAO.selectReplyList(pagingVO);
	}

	private Travel_Tip_ReplyVO retrieveReply(long rep_no) {
		Travel_Tip_ReplyVO reply = replyDAO.selectReply(rep_no);
		if(reply==null) {
			throw new BoardException(rep_no+" 덧글 존재하지 않음. ");
		}
		return reply;
	}
	
	@Override
	public ServiceResult modifyReply(Travel_Tip_ReplyVO reply) {
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
	public ServiceResult removeReply(Travel_Tip_ReplyVO reply) {
//		PersonVO savedPerson = boardDAO.selectPerson(reply.getTravel_tip_mem_id());
		ServiceResult result = null;
//		if(savedPerson.getPerson_pass().equals(reply.getTravel_tip_reply_pass())) {
			int rowCnt = replyDAO.deleteReply(reply.getTravel_tip_reply_no());
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





