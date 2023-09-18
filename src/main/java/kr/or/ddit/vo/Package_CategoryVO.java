package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Package_CategoryVO implements Serializable{
	long package_category_no;
	String package_category_name;
}
