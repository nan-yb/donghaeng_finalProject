package kr.or.ddit.cor.packageProduct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PackageProductFormController {
	@RequestMapping("/package/pakageProductForm.do")
	public String process(){
		return "corporation/product/packageProductForm";
	}
}
