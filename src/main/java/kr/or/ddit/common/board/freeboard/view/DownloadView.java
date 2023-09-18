package kr.or.ddit.common.board.freeboard.view;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.vo.Free_FileVO;



public class DownloadView extends AbstractView {
	
	@Value("#{appInfo.pdsPath}")
	File saveFolder;
	
	@PostConstruct
	public void init(){
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}

	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		Free_FileVO free = (Free_FileVO) model.get("free");
		String fileName = free.getBoard_file_name();
//		fileName = URLEncoder.encode(fileName, "UTF-8"); // IE
		resp.setContentType("application/octet-stream");
		String agent = req.getHeader("User-Agent");
		if(StringUtils.containsIgnoreCase(agent, "msie") || 
				StringUtils.containsIgnoreCase(agent, "trident")) {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}else {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		resp.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");
		resp.setHeader("Content-Length", free.getBoard_file_size()+"");
		
		File downloadFile = new File(saveFolder, free.getBoard_file_savename());
		
		try(
				OutputStream out = resp.getOutputStream();
		){
			FileUtils.copyFile(downloadFile, out);
		}
		
		

	}

}
