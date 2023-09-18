package kr.or.ddit.common.board.freeboard.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.BoardException;
import kr.or.ddit.common.board.freeboard.dao.IFreeFileDAO;
import kr.or.ddit.common.board.freeboard.dao.IFreeReplyDAO;
import kr.or.ddit.common.board.freeboard.dao.IFreeboardDAO;
import kr.or.ddit.vo.Free_FileVO;
import kr.or.ddit.vo.FreeboardVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;

@Service
public class FreeboardServiceImpl implements IFreeboardService{
	
	@Inject
	IFreeboardDAO boardDAO;
	@Inject
	IFreeFileDAO fileDAO;
	@Inject
	IFreeReplyDAO replyDAO;
	
	@Value("#{appInfo.pdsPath}")
	File saveFolder;
	
	@PostConstruct
	public void init(){
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}
	
	private int processFiles(FreeboardVO freeVO){
		int rowCnt = 0;
		List<Free_FileVO> freeFileList = freeVO.getFreeFileList();
		
		if(freeFileList != null && freeFileList.size() > 0){
			rowCnt += fileDAO.insertFileList(freeVO);
			for(Free_FileVO freeFile : freeFileList){
				try(
					InputStream in = freeFile.getItem().getInputStream();	
				){
					FileUtils.copyInputStreamToFile(in, 
							new File(saveFolder, freeFile.getBoard_file_savename()));
				} catch(IOException e){
				}
			}
		}
		Long[] delFiles = freeVO.getDelFiles();
		if(delFiles != null){
			String[] saveNames = new String[delFiles.length];
			for(int idx = 0; idx < delFiles.length; idx++){
				saveNames[idx] = fileDAO.selectFile(delFiles[idx]).getBoard_file_savename();
			}
			rowCnt += fileDAO.deleteFileList(freeVO);
			
			for(String savename : saveNames){
				FileUtils.deleteQuietly(new File(saveFolder, savename));
			}
		}
		return rowCnt;
	}
	
	@Override
	public List<FreeboardVO> retrieveBoardList(PagingInfoVO<FreeboardVO> pagingVO) {
		return boardDAO.selectBoardList(pagingVO);
	}

	@Override
	public long retrieveBoardCount(PagingInfoVO<FreeboardVO> pagingVO) {
		return boardDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public PersonVO selectPerson(String mem_id) {
		return boardDAO.selectPerson(mem_id);
	}

	@Override
	public ServiceResult createBoard(FreeboardVO freeVO) {
		int rowCnt = boardDAO.insertBoard(freeVO);
		int check = 1;
		if(rowCnt > 0){
			if(freeVO.getFreeFileList() != null)
				check += freeVO.getFreeFileList().size();
			rowCnt += processFiles(freeVO);
		}
		ServiceResult result = ServiceResult.FAILED;
		if(rowCnt >= check){
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public FreeboardVO retrieveBoard(long board_no) {
		FreeboardVO freeVO = boardDAO.selectBoard(board_no);
		if(freeVO == null){
			throw new BoardException(board_no+"에 해당하는 게시글이 없습니다.");
		}
		boardDAO.incrementHit(board_no);
		return freeVO;
	}

	@Override
	public ServiceResult modifyBoard(FreeboardVO freeVO) {
		ServiceResult result = null;
		int rowCnt = boardDAO.updateBoard(freeVO);
		int check = rowCnt;
		if (rowCnt > 0) {
			if (freeVO.getFreeFileList() != null)
				check += freeVO.getFreeFileList().size();
			if (freeVO.getDelFiles() != null)
				check += freeVO.getDelFiles().length;
			rowCnt += processFiles(freeVO);
		}
		if (rowCnt >= check) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	@Transactional
	public ServiceResult removeBoard(FreeboardVO freeVO) {
		FreeboardVO savedBoard = retrieveBoard(freeVO.getBoard_no());
		ServiceResult result = null;
			int rowCnt = boardDAO.deleteBoard(freeVO.getBoard_no());
			if(rowCnt > 0){
				try{
					List<Free_FileVO> freeFileList = savedBoard.getFreeFileList();
					if(freeFileList != null){
						for(Free_FileVO freeFile : freeFileList){
							FileUtils.deleteQuietly(new File(saveFolder, freeFile.getBoard_file_savename()));
						}
					}
				}catch(NullPointerException e){
					
				}
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		return result;
	}
	
	/* 종속삭제하려고 만들어놓음 근데 디비 타임아웃생김
	 * private int remove(long board_no){
		int cnt = replyDAO.deleteReplyList(board_no);
		int cnt2 = boardDAO.deleteFreeRcmd(board_no);
		
		if(cnt > 0 && cnt2 > 0){
			return cnt;
		}
		return cnt;
	}*/

	@Override
	public Free_FileVO downloadFreeFile(long freeFileNo) {
		Free_FileVO freeFile = fileDAO.selectFile(freeFileNo);
		if(freeFile == null){
			throw new BoardException(freeFileNo+"에 해당 파일이 없습니다.");
		}
		return freeFile;
	}

	@Override
	@Transactional
	public ServiceResult rcmdBoard(long board_no, String authMembmer) {
		ServiceResult result = null;
		Map<String, Object> map = new HashMap<>();
		map.put("board_no", board_no);
		map.put("authMembmer", authMembmer);
		
		int cnt = boardDAO.selectFreeRcmd(map);
		if(cnt>0){
			result = ServiceResult.DUPLICATED; // 추천중복
		} else{
			boardDAO.incrementRcmd(board_no);
			boardDAO.insertFreeRcmd(map);
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	@Transactional
	public ServiceResult reportBoard(long board_no, String authMember) {
		ServiceResult result = null;
		Map<String, Object> map = new HashMap<>();
		map.put("board_no", board_no);
		map.put("authMember", authMember);
		
		int cnt = boardDAO.selectFreeReport(map);
		if(cnt > 0){
			result = ServiceResult.DUPLICATED;
		} else {
			boardDAO.insertFreeReport(map);
			result = ServiceResult.OK;
		}
		return result;
	}

	
}
