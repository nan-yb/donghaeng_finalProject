package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("personVO")
public class PersonVO implements Serializable , HttpSessionBindingListener{
	
	public PersonVO(String person_id, String person_pass) {
		super();
		this.person_id = person_id;
		this.person_pass = person_pass;
	}
	
	@NotBlank
	private String person_id;
	@NotBlank
	private String person_pass;
	@NotBlank
	private String person_zip;
	@NotBlank
	private String person_addr1;
	@NotBlank
	private String person_addr2;
	@NotBlank
	private String person_tel;
	@NotBlank
	private String person_mail;
	@NotBlank
	private String person_name;

	@NotBlank
	private String person_type;
	
	private List<MemberVO> memberList;
	
	private String company_business_no;
	private List<CorporationVO> corList;
	
	
	private MultipartFile person_img;
	public void setPerson_img(MultipartFile person_img) throws IOException {
		this.person_img = person_img;
		if (person_img != null && StringUtils.isNotBlank(person_img.getOriginalFilename())) {
			this.per_img = person_img.getBytes();
		}
	}
	
	private byte[] per_img;
	
	public String getPer_imgToBase64() {
		if (per_img == null) {
			return null;
		} else {
			return Base64.encodeBase64String(per_img);
		}
	}
	
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		if("authMember".equals(event.getName())) {
			ServletContext application = event.getSession().getServletContext();
			Set<PersonVO> applicationUsers = (Set<PersonVO>) application.getAttribute("applicationUsers");
			applicationUsers.add(this);
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		if("authMember".equals(event.getName())) {
			ServletContext application = event.getSession().getServletContext();
			Set<PersonVO> applicationUsers = (Set<PersonVO>) application.getAttribute("applicationUsers");
			applicationUsers.remove(this);
		}
		
	}

	

	
}
