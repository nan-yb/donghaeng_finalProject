package kr.or.ddit.cor.category.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// 업체 카테고리 등록
public class CategoryInsertController {

	@RequestMapping("/cor/corCategoryInsert.do")
	public String getProcess(){
		return "corporation/category/categoryForm";
	}	
}
