package kr.or.ddit.common.board.postboard.service;

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
import kr.or.ddit.common.board.postboard.dao.IBoardDAO;
import kr.or.ddit.common.board.postboard.dao.IPdsDAO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;
import kr.or.ddit.vo.ReviewVO;
import kr.or.ddit.vo.Review_FileVO;

@Service
public class BoardServiceImpl implements IBoardService {
	
	@Inject
	IBoardDAO boardDAO;
	@Inject
	IPdsDAO pdsDAO;

	
	@Value("#{appInfo.pdsPath}")
	File saveFolder;
	
	@PostConstruct
	public void init(){
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}
	
	private int processFiles(ReviewVO review) {
		int rowCnt = 0;
		List<Review_FileVO> pdsList = review.getPdsList();
		
		if(pdsList!=null && pdsList.size() > 0) {
			
			rowCnt += pdsDAO.insertPdsList(review);
			for(Review_FileVO pds : pdsList) {
				try(
						InputStream in = pds.getItem().getInputStream();	
				){
					FileUtils.copyInputStreamToFile(in, 
							new File(saveFolder, pds.getReview_savename()));
				}catch (IOException e) {
				}
			}
		}
		
		Long[] delFiles = review.getDelFiles();
		if(delFiles!=null) {
			
			String[] saveNames = new String[delFiles.length];
			for(int idx=0; idx<delFiles.length; idx++) {
				saveNames[idx] = pdsDAO.selectPds(delFiles[idx])
									   .getReview_savename(); 
			}
			rowCnt += pdsDAO.deletePdses(review);

			for(String savename : saveNames) {
				FileUtils.deleteQuietly(new File(saveFolder, savename));
			}
			
		}
		
		return rowCnt;
	}
	
	
	@Override
	public ServiceResult createBoard(ReviewVO review) {
		PersonVO savedPerson = personSelect(review.getReview_mem_id());
		if(savedPerson == null) return ServiceResult.PKNOTFOUND;
		int rowCnt = boardDAO.insertBoard(review);
		int check = 1;
		if(rowCnt>0) {
			if(review.getPdsList()!=null)
				check += review.getPdsList().size();
			rowCnt += processFiles(review);
		}
		ServiceResult result = ServiceResult.FAILED;
		if(rowCnt>=check) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public long retriveBoardCount(PagingInfoVO<ReviewVO> pagingVO) {
		return boardDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<ReviewVO> retriveBoardList(PagingInfoVO<ReviewVO> pagingVO) {
		return boardDAO.selectBoardList(pagingVO);	
	}

	@Override
	public ReviewVO retriveBoard(long review_no) {
		ReviewVO review = boardDAO.selectBoard(review_no);
		if(review==null) {
			throw new BoardException(review_no+"에 해당하는 게시글이 없음");
		}
		boardDAO.incrementHit(review_no);
		return review;
	}

	@Override
	public ServiceResult modifyBoard(ReviewVO review) {
//		ReviewVO savedBoard = retriveBoard(review.getReview_no());
//		PersonVO savedPerson = personSelect(savedBoard.getReview_mem_id());
		ServiceResult result = null;
//		if(savedPerson.getPerson_pass().equals(review.getReview_pass()) && savedBoard.getReview_mem_id().equals(review.getReview_mem_id())) {
			int rowCnt = boardDAO.updateBoard(review);
			int check = rowCnt;
			if(rowCnt > 0) {
				if(review.getPdsList()!=null) 
					check += review.getPdsList().size();
				if(review.getDelFiles()!=null)
					check += review.getDelFiles().length;
				rowCnt += processFiles(review);
			}
			if(rowCnt >= check ) {
				result = ServiceResult.OK;	
			}else {
				result = ServiceResult.FAILED;
			} // rowCnt 체크 if end
//		}else {
//			result = ServiceResult.INVALIDPASSWORD;
//		} // 비번 체크 if end
		return result;
	}

	@Override
	public ServiceResult removeBoard(ReviewVO review) {
		ReviewVO savedBoard = retriveBoard(review.getReview_no());
//		PersonVO savedPerson = personSelect(savedBoard.getReview_mem_id());
		ServiceResult result = null;
//		System.out.println(savedBoard.getReview_mem_id());
//		System.out.println(review.getReview_mem_id());
//		if(savedPerson.getPerson_pass().equals(review.getReview_pass())&& savedBoard.getReview_mem_id().equals(review.getReview_mem_id())) {
			int rowCnt = boardDAO.deleteBoard(review.getReview_no());
			if(rowCnt > 0) {
				try {
					List<Review_FileVO> pdsList = savedBoard.getPdsList();
					if(pdsList!=null) {
						for(Review_FileVO pds : pdsList) {
							FileUtils.deleteQuietly(
									new File(saveFolder, pds.getReview_savename())); 							
						}
					}// 첨부파일 체크 if end
				} catch (NullPointerException e) {

				}
					result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
//		}else {
//			result = ServiceResult.INVALIDPASSWORD;
//		}
		return result;
	}


	@Override
	public Review_FileVO downloadPds(long review_file_no) {
		Review_FileVO pds = pdsDAO.selectPds(review_file_no);
		if(pds==null) {
			throw new BoardException(review_file_no+"에 해당 파일이 없음.");
		}
		return pds;
	}

	@Override
	public PersonVO personSelect(String mem_id) {
		return boardDAO.selectPerson(mem_id);
	}

	@Override
	@Transactional
	public ServiceResult rcmdBoard(long review_no, String authMember) {
		ServiceResult result = null;
		Map<String, Object> map = new HashMap<>();
		map.put("review_no", review_no);
		map.put("authMember", authMember);
		
		int cnt = boardDAO.selectPostRcmd(map);
		
		if(cnt > 0){
			result = ServiceResult.DUPLICATED; // 추천중복
		}else{
			boardDAO.incrementRcmd(review_no);
			boardDAO.insertPostRcmd(map);
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public ServiceResult reportBoard(long review_no, String authMember) {
		ServiceResult result = null;
		Map<String, Object> map = new HashMap<>();
		map.put("review_no", review_no);
		map.put("authMember", authMember);
		
		int cnt = boardDAO.selectPostReport(map);
		if(cnt > 0){
			result = ServiceResult.DUPLICATED;
		} else {
			boardDAO.insertPostReport(map);
			result = ServiceResult.OK;
		}
		return result;
	}
	
	
	


}










