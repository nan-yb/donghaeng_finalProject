package kr.or.ddit.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("companyVO")
@Data
@NoArgsConstructor
public class CompanyVO {
	private String company_id;
	private String company_business_no;
	private String company_name;
}
