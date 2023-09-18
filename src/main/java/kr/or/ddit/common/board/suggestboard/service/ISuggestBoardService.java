package kr.or.ddit.common.board.suggestboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.SuggestBoardVO;

public interface ISuggestBoardService {
	
	/**
	 * @author 박지원
	 * @param sbVO
	 * @return 
	 * 건의사항게시판을 등록하는 메서드
	 */
	public ServiceResult createSuggest(SuggestBoardVO sbVO);
	
	// 여러개 가져오고 싶다 해당 타입의 리스트를 가져오는거야
	//	public List<SuggestBoardVO>
	/**
	 * 검색조건에 맞는 게시글 목록 조회
	 * @param pagingVO 검색 조건과 페이징 속성을 가진 VO
	 * @return 조건에 맞는 글이 없다면 size()==0
	 */
	public List<SuggestBoardVO> suggestList(PagingInfoVO<SuggestBoardVO> pagingVO);
	
	//하나만 가져오고싶다 해당 타입의 VO 하나
	//	public SuggestBoardVO
	
	public SuggestBoardVO suggestSelect(long bo_no);
	
	/**
	 * 게시글 삭제
	 * @param qnaboard_no 글번호
	 * @return OK FAILED INVALIDPASSWORD
	 */
	public ServiceResult suggestDelete(long bo_no);
	
	/**
	 * 게시글 수정
	 * @param sbVO
	 * @return OK FAILED 
	 */
	public ServiceResult suggestModify(SuggestBoardVO sbVO);
	
	/**
	 * 검색과 페이징을 위한 전체 게시글 수 조회
	 * @param pagingVO 검색조건을 가짐
	 * @return 조건에 맞는 글이 없다면 0
	 */
	public long retrieveTotalRecord(PagingInfoVO<SuggestBoardVO> pagingVO);
		
}
