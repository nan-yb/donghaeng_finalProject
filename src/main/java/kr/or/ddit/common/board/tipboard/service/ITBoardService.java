package kr.or.ddit.common.board.tipboard.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;
import kr.or.ddit.vo.Travel_TipVO;
import kr.or.ddit.vo.Travel_Tip_FileVO;

/**
 * @author sem
 * @since 2018. 12. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 12. 4.      작성자명       게시글 관리를 위한 Business Logic Layer
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
public interface ITBoardService {
	public PersonVO personSelect(String mem_id);
	/**
	 * 새글 작성
	 * @param board
	 * @return OK, FAILED
	 */
	public ServiceResult createBoard(Travel_TipVO board);
	/**
	 * 검색 조건에 맞는 게시글 수
	 * @param pagingVO 검색 조건을 가진 VO
	 * @return 없다면, 0  반환
	 */
	public long retriveBoardCount(PagingInfoVO<Travel_TipVO> pagingVO);
	/**
	 * 검색 조건에 맞는 게시글 목록
	 * @param pagingVO 검색 조건과 페이징 속성을 가진 VO
	 * @return 없다면, .size()==0
	 */
	public List<Travel_TipVO> retriveBoardList(PagingInfoVO<Travel_TipVO> pagingVO);
	/**
	 * 글 조회
	 * @param bo_no 글번호
	 * @return 없으면, BoardException (unchecked exception) 발생
	 */
	public Travel_TipVO retriveBoard(long travel_tip_no);
	/**
	 * 글 수정
	 * @param board
	 * @return BoardException, INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult modifyBoard(Travel_TipVO board);
	/**
	 * 글 삭제
	 * @param board
	 * @return BoardException, INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult removeBoard(Travel_TipVO board);
	
	public Travel_Tip_FileVO downloadPds(long pds_no);
	
	public ServiceResult rcmdBoard(long travel_tip_no, String authMember);
	
	public ServiceResult reportBoard(long travel_tip_no, String authMember);
	
}


















