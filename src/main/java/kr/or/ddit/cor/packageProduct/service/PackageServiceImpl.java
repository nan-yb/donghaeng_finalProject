package kr.or.ddit.cor.packageProduct.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.cor.packageProduct.dao.IPackageDAO;
import kr.or.ddit.vo.CarVO;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.PackageVO;
import kr.or.ddit.vo.PersonVO;

@Service
public class PackageServiceImpl implements IPackageService {
	
	@Inject
	IPackageDAO packageDAO;

	@Override
	public List<CategoryVO> selectCategory() {
		return packageDAO.selectCategory();
	}

	@Override
	public List<CarVO> selectCar(String mem_id) {
		return packageDAO.selectCar(mem_id);
	}

	@Override
	public ServiceResult createPackage(PackageVO packageVO) {
		int rowCnt = packageDAO.createPackage(packageVO);
		ServiceResult result = ServiceResult.FAILED;
		if(rowCnt>0) {
			result = ServiceResult.OK;
		}
		return result;
	}

	

	
}










