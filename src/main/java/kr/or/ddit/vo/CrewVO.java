package kr.or.ddit.vo;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("crewVO")
public class CrewVO {
	private Long crew_no;
	private String mem_id;
	private String crew_name;
	private Long crew_limit;
	private String crew_date;
	private String crew_content;
	private String crew_type;
	
	
	private MultipartFile crew_image;
	
	public void setcrew_image(MultipartFile crew_image) throws IOException {
		this.crew_image = crew_image;
		if(crew_image!=null && StringUtils.isNotBlank(crew_image.getOriginalFilename())){
			this.crew_img = crew_image.getBytes();
		}
	}
	
	private byte[] crew_img;
	
	public String getcrew_imgToBase64(){
		if(crew_img==null) {
			return null;
		}else {
			return Base64.encodeBase64String(crew_img);
		}
	}
	
}
