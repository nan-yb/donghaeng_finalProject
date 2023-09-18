package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CorporationVO {
	private String company_id;
	private String company_business_no;
	private String company_name;
	private String company_apply;
	private PersonVO person;
	private List<PersonVO> personList;
}
