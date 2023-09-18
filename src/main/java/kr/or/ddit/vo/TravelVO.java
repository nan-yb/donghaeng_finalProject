package kr.or.ddit.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TravelVO implements Serializable{
	private Long travel_no;
	private String travel_content;
	private String travel_place;
	private Long package_no;
	private Long hotel_no;
}
