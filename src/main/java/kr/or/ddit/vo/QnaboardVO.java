package kr.or.ddit.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("qnaboardVO")
public class QnaboardVO {
	@NotBlank
	private Long qnaboard_no;
	@NotBlank
	private String qnaboard_title;
	@NotBlank
	private String qnaboard_content;
	@NotBlank
	private String qnaboard_date;
	@NotBlank
	private String mem_id;
	private String person_name;
	
	private List<PersonVO> personList;
	
}
