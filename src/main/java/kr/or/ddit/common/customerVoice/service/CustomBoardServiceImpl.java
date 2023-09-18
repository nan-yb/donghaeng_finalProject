package kr.or.ddit.common.customerVoice.service;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.BoardException;
import kr.or.ddit.common.board.postboard.dao.IBoardDAO;
import kr.or.ddit.common.customerVoice.dao.ICustomBoardDAO;
import kr.or.ddit.vo.CompanyVO;
import kr.or.ddit.vo.CustomerVoiceVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;
import kr.or.ddit.vo.ReviewVO;
import kr.or.ddit.vo.Review_FileVO;

@Service
public class CustomBoardServiceImpl implements ICustomBoardService {
	
	@Inject
	ICustomBoardDAO customboardDAO;
	@Inject
	IBoardDAO boardDAO;

	@Override
	public PersonVO personSelect(String mem_id) {
		return boardDAO.selectPerson(mem_id);
	}

	@Override
	public ServiceResult createBoard(CustomerVoiceVO customvoice) {
		PersonVO savedPerson = personSelect(customvoice.getMem_id());
		ServiceResult result = ServiceResult.FAILED;
		if(savedPerson == null) return ServiceResult.PKNOTFOUND;
		int rowCnt = 0;
		if(savedPerson.getPerson_pass().equals(customvoice.getCustomvoice_pass())){
			rowCnt = customboardDAO.insertBoard(customvoice);
		}else{
			result = ServiceResult.INVALIDPASSWORD;
		}
		if(rowCnt>0) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public long retriveBoardCount(PagingInfoVO<CustomerVoiceVO> pagingVO) {
		return customboardDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<CustomerVoiceVO> retriveBoardList(PagingInfoVO<CustomerVoiceVO> pagingVO) {
		return customboardDAO.selectBoardList(pagingVO);
	}

	@Override
	public CustomerVoiceVO retriveBoard(String customvoice_no) {
		CustomerVoiceVO customvoice = customboardDAO.selectBoard(customvoice_no);
		if(customvoice==null) {
			throw new BoardException(customvoice_no+"에 해당하는 게시글이 없음");
		}
		return customvoice;
	}

	@Override
	public ServiceResult modifyBoard(CustomerVoiceVO customvoice) {
		CustomerVoiceVO savedBoard = retriveBoard(customvoice.getCustomvoice_no());
		PersonVO savedPerson = personSelect(savedBoard.getMem_id());
		ServiceResult result = null;
		if(savedPerson.getPerson_pass().equals(customvoice.getCustomvoice_pass()) && savedBoard.getMem_id().equals(customvoice.getMem_id())) {
			int rowCnt = customboardDAO.updateBoard(customvoice);
			if(rowCnt > 0 ) {
				result = ServiceResult.OK;	
			}else {
				result = ServiceResult.FAILED;
			} // rowCnt 체크 if end
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		} // 비번 체크 if end
		return result;
	}

	@Override
	public ServiceResult removeBoard(CustomerVoiceVO board) {
		CustomerVoiceVO savedBoard = retriveBoard(board.getCustomvoice_no());
		PersonVO savedPerson = personSelect(savedBoard.getMem_id());
		ServiceResult result = null;
		if(savedBoard != null){
			if(savedPerson.getPerson_pass().equals(board.getCustomvoice_pass()) && savedBoard.getMem_id().equals(board.getMem_id())) {
				int rowCnt = customboardDAO.deleteBoard(board.getCustomvoice_no());
					if(rowCnt > 0) result = ServiceResult.OK;
				}else {
					result = ServiceResult.INVALIDPASSWORD;
				}
		}else{
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<CompanyVO> getCompanyName() {
		return customboardDAO.getCompanyName();
	}

	

}










