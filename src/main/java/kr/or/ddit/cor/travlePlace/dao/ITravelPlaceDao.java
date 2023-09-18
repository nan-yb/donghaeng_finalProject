package kr.or.ddit.cor.travlePlace.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.TravelVO;

@Repository
public interface ITravelPlaceDao {
	
	public int insertTravle();
	public List<TravelVO> travelplaceList(String cor_id);

}
