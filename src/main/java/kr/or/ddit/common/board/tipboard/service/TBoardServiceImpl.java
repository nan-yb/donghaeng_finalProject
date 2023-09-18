package kr.or.ddit.common.board.tipboard.service;

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
import kr.or.ddit.common.board.tipboard.dao.ITBoardDAO;
import kr.or.ddit.common.board.tipboard.dao.ITPdsDAO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;
import kr.or.ddit.vo.Travel_TipVO;
import kr.or.ddit.vo.Travel_Tip_FileVO;

@Service
public class TBoardServiceImpl implements ITBoardService {
	
	@Inject
	ITBoardDAO boardDAO;
	@Inject
	ITPdsDAO pdsDAO;

	
	@Value("#{appInfo.pdsPath}")
	File saveFolder;
	
	@PostConstruct
	public void init(){
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}
	
	private int processFiles(Travel_TipVO travel_tip) {
		int rowCnt = 0;
		List<Travel_Tip_FileVO> pdsList = travel_tip.getPdsList();
		
		if(pdsList!=null && pdsList.size() > 0) {
			
			rowCnt += pdsDAO.insertPdsList(travel_tip);
			for(Travel_Tip_FileVO pds : pdsList) {
				try(
						InputStream in = pds.getItem().getInputStream();	
				){
					FileUtils.copyInputStreamToFile(in, 
							new File(saveFolder, pds.getTravel_tip_savename()));
				}catch (IOException e) {
				}
			}
		}
		
		Long[] delFiles = travel_tip.getDelFiles();
		if(delFiles!=null) {
			
			String[] saveNames = new String[delFiles.length];
			for(int idx=0; idx<delFiles.length; idx++) {
				saveNames[idx] = pdsDAO.selectPds(delFiles[idx])
									   .getTravel_tip_savename(); 
			}
			rowCnt += pdsDAO.deletePdses(travel_tip);

			for(String savename : saveNames) {
				FileUtils.deleteQuietly(new File(saveFolder, savename));
			}
			
		}
		
		return rowCnt;
	}
	
	
	@Override
	public ServiceResult createBoard(Travel_TipVO travel_tip) {
		PersonVO savedPerson = personSelect(travel_tip.getTravel_tip_mem_id());
		if(savedPerson == null) return ServiceResult.PKNOTFOUND;
		int rowCnt = boardDAO.insertBoard(travel_tip);
		int check = 1;
		if(rowCnt>0) {
			if(travel_tip.getPdsList()!=null)
				check += travel_tip.getPdsList().size();
			rowCnt += processFiles(travel_tip);
		}
		ServiceResult result = ServiceResult.FAILED;
		if(rowCnt>=check) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public long retriveBoardCount(PagingInfoVO<Travel_TipVO> pagingVO) {
		return boardDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<Travel_TipVO> retriveBoardList(PagingInfoVO<Travel_TipVO> pagingVO) {
		return boardDAO.selectBoardList(pagingVO);	
	}

	@Override
	public Travel_TipVO retriveBoard(long travel_tip_no) {
		Travel_TipVO travel_tip = boardDAO.selectBoard(travel_tip_no);
		if(travel_tip==null) {
			throw new BoardException(travel_tip_no+"에 해당하는 게시글이 없음");
		}
		boardDAO.incrementHit(travel_tip_no);
		return travel_tip;
	}

	@Override
	public ServiceResult modifyBoard(Travel_TipVO travel_tip) {
		Travel_TipVO savedBoard = retriveBoard(travel_tip.getTravel_tip_no());
		PersonVO savedPerson = personSelect(savedBoard.getTravel_tip_mem_id());
		ServiceResult result = null;
//		if(savedPerson.getPerson_pass().equals(travel_tip.getTravel_tip_pass())&& savedBoard.getTravel_tip_mem_id().equals(travel_tip.getTravel_tip_mem_id())) {
			int rowCnt = boardDAO.updateBoard(travel_tip);
			int check = rowCnt;
			if(rowCnt > 0) {
				if(travel_tip.getPdsList()!=null) 
					check += travel_tip.getPdsList().size();
				if(travel_tip.getDelFiles()!=null)
					check += travel_tip.getDelFiles().length;
				rowCnt += processFiles(travel_tip);
			}
			if(rowCnt >= check ) {
				result = ServiceResult.OK;	
			}else {
				result = ServiceResult.FAILED;
			} // rowCnt 체크 if end
//		}
//		else {
//			result = ServiceResult.INVALIDPASSWORD;
//		} // 비번 체크 if end
		return result;
	}

	@Override
	public ServiceResult removeBoard(Travel_TipVO travel_tip) {
		Travel_TipVO savedBoard = retriveBoard(travel_tip.getTravel_tip_no());
		PersonVO savedPerson = personSelect(savedBoard.getTravel_tip_mem_id());
		ServiceResult result = null;
		if(savedBoard.getTravel_tip_mem_id().equals(travel_tip.getTravel_tip_mem_id())) {
			int rowCnt = boardDAO.deleteBoard(travel_tip.getTravel_tip_no());
			if(rowCnt > 0) {
				try {
					List<Travel_Tip_FileVO> pdsList = savedBoard.getPdsList();
					if(pdsList!=null) {
						for(Travel_Tip_FileVO pds : pdsList) {
							FileUtils.deleteQuietly(
									new File(saveFolder, pds.getTravel_tip_savename())); 							
						}
					}// 첨부파일 체크 if end
				} catch (NullPointerException e) {

				}
					result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}


	@Override
	public Travel_Tip_FileVO downloadPds(long travel_tip_file_no) {
		Travel_Tip_FileVO pds = pdsDAO.selectPds(travel_tip_file_no);
		if(pds==null) {
			throw new BoardException(travel_tip_file_no+"에 해당 파일이 없음.");
		}
		return pds;
	}

	@Override
	public PersonVO personSelect(String mem_id) {
		return boardDAO.selectPerson(mem_id);
	}

	@Override
	public ServiceResult rcmdBoard(long travel_tip_no, String authMember) {
		ServiceResult result = null;
		Map<String, Object> map = new HashMap<>();
		map.put("travel_tip_no", travel_tip_no);
		map.put("authMember", authMember);
		
		int cnt = boardDAO.selectTipRcmd(map);
		
		if(cnt > 0){
			result = ServiceResult.DUPLICATED; // 추천중복
		}else{
			boardDAO.incrementRcmd(travel_tip_no);
			boardDAO.insertTipRcmd(map);
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	@Transactional
	public ServiceResult reportBoard(long travel_tip_no, String authMember) {
		ServiceResult result = null;
		Map<String, Object> map = new HashMap<>();
		map.put("travel_tip_no", travel_tip_no);
		map.put("authMember", authMember);
		
		int cnt = boardDAO.selectTipReport(map);
		if(cnt > 0){
			result = ServiceResult.DUPLICATED;
		} else {
			boardDAO.insertTipReport(map);
			result = ServiceResult.OK;
		}
		return result;
	}
	
	
	


}










