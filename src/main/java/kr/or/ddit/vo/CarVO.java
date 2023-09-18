package kr.or.ddit.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("carVO")
@Data
@NoArgsConstructor
public class CarVO implements Serializable{
	private String car_no;
	private Long car_count;
	private String car_name;
	private Long car_price;
	private Long car_state;
	private String car_mem_id;
}
