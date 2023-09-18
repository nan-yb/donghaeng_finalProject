package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FreeTrafficVO implements Serializable{
	private Long free_reservation_traffic_no;
	private Long free_reservation_no;
	private Long airline_no;
	private Long train_no;
}
