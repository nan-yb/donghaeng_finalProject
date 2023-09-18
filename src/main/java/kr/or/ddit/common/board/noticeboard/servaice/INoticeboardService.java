package kr.or.ddit.common.board.noticeboard.servaice;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.Notice_FileVO;
import kr.or.ddit.vo.NoticeboardVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;

public interface INoticeboardService {
	public PersonVO selectPerson(String person_id);
	/**
	 * 새글 작성
	 * @param nbVO
	 * @return OK, FAILED
	 */
	public ServiceResult createBoard(NoticeboardVO nbVO);
	
	/**
	 * 검색 조건에 맞는 게시글 수
	 * @param pagingVO 검색 조건을 가진 VO
	 * @return 없다면, 0  반환
	 */
	public long retriveBoardCount(PagingInfoVO<NoticeboardVO> pagingVO);
	
	/**
	 * 검색 조건에 맞는 게시글 목록
	 * @param pagingVO 검색 조건과 페이징 속성을 가진 VO
	 * @return 없다면, .size()==0
	 */
	public List<NoticeboardVO> retriveBoardList(PagingInfoVO<NoticeboardVO> pagingVO);
	
	/**
	 * 글 조회
	 * @param notice_no 글번호
	 * @return 없으면, BoardException (unchecked exception) 발생
	 */
	public NoticeboardVO retriveBoard(long notice_no);
	
	/**
	 * 글 수정
	 * @param board
	 * @return BoardException, INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult modifyBoard(NoticeboardVO nbVO);
	
	/**
	 * 글 삭제
	 * @param board
	 * @return BoardException, INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult removeBoard(long notice_no);
	
	/**
	 * 첨부파일 다운로드용 메소드
	 * @param nf_no 파일 번호
	 * @return 없다면, BoardException 발생
	 */
	public Notice_FileVO downloadPds(long nf_no);
	
	
}
