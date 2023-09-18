package kr.or.ddit.vo;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("review_replyVO")
@Data
@NoArgsConstructor
public class Review_ReplyVO {
	private Long review_reply_no;
	private Long review_no;
	private String review_reply_mem_id;
	private String review_reply_pass;
	private String review_reply_content;
	private String review_reply_date;
	private String review_reply_mem_name;
	
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
