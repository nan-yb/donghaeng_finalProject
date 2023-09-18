package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("reviewVO")
@Data
@NoArgsConstructor
public class ReviewVO implements Serializable{

	public ReviewVO(Long review_no, String review_mem_id) {
		super();
		this.review_no = review_no;
		this.review_mem_id = review_mem_id;
	}
	
	private Long rnum;
	private Long review_no;
	private Long review_parent;

	private String review_content;
	private String review_title;
	private String review_date;
	private Long review_view_count;
	private Long review_recommend_count;
	private String review_mem_id;
	private String review_pass;
	
	
	private List<PersonVO> personList;
	private List<Review_FileVO> pdsList;
	private List<Review_ReplyVO> replyList;
	private Long[] delFiles;
	
	private MultipartFile[] review_file;
	
	public void setreview_file(MultipartFile[] review_file) {
		this.review_file = review_file;
		List<Review_FileVO> pdsList = null;
		if(review_file==null) return; 
		pdsList = new ArrayList<>();
		for(MultipartFile item:review_file) {
			if(StringUtils.isBlank(item.getOriginalFilename())) continue;
			pdsList.add(new Review_FileVO(item));							
		}
		if(pdsList.size()>0)
			this.pdsList = pdsList;
	}
}

