package kr.or.ddit.common.board.freeboard.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.Free_FileVO;
import kr.or.ddit.vo.FreeboardVO;

@Repository
public interface IFreeFileDAO {
	public int insertFile(Free_FileVO freeFile);
	
	public int insertFileList(FreeboardVO freeVO);
	
	public Free_FileVO selectFile(long board_file_no);
	
	public int deleteFile(long board_file_no);
	
	public int deleteFileList(FreeboardVO freeVO);
}
