package kr.or.ddit.vo;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("travel_tip_replyVO")
@Data
@NoArgsConstructor
public class Travel_Tip_ReplyVO {
	private Long travel_tip_reply_no;
	private Long travel_tip_no;
	private String travel_tip_mem_id;
	private String travel_tip_mem_name;
	private String travel_tip_reply_pass;
	private String travel_tip_reply_content;
	private String travel_tip_reply_date;
	
	private byte[] per_img;
	
	public String getper_imgToBase64(){
		if(per_img==null) {
			return null;
		}else {
			return Base64.encodeBase64String(per_img);
		}
	}
	
	private List<PersonVO> personList;
}
