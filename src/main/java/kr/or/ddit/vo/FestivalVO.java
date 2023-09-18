package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FestivalVO implements Serializable{
	private String title;			
	private String addr1;			
	private String firstimage;		
	private String contentid;		
	private String mapx;				
	private String mapy;				
	private String tel;				
}
