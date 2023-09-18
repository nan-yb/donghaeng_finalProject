package kr.or.ddit.vo;

import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("travel_tip_fileVO")
@Data
@NoArgsConstructor
public class Travel_Tip_FileVO {
	
	public Travel_Tip_FileVO(MultipartFile item) {
		super();
		this.item = item;
		setTravel_tip_file_size(item.getSize());
		setTravel_tip_file_fancysize(FileUtils.byteCountToDisplaySize(item.getSize()));
		setTravel_tip_file_name(item.getOriginalFilename());
		setTravel_tip_file_mime(item.getContentType());
		setTravel_tip_savename(UUID.randomUUID().toString());
	}
	private Long travel_tip_file_no;
	private String travel_tip_file_name;
	private String travel_tip_savename;
	private String travel_tip_file_mime;
	private Long travel_tip_file_size;
	private String travel_tip_file_fancysize;
	private Long travel_tip_no;
	private MultipartFile item;

}
