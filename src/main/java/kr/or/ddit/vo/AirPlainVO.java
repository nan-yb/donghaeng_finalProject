package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AirPlainVO implements Serializable{
	private Long airline_no;
	private String airline_book_no;
	private String airline_date;
	private String airline_startdate;
	private String airline_enddate;
	private String airline_type;
	private Long airline_seat;
	private Long airline_charge;
	private String airline_airline;
	private String airline_routetype;
}
