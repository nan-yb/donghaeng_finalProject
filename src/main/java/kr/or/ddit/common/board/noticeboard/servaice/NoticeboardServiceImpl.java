package kr.or.ddit.common.board.noticeboard.servaice;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.BoardException;
import kr.or.ddit.common.board.noticeboard.dao.INoticeFileDAO;
import kr.or.ddit.common.board.noticeboard.dao.INoticeboardDAO;
import kr.or.ddit.vo.Notice_FileVO;
import kr.or.ddit.vo.NoticeboardVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;

@Service
public class NoticeboardServiceImpl implements INoticeboardService{
	
	@Inject
	INoticeboardDAO noticeDAO;
	
	@Inject
	INoticeFileDAO noticeFileDAO;
	
	@Value("#{appInfo.noticeFilePath}")
	File saveFolder;
	
	@PostConstruct
	public void init(){
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}
	
	private int processFiles(NoticeboardVO nbVO){
		int rowCnt = 0;
		List<Notice_FileVO> noticeFileList = nbVO.getNoticeFileList();
		
		if(noticeFileList!=null && noticeFileList.size() > 0){
			
			rowCnt += noticeFileDAO.insertNoticeFileList(nbVO);
			
			for(Notice_FileVO file : noticeFileList){
				try(
						InputStream in = file.getItem().getInputStream();	
				){
					FileUtils.copyInputStreamToFile(in, 
							new File(saveFolder, file.getNotice_file_savename()));
				}catch (IOException e) {
				}
			}
		}
		Long[] delFiles = nbVO.getDelFiles();
		if(delFiles != null){
			String[] saveNames = new String[delFiles.length];
			for(int idx = 0; idx<delFiles.length; idx++){
				saveNames[idx] = noticeFileDAO.selectNoticeFile(delFiles[idx]).getNotice_file_savename();
			}
			rowCnt += noticeFileDAO.deleteNoticeFileList(nbVO);
			
			for(String savename : saveNames){
				FileUtils.deleteQuietly(new File(saveFolder, savename));
			}
		}
		return rowCnt;
	}
	
	@Override
	public ServiceResult createBoard(NoticeboardVO nbVO) {
		int rowCnt = noticeDAO.insertBoard(nbVO);
		int check = 1;
		if(rowCnt>0){
			if(nbVO.getNoticeFileList() != null)
				check += nbVO.getNoticeFileList().size();
			rowCnt += processFiles(nbVO);
		}
		ServiceResult result = ServiceResult.FAILED;
		if(rowCnt >= check) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public long retriveBoardCount(PagingInfoVO<NoticeboardVO> pagingVO) {
		return noticeDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<NoticeboardVO> retriveBoardList(PagingInfoVO<NoticeboardVO> pagingVO) {
		return noticeDAO.selectBoardList(pagingVO);
	}

	@Override
	public NoticeboardVO retriveBoard(long notice_no) {
		NoticeboardVO notice = noticeDAO.selectBoard(notice_no);
		if(notice==null) {
			throw new BoardException(notice_no+"에 해당하는 게시글이 없음");
		}
		noticeDAO.incrementHit(notice_no);
		return notice;
	}

	@Override
	public ServiceResult modifyBoard(NoticeboardVO nbVO) {
		ServiceResult result = null;

		int rowCnt = noticeDAO.updateBoard(nbVO);
		int check = rowCnt;
		if (rowCnt > 0) {
			if (nbVO.getNoticeFileList() != null)
				check += nbVO.getNoticeFileList().size();
			if (nbVO.getDelFiles() != null)
				check += nbVO.getDelFiles().length;
			rowCnt += processFiles(nbVO);
		}
		if (rowCnt >= check) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}

		return result;
	}

	@Override
	public ServiceResult removeBoard(long notice_no) {
		ServiceResult result = null;
		NoticeboardVO savedBoard = retriveBoard(notice_no);

		int rowCnt = noticeDAO.deleteBoard(notice_no);
		if (rowCnt > 0) {
			try {
				List<Notice_FileVO> noticeFileList = savedBoard.getNoticeFileList();
				if (noticeFileList != null) {
					for (Notice_FileVO file : noticeFileList) {
						FileUtils.deleteQuietly(new File(saveFolder, file.getNotice_file_savename()));
					}
				}
			} catch (NullPointerException e) {

			}
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public Notice_FileVO downloadPds(long nf_no) {
		Notice_FileVO file = noticeFileDAO.selectNoticeFile(nf_no);
		if(file == null){
			throw new BoardException(nf_no + "에 해당 파일이 없음.");
		}
		return file;
	}

	@Override
	public PersonVO selectPerson(String person_id) {
		return noticeDAO.selectPerson(person_id);
	}

}
