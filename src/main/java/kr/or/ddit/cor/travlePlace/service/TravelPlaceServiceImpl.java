package kr.or.ddit.cor.travlePlace.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.cor.travlePlace.dao.ITravelPlaceDao;
import kr.or.ddit.vo.TravelVO;

@Service
public class TravelPlaceServiceImpl implements ITravelPlaceService {

	@Inject
	ITravelPlaceDao dao;
	
	@Override
	public List<TravelVO> travelList(String cor_id) {
		return dao.travelplaceList(cor_id);
	}
	
}
