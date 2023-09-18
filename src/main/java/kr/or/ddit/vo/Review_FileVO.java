package kr.or.ddit.vo;

import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("review_fileVO")
@Data
@NoArgsConstructor
public class Review_FileVO {
	
	public Review_FileVO(MultipartFile item) {
		super();
		this.item = item;
		setReview_file_size(item.getSize());
		setReview_file_fancysize(FileUtils.byteCountToDisplaySize(item.getSize()));
		setReview_file_name(item.getOriginalFilename());
		setReview_file_mime(item.getContentType());
		setReview_savename(UUID.randomUUID().toString());
	}
	private Long review_file_no;
	private String review_file_name;
	private String review_savename;
	private String review_file_mime;
	private Long review_file_size;
	private String review_file_fancysize;
	private Long review_no;
	private MultipartFile item;

}
