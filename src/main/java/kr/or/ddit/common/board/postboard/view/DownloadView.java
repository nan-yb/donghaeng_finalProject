package kr.or.ddit.common.board.postboard.view;

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

import kr.or.ddit.vo.Review_FileVO;

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
		
		Review_FileVO pds = (Review_FileVO) model.get("pds");
		String fileName = pds.getReview_file_name();
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
		resp.setHeader("Content-Length", pds.getReview_file_size()+"");
		
		File downloadFile = new File(saveFolder, pds.getReview_savename());
		
		try(
				OutputStream out = resp.getOutputStream();
		){
			FileUtils.copyFile(downloadFile, out);
		}
		
		

	}

}
