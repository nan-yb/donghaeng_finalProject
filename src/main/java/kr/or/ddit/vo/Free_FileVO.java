package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Alias("freefileVO")
public class Free_FileVO implements Serializable{
	
	public Free_FileVO(MultipartFile item){
		super();
		this.item = item;
		setBoard_file_size(item.getSize());
		setBoard_file_fancysize(FileUtils.byteCountToDisplaySize(item.getSize()));
		setBoard_file_name(item.getOriginalFilename());
		setBoard_file_mime(item.getContentType());
		setBoard_file_savename(UUID.randomUUID().toString());
	}
	
	private Long board_file_no;
	private String board_file_name;
	private String board_file_savename;
	private String board_file_mime;
	private Long board_file_size;
	private String board_file_fancysize;
	private Long board_no;
	private MultipartFile item;
}
