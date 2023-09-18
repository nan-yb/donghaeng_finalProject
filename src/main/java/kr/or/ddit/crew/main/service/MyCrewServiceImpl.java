package kr.or.ddit.crew.main.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.BoardException;
import kr.or.ddit.crew.main.dao.IMyCrewDAO;
import kr.or.ddit.vo.CrewVO;
import kr.or.ddit.vo.PagingInfoVO;

@Service
public class MyCrewServiceImpl implements IMyCrewService {

	@Inject
	IMyCrewDAO dao;

	public ServiceResult createCrew(CrewVO crewVO) {
		ServiceResult result = null;

		int rowCnt = dao.insertCrew(crewVO);
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}

		return result;
	}

	@Override
	public List<CrewVO> retrieveCrewList(String mem_id) {
		List<CrewVO> CrewList = dao.selectCrewList(mem_id);
		return CrewList;
	}

	@Override
	public CrewVO retrieveCrew(Long crew_no) {
		CrewVO Crew = dao.selectCrew(crew_no);
		if (Crew == null) {
			throw new BoardException(crew_no + "에 해당하는 회원이 없음.");
		}
		return Crew;
	}

	@Override
	public ServiceResult modifyCrew(CrewVO Crew) {
		ServiceResult result = null;
		CrewVO check = retrieveCrew(Crew.getCrew_no());
		int rowCnt = dao.updateCrew(Crew);
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}

		return result;
	}

	@Override
	public ServiceResult removeCrew(CrewVO Crew) {
		ServiceResult result = null;
		CrewVO check = retrieveCrew(Crew.getCrew_no());
		int rowCnt = dao.deleteCrew(Crew.getCrew_no());
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	// @Override
	// public List<CrewVO> retrieveAllCrewList(PagingInfoVO<CrewVO> pagingVO) {
	// List<CrewVO> crewList = dao.selectCrewAllList();
	// return crewList;
	//
	// }

	@Override
	public long retrieveCrewCount(PagingInfoVO<CrewVO> pagingVO) {
		// TODO Auto-generated method stub
		return 0;
	}
}
