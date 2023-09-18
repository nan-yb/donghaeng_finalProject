package kr.or.ddit.common.board.qnaboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.qnaboard.dao.IQnaboardDAO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.QnaboardVO;

/**
 * @author 서신원
 * @since 2019. 1. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 11.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class QnaboardServiceImpl implements IQnaboardService {

	@Inject
	IQnaboardDAO qnaboardDAO;
	
	@Override
	public ServiceResult createQnaboard(QnaboardVO qnaboard) {
		ServiceResult result = null;
		int rowCnt = qnaboardDAO.insertQnaboard(qnaboard);
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public long retrieveTotalRecord(PagingInfoVO<QnaboardVO> pagingVO) {
		return qnaboardDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<QnaboardVO> retrieveQnaboardList(PagingInfoVO<QnaboardVO> pagingVO) {
		return qnaboardDAO.selectQnaboardList(pagingVO);
	}

	@Override
	public QnaboardVO retrieveQnaboard(long qnaboard_no) {
		return qnaboardDAO.selectQnaboard(qnaboard_no);
	}

	@Override
	public ServiceResult modifyQnaboard(QnaboardVO qnaboard) {
		ServiceResult result = null;
			int rowCnt = qnaboardDAO.updateQnaboard(qnaboard);
			if (rowCnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult removeQnaboard(long qnaboard_no) {
		ServiceResult result = null;
		int rowCnt = qnaboardDAO.deleteQnaboard(qnaboard_no);
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
