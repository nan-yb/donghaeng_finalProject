package kr.or.ddit.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("reserveListVO")
@Data
@NoArgsConstructor
public class Package_Reserve_ListVO implements Serializable{
	private Long rnum;
	private Long package_reservation_list_no;
	private String package_reservation_list_name;
	private String package_reservation_list_tel;
	private String package_reservation_list_gend;
	private String mem_id;
	private Long package_reservation_no;
	
}
