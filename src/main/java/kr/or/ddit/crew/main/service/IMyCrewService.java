package kr.or.ddit.crew.main.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.CrewVO;
import kr.or.ddit.vo.PagingInfoVO;

public interface IMyCrewService {
	
	public ServiceResult createCrew(CrewVO crewVO);

	public long retrieveCrewCount(PagingInfoVO<CrewVO> pagingVO);

	public List<CrewVO> retrieveCrewList(String mem_id);
	
//	public List<CrewVO> retrieveAllCrewList(PagingInfoVO<CrewVO> pagingVO);

	public CrewVO retrieveCrew(Long crew_no);

	public ServiceResult modifyCrew(CrewVO Crew);

	public ServiceResult removeCrew(CrewVO Crew);

}
