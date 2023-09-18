package kr.or.ddit.cor.category.service;

import java.util.List;

import kr.or.ddit.vo.CategoryVO;

public interface ICategoryService {
	
	
	public List<CategoryVO> selectCategory(String company_id);	
}
