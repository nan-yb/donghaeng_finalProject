package kr.or.ddit.common.product.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.TestHotelVO;

@Repository
public interface IFreeProductDAO {
	public int insertBook(TestHotelVO hotelVO);
	public List<TestHotelVO> selectBook();
}
