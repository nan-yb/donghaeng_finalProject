package kr.or.ddit.crew.main.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CrewVO;
import kr.or.ddit.vo.PagingInfoVO;

@Repository
public interface IMyCrewDAO {
	
	public int insertCrew(CrewVO crewVO);

	public CrewVO selectCrew(Long crew_no);

//	public long selectTotalRecord(PagingInfoVO<CrewVO> pagingVO);

	public int updateCrew(CrewVO crew);

	public List<CrewVO> selectCrewList(String mem_id);
	
//	public List<CrewVO> selectCrewAllList(PagingInfoVO<CrewVO> pagingVO);

	public int deleteCrew(Long crew_no);
	
}
