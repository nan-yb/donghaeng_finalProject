package kr.or.ddit.common.board.noticeboard.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.Notice_FileVO;
import kr.or.ddit.vo.NoticeboardVO;

@Repository
public interface INoticeFileDAO {
	/**
	 * 파일 등록(메타데이터만..)
	 * @param nfVO
	 * @return row count
	 */
	public int insertNoticeFile(Notice_FileVO nfVO);
	
	/**
	 * 여러건의 첨부파일을 한번의 insert 쿼리로 삽입.
	 * @param nbVO TODO
	 * @param sessoin TODO
	 * @return
	 */
	public int insertNoticeFileList(NoticeboardVO nbVO);
	
	/**
	 * 다운로드용으로 사용될 조회 메소드
	 * @param nf_no
	 * @return 존재하지 않는다면, null반환
	 */
	public Notice_FileVO selectNoticeFile(long nf_no);
	
	/**
	 * 게시글 수정시 파일 삭제를 위한 메소드
	 * @param nf_no
	 * @param session TODO
	 * @return row count
	 */
	public int deleteNoticeFile(long nf_no);
	/**
	 * 여러개의 첨부파일을 한꺼번에 삭제
	 * @param nbVO
	 * @param session TODO
	 * @return
	 */
	public int deleteNoticeFileList(NoticeboardVO nbVO);
}
