package kr.or.ddit.common.board.tipboard.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.Travel_Tip_ReplyVO;

/**
 * @author sem
 * @since 2018. 12. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 12. 4.      작성자명       덧글 관리용 Business Logic Layer
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
public interface ITReplyService {
	/**
	 * 덧글 작성
	 * @param reply
	 * @return OK, FAILED
	 */
	public ServiceResult createReply(Travel_Tip_ReplyVO reply); 
	/**
	 * 특정 게시글의 덧글 수
	 * @param pagingVO
	 * @return 없다면, 0
	 */
	public long retriveReplyCount(PagingInfoVO<Travel_Tip_ReplyVO> pagingVO);
	/**
	 * 특정 게시글의 덧글 목록
	 * @param pagingVO
	 * @return 없다면, .size()==0
	 */
	public List<Travel_Tip_ReplyVO> retriveReplyList(PagingInfoVO<Travel_Tip_ReplyVO> pagingVO);
	/**
	 * 덧글 수정
	 * @param reply
	 * @return BoardException, INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult modifyReply(Travel_Tip_ReplyVO reply);
	/**
	 * 덧글 삭제
	 * @param reply
	 * @return BoardException, INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult removeReply(Travel_Tip_ReplyVO reply);
}


















