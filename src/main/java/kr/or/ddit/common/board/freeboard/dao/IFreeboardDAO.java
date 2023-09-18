package kr.or.ddit.common.board.freeboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.FreeboardVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;

@Repository
public interface IFreeboardDAO {
	
	// 검색 조건에 맞는 게시글 목록 조회
	public List<FreeboardVO> selectBoardList(PagingInfoVO<FreeboardVO> pagingVO);
	
	// 검색과 페이징 처리를 위해 검색 조건에 맞는 게시글 수 조회
	public long selectTotalRecord(PagingInfoVO<FreeboardVO> pagingVO);
	
	//한 사람에 대한 정보 조회
	public PersonVO selectPerson(String mem_id);
	
	public int insertBoard(FreeboardVO freeVO);
	
	public FreeboardVO selectBoard(@Param("board_no") long board_no);
	
	public void incrementHit(long board_no);
	
	public void incrementRcmd(long board_no);
	
	public int updateBoard(FreeboardVO freeVO);
	
	public int deleteBoard(long board_no);
	
	//추천했는지 안했는지 확인
	public int selectFreeRcmd(@Param("filterData")Map<String, Object> map);
	//추천테이블에 입력
	public int insertFreeRcmd(@Param("filterData")Map<String, Object> map);
	
	public int deleteFreeRcmd(long board_no);
	
	//신고했는지 안했는지 확인
	public int selectFreeReport(Map<String, Object> map);
	
	//신고테이블에 입력
	public int insertFreeReport(Map<String, Object> map);
	
}
