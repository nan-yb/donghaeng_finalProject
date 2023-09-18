package kr.or.ddit.admin.corManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.admin.corManage.dao.ICorManagementDAO;
import kr.or.ddit.vo.CorporationVO;
import kr.or.ddit.vo.PagingInfoVO;
/**
 * @author 서신원
 * @since 2019. 1. 18.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 18.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class CorManagementServiceImpl implements ICorManagementService {

	@Inject
	ICorManagementDAO corManagementDAO;
	
	@Override
	public long retrieveTotalRecord(PagingInfoVO<CorporationVO> pagingVO) {
		return corManagementDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<CorporationVO> retrieveCorManagementList(PagingInfoVO<CorporationVO> pagingVO) {
		return corManagementDAO.selectCorManagementList(pagingVO);
	}

	@Override
	public CorporationVO retrieveCorManagement(String company_id) {
		return corManagementDAO.selectCorManagement(company_id);
	}

}
