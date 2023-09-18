package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Alias("freeboardVO")
public class FreeboardVO implements Serializable{
	
	private Long board_no;
	
	@NotBlank
	private String board_content;
	@NotBlank
	private String board_title;
	private String board_date;
	private Long board_view_count;
	private Long board_recommend_count;
	private String person_name;
	private String mem_id;
	private Long board_type; //freeboard : 3
	private List<PersonVO> personList;
	private List<Free_FileVO> freeFileList;
	private List<Free_ReplyVO> freeReplyList;
	private Long[] delFiles;
	
	private MultipartFile[] free_file;
	
	public void setFree_file(MultipartFile[] free_file){
		this.free_file = free_file;
		List<Free_FileVO> freeFileList = null;
		if(free_file == null) return;
		freeFileList = new ArrayList<>();
		for(MultipartFile item : free_file){
			if(StringUtils.isBlank(item.getOriginalFilename()))
				continue;
			freeFileList.add(new Free_FileVO(item));
		}
		if(freeFileList.size() > 0)
			this.freeFileList = freeFileList;
	}
}
