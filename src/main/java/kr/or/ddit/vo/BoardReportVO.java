package kr.or.ddit.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Alias("boardReportVO")
public class BoardReportVO {
	private long rnum;
	
	private long report_no;
	private long board_no;
	private String report_mem_id;
	private long board_type;
	
	private long reportCount; // 누적 신고수
	
	private List<PersonVO> personList;
	private List<Travel_TipVO> travelTipBoardList;
	private List<ReviewVO> postBoardList;
	private List<FreeboardVO> freeBoardList;
	
}
