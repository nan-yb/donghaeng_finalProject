package kr.or.ddit.vo;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("qnaReplyVO")
public class QnaReplyVO {
	@NotBlank
	private Long qnaboard_reply_no;
	@NotBlank
	private String qnaboard_reply_content;
	@NotBlank
	private String qnaboard_reply_date;
	@NotBlank
	private Long qnaboard_no;
	@NotBlank
	private String company_id;
	private String person_name;
}
