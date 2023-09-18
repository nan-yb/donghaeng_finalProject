package kr.or.ddit.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("voiceVO")
@Data
@NoArgsConstructor
public class CustomerVoiceVO implements Serializable{
	public CustomerVoiceVO(String customvoice_no, String customvoice_pass, String mem_id) {
		super();
		this.customvoice_no = customvoice_no;
		this.customvoice_pass = customvoice_pass;
		this.mem_id = mem_id;
	}
	
	private Long rnum;
	private String customvoice_no;
	private String customvoice_title;
	private String customvoice_pass;
	private String customvoice_content;
	private String customvoice_date;
	private String company_id;
	private String mem_id;
	private String Type;

}
