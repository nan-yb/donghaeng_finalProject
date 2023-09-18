package kr.or.ddit.common.board.suggestboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.suggestboard.dao.ISuggestBoardDao;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.SuggestBoardVO;

@Service
public class SuggestBoardServiceImpl implements ISuggestBoardService {

	@Inject
	ISuggestBoardDao dao;

	@Override
	public ServiceResult createSuggest(SuggestBoardVO sbVO) {
		ServiceResult result = ServiceResult.FAILED;
		int cnt = dao.suggestInsert(sbVO);
		if (cnt > 0) {
			result = ServiceResult.OK;
		}
		return result;
	}



	@Override
	public SuggestBoardVO suggestSelect(long bo_no) {
		return dao.suggestSelect(bo_no);
	}

	
	
	@Override
	public ServiceResult suggestDelete(long bo_no) {
		ServiceResult result = null;
		int rowCnt = dao.suggestDelete(bo_no);
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult suggestModify(SuggestBoardVO sbVO) {
		ServiceResult result = ServiceResult.FAILED;
		int cnt = dao.suggestUpdate(sbVO);
		if (cnt > 0) {
			result = ServiceResult.OK;
		}
		return result;
	}



	@Override
	public List<SuggestBoardVO> suggestList(PagingInfoVO<SuggestBoardVO> pagingVO) {
		return dao.selectSuggestList(pagingVO);
	}



	@Override
	public long retrieveTotalRecord(PagingInfoVO<SuggestBoardVO> pagingVO) {
		return dao.selectTotalRecord(pagingVO);
	}


}
