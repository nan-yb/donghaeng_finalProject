package kr.or.ddit.admin.corManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.admin.corManage.dao.ICorApplyDAO;
import kr.or.ddit.vo.CorporationVO;
import kr.or.ddit.vo.PagingInfoVO;
/**
 * @author 서신원
 * @since 2019. 1. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 19.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class CorApplyServiceImpl implements ICorApplyService {
	
	@Inject
	ICorApplyDAO corApplyDAO;

	@Override
	public long retrieveTotalRecord(PagingInfoVO<CorporationVO> pagingVO) {
		return corApplyDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<CorporationVO> retrieveCorApplyList(PagingInfoVO<CorporationVO> pagingVO) {
		return corApplyDAO.selectCorApplyList(pagingVO);
	}

	@Override
	public CorporationVO retrieveCorApply(String company_id) {
		return corApplyDAO.selectCorApply(company_id);
	}

	@Override
	public ServiceResult modifyCorApply(List<String> company_id) {
		ServiceResult result = null;
		int rowCnt = 0;
		for(String id : company_id){
			rowCnt += corApplyDAO.updateCorApply(id);
		}
		if (rowCnt >= company_id.size()) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
