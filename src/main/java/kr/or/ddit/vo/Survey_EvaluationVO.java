package kr.or.ddit.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Alias("survey_evaluationVO")
public class Survey_EvaluationVO implements Serializable{
	private Long rnum;
	private Long evaluation_no;
	private Long evaluation_count;
	private String evaluation_content;
	private Long evaluation_point;
	private Long survey_no;
}
