package kr.or.ddit.common.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.TestHotelVO;

public interface IFreeProductService {
	public ServiceResult registBook(TestHotelVO hotelVO);
	
	public List<TestHotelVO> selectBook();
}
