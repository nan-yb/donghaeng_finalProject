package kr.or.ddit.admin.crewManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.admin.crewManage.dao.ICrewManagementDAO;
import kr.or.ddit.vo.CrewVO;
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
public class CrewManagementServiceImpl implements ICrewManagementService {

	@Inject
	ICrewManagementDAO crewManagementDAO;
	
	@Override
	public long retrieveTotalRecord(PagingInfoVO<CrewVO> pagingVO) {
		return crewManagementDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<CrewVO> retrieveCrewManagementList(PagingInfoVO<CrewVO> pagingVO) {
		return crewManagementDAO.selectCrewManagementList(pagingVO);
	}

	@Override
	public CrewVO retrieveCrewManagement(long crew_no) {
		return crewManagementDAO.selectCrewManagement(crew_no);
	}

	@Override
	public ServiceResult modifyCrewManagement(CrewVO crew) {
		ServiceResult result = null;
		int rowCnt = crewManagementDAO.updateCrewManagement(crew);
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
