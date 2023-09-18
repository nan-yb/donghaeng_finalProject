package kr.or.ddit.common.board.tipboard.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.vo.UploadImageVO;

@RestController
public class TUploadImageController{
	@Value("#{appInfo.boardImages}")
	String saveFolderUrl;
	@Value("#{appInfo.boardImages}")
	File folder;
	
	@PostConstruct
	public void init() throws IOException{
		if(!folder.exists()) folder.mkdirs();
	}
	
	@Inject
	WebApplicationContext container;
	

	@RequestMapping(value="/tipboard/uploadImage.do", method=RequestMethod.POST,
			produces="application/json;charset=UTF-8"
			)
	public UploadImageVO process(
			@RequestPart(required=true) MultipartFile upload
			) throws IOException{
		UploadImageVO vo = new UploadImageVO();
		if (StringUtils.isNotBlank(upload.getOriginalFilename())) {
			String savename = UUID.randomUUID().toString();
			File savefile = new File(folder, savename);
			vo.setFileName(upload.getOriginalFilename());
			vo.setUploaded(1);
			String cPath = container.getServletContext().getContextPath();
			System.out.println(cPath);
			vo.setUrl(cPath + saveFolderUrl + "/"+savename);
			try (InputStream in = upload.getInputStream();) {

				FileUtils.copyInputStreamToFile(in, savefile);
			}
		}else {
			// 이미지 업로드 안된 경우.
			Map<String, Object> error = new HashMap<>();
			error.put("number", 400);
			error.put("message", "업로드된 이미지가 없음.");
			vo.setError(error);
			
		}
			
		return vo;
	}

}










