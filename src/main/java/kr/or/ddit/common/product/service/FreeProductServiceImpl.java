package kr.or.ddit.common.product.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.product.dao.IFreeProductDAO;
import kr.or.ddit.vo.TestHotelVO;

@Service
public class FreeProductServiceImpl implements IFreeProductService{
	
	IFreeProductDAO freeProductDAO;
	
	@Required
	@Inject
	public void setFreeProductDAO(IFreeProductDAO freeProductDAO) {
		this.freeProductDAO = freeProductDAO;
	}

	@Override
	public ServiceResult registBook(TestHotelVO hotelVO) {
		ServiceResult result = null;
		int rowCnt = freeProductDAO.insertBook(hotelVO);
		if(rowCnt > 0){
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;			
		}
		return result;
	}
	
	@Override
	public List<TestHotelVO> selectBook() {
		List<TestHotelVO> bookList = freeProductDAO.selectBook();
		
		return bookList;
	}
	
}
