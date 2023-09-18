package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("travel_tipVO")
@Data
@NoArgsConstructor
public class Travel_TipVO implements Serializable{

	public Travel_TipVO(Long travel_tip_no, String travel_tip_mem_id) {
		super();
		this.travel_tip_no = travel_tip_no;
		this.travel_tip_mem_id = travel_tip_mem_id;
	}
	
	private Long rnum;
	private Long travel_tip_no;
	private Long travel_tip_parent;

	private String travel_tip_content;
	private String travel_tip_title;
	private String travel_tip_date;
	private Long travel_tip_view_count;
	private Long travel_tip_recommend_count;
	private String travel_tip_mem_id;
	private String travel_tip_pass;
	private List<PersonVO> personList;
	private List<Travel_Tip_FileVO> pdsList;
	private List<Travel_Tip_ReplyVO> replyList;
	private Long[] delFiles;
	
	private MultipartFile[] travel_tip_file;
	
	public void settravel_tip_file(MultipartFile[] travel_tip_file) {
		this.travel_tip_file = travel_tip_file;
		List<Travel_Tip_FileVO> pdsList = null;
		if(travel_tip_file==null) return; 
		pdsList = new ArrayList<>();
		for(MultipartFile item:travel_tip_file) {
			if(StringUtils.isBlank(item.getOriginalFilename())) continue;
			pdsList.add(new Travel_Tip_FileVO(item));							
		}
		if(pdsList.size()>0)
			this.pdsList = pdsList;
	}
}

