package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FreeProductVO implements Serializable{
	private Long free_reservation_state;
	private String free_reservation_startdate;
	private String free_reservation_date;
	private Long free_reservation_count;
	
	
	private List<String> mem_id;
	private List<Long> free_no;
	private List<Long> volunteerlist_no;
}
