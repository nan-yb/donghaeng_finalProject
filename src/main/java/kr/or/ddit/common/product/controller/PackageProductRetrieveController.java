package kr.or.ddit.common.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PackageProductRetrieveController {
	@RequestMapping("/common/package/pakageProductForm.do")
	public String process(){
		return "common/product/packageProductList";
	}
}
