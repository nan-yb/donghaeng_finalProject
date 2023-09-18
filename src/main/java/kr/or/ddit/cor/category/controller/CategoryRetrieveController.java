package kr.or.ddit.cor.category.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 업체 카테고리 조회 
@Controller
public class CategoryRetrieveController {
	
//	@Inject
//	IOtherService service;
	
	@RequestMapping("/cor/corCategoryRetrieve.do")
	public String getProcess(){
		return "corporation/category/categoryList";
	}	
}
