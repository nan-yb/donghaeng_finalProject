package kr.or.ddit.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TestHotelVO {
	private Long book_no;
	private Long hotel_id;
	private String mem_id;
	private Long use_room_cnt;
}
