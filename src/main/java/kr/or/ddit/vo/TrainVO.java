package kr.or.ddit.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("trainVO")
@Data
@NoArgsConstructor
public class TrainVO implements Serializable{
	private Long train_no;
	private String depPlaceId;
	private String arrPlaceId;
	private String depPlandTime;
	private String person;
	private String arrplandtime;
	private String depPlandTime2;
	private String adultcharge;
	private String traingradename;
	private String mem_id;
}
