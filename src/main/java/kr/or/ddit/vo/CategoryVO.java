package kr.or.ddit.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("categoryVO")
@Data
@NoArgsConstructor
public class CategoryVO implements Serializable {
	private Long package_category_no;
	private String package_category_name;
}
