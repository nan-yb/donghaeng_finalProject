package kr.or.ddit.common.board.freeboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.Free_FileVO;
import kr.or.ddit.vo.FreeboardVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;

@Service
public interface IFreeboardService {
	public List<FreeboardVO> retrieveBoardList(PagingInfoVO<FreeboardVO> pagingVO);
	
	public long retrieveBoardCount(PagingInfoVO<FreeboardVO> pagingVO);
	
	public PersonVO selectPerson(String mem_id);
	
	public ServiceResult createBoard(FreeboardVO freeVO);
	
	public FreeboardVO retrieveBoard(long board_no);
	
	public ServiceResult modifyBoard(FreeboardVO freeVO);
	
	public ServiceResult removeBoard(FreeboardVO freeVO);
	
	public Free_FileVO downloadFreeFile(long freeFileNo);
	
	public ServiceResult rcmdBoard(long board_no, String authMembmer);
	
	public ServiceResult reportBoard(long board_no, String authMember);
}
