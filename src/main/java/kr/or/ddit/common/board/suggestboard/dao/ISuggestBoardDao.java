package kr.or.ddit.common.board.suggestboard.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.QnaboardVO;
import kr.or.ddit.vo.SuggestBoardVO;

@Repository
public interface ISuggestBoardDao {
	/**
	 * 게시글 작성
	 * @param sbVO
	 * @return 성공(>0) 실패
	 */
	public int suggestInsert(SuggestBoardVO sbVO);
/**
 * 검색과 페이징을 위한 전체 게시글 수 조회
 * @param pagingVO
 * @return 조건에 맞는 글이 없다면 0 반환
 */
	public long selectTotalRecord(PagingInfoVO<SuggestBoardVO> pagingVO);
	
	/**
	 * 게시글 상세조회
	 * @param bo_no
	 * @return 없다면 null 반환
	 */
	public SuggestBoardVO suggestSelect(long bo_no);
	
	/**
	 * 게시글 삭제
	 * @param bo_no
	 * @return 성공(>0) 실패
	 */
	public int suggestDelete(long bo_no);
	
	/**
	 * 게시글 수정
	 * @param 게시글 등록할 게시글의 정보를 가진 VO
	 * @return 성공(>0) 실패
	 */
	public int suggestUpdate(SuggestBoardVO sbVO);
	
	/**
	 * 검색조건에 맞는 게시글 목록 조회
	 * @param pagingVO
	 * @return 조건에 맞는 글이 없다면 size()==0
	 */
	public List<SuggestBoardVO> selectSuggestList(PagingInfoVO<SuggestBoardVO> pagingVO);
	
	
}
