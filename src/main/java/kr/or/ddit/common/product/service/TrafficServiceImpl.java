package kr.or.ddit.common.product.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.product.dao.ITrafficDAO;
import kr.or.ddit.vo.AirPlainVO;
import kr.or.ddit.vo.TrainVO;

@Service
public class TrafficServiceImpl implements ITrafficService{

	@Inject
	ITrafficDAO trafficDAO;
	
	@Override
	public ServiceResult createAir(AirPlainVO airPlainVO) {
		
		ServiceResult result = ServiceResult.FAILED;
		int rowCnt = 0;
		rowCnt = trafficDAO.insertAir(airPlainVO);
		if(rowCnt>0) result = ServiceResult.OK;
		return result;
	}
	
	@Override
	public ServiceResult createProduct() {
		
		List<AirPlainVO> airPlain = new ArrayList<>();
		
		return null;
		
	}

	@Override
	public List<AirPlainVO> retrieveAir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult createTrain(TrainVO train) {
		ServiceResult result = ServiceResult.FAILED;
		int rowCnt = 0;
		rowCnt = trafficDAO.insertTrain(train);
		if(rowCnt>0) result = ServiceResult.OK;
		return result;
	}

	
}
