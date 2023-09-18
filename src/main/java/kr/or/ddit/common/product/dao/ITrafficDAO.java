package kr.or.ddit.common.product.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AirPlainVO;
import kr.or.ddit.vo.TrainVO;

@Repository
public interface ITrafficDAO {
	
	public int insertAir(AirPlainVO airPlainVO);
	public List<AirPlainVO> selectAir();
	
	public int insertTrain(TrainVO train);
}
