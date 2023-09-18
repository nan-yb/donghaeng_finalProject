package kr.or.ddit.common.product.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.AirPlainVO;
import kr.or.ddit.vo.TrainVO;

public interface ITrafficService {
	
	
	public ServiceResult createAir(AirPlainVO airPlainVO);
	public List<AirPlainVO> retrieveAir();
	
	public ServiceResult createTrain(TrainVO train);
	public ServiceResult createProduct();
}
