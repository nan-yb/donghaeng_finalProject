package kr.or.ddit.cor.packageProduct.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.cor.packageProduct.service.IPackageService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.CarVO;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.PackageVO;
import kr.or.ddit.vo.ReviewVO;
@Controller
@RequestMapping("/packageProduct/packageInsert.do")
public class CorPacakgeProductInsertController {
	@Inject
	IPackageService service;
	// 여행사가 자신의 패키지상품을 추가하는 메서드
	
	@RequestMapping(method=RequestMethod.GET)
	public String getProcess(
			String mem_id,	
			Model model
			){
		
		List<CategoryVO> category = service.selectCategory();
		List<CarVO> car = service.selectCar(mem_id);
		
		model.addAttribute("category", category);
		model.addAttribute("car", car);
		
		return "corporation/product/corProductForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postProcess(
			@Validated(InsertGroup.class) @ModelAttribute("packageVO") PackageVO packageVO,
			Errors errors,
			Model model) {
		boolean valid = !errors.hasErrors();
		String view = null;
		if(valid) {
			ServiceResult result = service.createPackage(packageVO);
			if(result == ServiceResult.OK){
				view = "redirect:/packageProduct/packageInsert.do?mem_id="+packageVO.getCompany_id();
			}else{
				view = "redirect:/packageProduct/packageInsert.do?mem_id="+packageVO.getCompany_id();
			}
		}else {
			view = "corporation/product/corProductForm";
		}
		return view;
	}
}
