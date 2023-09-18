package kr.or.ddit.vo;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberVO {
	@NotBlank
	private String mem_id;
	private Long mem_mileage;
	private PersonVO person;
	private List<PersonVO> personList;
	private String mem_state;
	
}
