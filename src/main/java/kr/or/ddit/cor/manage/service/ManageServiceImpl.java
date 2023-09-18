package kr.or.ddit.cor.manage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.cor.manage.dao.IManageDAO;
import kr.or.ddit.vo.PackageVO;
import kr.or.ddit.vo.Package_Reserve_ListVO;
import kr.or.ddit.vo.PagingInfoVO;

@Service
public class ManageServiceImpl implements IManageService {
	
	@Inject
	IManageDAO manageDAO;

	
	@Override
	public long retrivePackageCount(PagingInfoVO<PackageVO> pagingVO) {
		return manageDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<PackageVO> retrivePackageList(PagingInfoVO<PackageVO> pagingVO) {
		return manageDAO.selectPackageList(pagingVO);
	}

	@Override
	public long retrivePackageReserveCount(PagingInfoVO<Package_Reserve_ListVO> pagingVO) {
		return manageDAO.selectTotalReserveRecord(pagingVO);
	}

	@Override
	public List<Package_Reserve_ListVO> retrivePackageReserveList(PagingInfoVO<Package_Reserve_ListVO> pagingVO) {
		return manageDAO.selectPackageReserveList(pagingVO);
	}


	

	
}










