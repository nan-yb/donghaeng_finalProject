package kr.or.ddit.cor.travlePlace.service;

import java.util.List;

import kr.or.ddit.vo.TravelVO;

public interface ITravelPlaceService {
	
	public List<TravelVO> travelList(String cor_id);
	
	
}
