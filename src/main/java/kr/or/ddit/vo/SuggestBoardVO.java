package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@Alias("suggestboardVO")
public class SuggestBoardVO implements Serializable{
	
	public SuggestBoardVO(Long suggset_board_no, String suggest_board_pass, Long suggest_board_no){
		super();
		this.suggest_board_no = suggest_board_no;
		this.suggest_board_pass = suggest_board_pass;		
	}
	
	private Long suggest_board_no;
	private String suggest_board_title;
	private String suggest_board_content;
	private String suggest_board_date;
	private String mem_id;
	private String suggest_board_pass;
	private String person_name;
	
	private List<PersonVO> personList;
	

}
