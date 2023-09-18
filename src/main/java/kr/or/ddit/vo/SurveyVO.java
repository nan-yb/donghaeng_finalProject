package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;


@Alias("surveyVO")
@Data
@NoArgsConstructor
public class SurveyVO implements Serializable{
	private Long rnum;
	private Long survey_no;
	private String survey_date;
	private String survey_end_date;
	private String[] evaluation_content;
	private String company_id;
	private String survey_title;
	private List<Survey_EvaluationVO> surveyList;
	
	
	private String person_name; // 회사 이름 가져오려고
}
