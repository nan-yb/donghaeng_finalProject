package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("packageVO")
@Data
@NoArgsConstructor
public class PackageVO implements Serializable{
	private Long rnum;
//	패키지 상품
	private Long package_no;
	private String package_name;
	private String package_content;
	private String package_start_date;
	private String package_end_date;
	private Long package_mincount;
	private Long package_maxcount;
	private Long package_price;
	private String company_id;
	private String car_no;
	private Long package_category_no;
	private MultipartFile package_image;
	private String mem_id;
	
//	패키지 예약
	private Long package_reservation_no;
	private Long package_reservation_state;
	private String package_reservation_date;
	private Long package_schedule_no;

//	패키지 스케쥴
	private String package_schedule_startdate;
	
	
	public void setpackage_image(MultipartFile package_image) throws IOException {
		this.package_image = package_image;
		if(package_image!=null && StringUtils.isNotBlank(package_image.getOriginalFilename())){
			this.package_img = package_image.getBytes();
		}
	}
	private byte[] package_img;
	public String getpackage_imgToBase64(){
		if(package_img==null) {
			return null;
		}else {
			return Base64.encodeBase64String(package_img);
		}
	}
}
