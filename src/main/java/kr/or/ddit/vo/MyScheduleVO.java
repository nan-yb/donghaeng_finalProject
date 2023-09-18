package kr.or.ddit.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 서신원
 * @since 2019. 1. 14.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 14.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("myScheduleVO")
public class MyScheduleVO {
	private Long myschedule_no;
	private String myschedule_title;
	private String myschedule_content;
	private String myschedule_startdate;
	private String myschedule_enddate;
	private String mem_id;
}
