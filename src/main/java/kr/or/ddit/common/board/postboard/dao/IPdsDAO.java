package kr.or.ddit.common.board.postboard.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.ReviewVO;
import kr.or.ddit.vo.Review_FileVO;

/**
 * @author sem
 * @since 2018. 12. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 12. 4.      작성자명       첨부파일 관리를 위한 persistence layer
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
	@Repository
	public interface IPdsDAO {
	/**
	 * 파일 등록(메타데이터만..)
	 * @param pds
	 * @return row count
	 */
	public int insertPds(Review_FileVO pds);
	
	/**
	 * 여러건의 첨부파일을 한번의 insert 쿼리로 삽입.
	 * @param board TODO
	 * @param sessoin TODO
	 * @return
	 */
	public int insertPdsList(ReviewVO review);
	
	/**
	 * 다운로드용으로 사용될 조회 메소드
	 * @param pds_no
	 * @return 존재하지 않는다면, null반환
	 */
	public Review_FileVO selectPds(long review_file_no);
	
	/**
	 * 게시글 수정시 파일 삭제를 위한 메소드
	 * @param pds_no
	 * @param session TODO
	 * @return row count
	 */
	public int deletePds(long pds_no);
	/**
	 * 여러개의 첨부파일을 한꺼번에 삭제
	 * @param board
	 * @param session TODO
	 * @return
	 */
	public int deletePdses(ReviewVO review);
}


















