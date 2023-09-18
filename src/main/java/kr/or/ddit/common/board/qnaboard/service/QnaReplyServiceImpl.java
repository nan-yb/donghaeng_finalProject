package kr.or.ddit.common.board.qnaboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.qnaboard.dao.IQnaReplyDAO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.QnaReplyVO;
@Service
public class QnaReplyServiceImpl implements IQnaReplyService {

	@Inject
	IQnaReplyDAO qnaReplyDAO;
	
	@Override
	public List<QnaReplyVO> retrieveQnaReplyList(PagingInfoVO<QnaReplyVO> pagingVO) {
		return qnaReplyDAO.selectQnaReplyList(pagingVO);
	}

	@Override
	public long retrieveTotalRecord(PagingInfoVO<QnaReplyVO> pagingVO) {
		return qnaReplyDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public ServiceResult createQnaReply(QnaReplyVO qnaReply) {
		ServiceResult result = null;
		int rowCnt = qnaReplyDAO.insertQnaReply(qnaReply);
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult modifyQnaReply(QnaReplyVO qnaReply) {
		ServiceResult result = null;
		int rowCnt = qnaReplyDAO.updateQnaReply(qnaReply);
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult removeQnaReply(QnaReplyVO qnaReply) {
		ServiceResult result = null;
		int rowCnt = qnaReplyDAO.deleteQnaReply(qnaReply.getQnaboard_reply_no());
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
