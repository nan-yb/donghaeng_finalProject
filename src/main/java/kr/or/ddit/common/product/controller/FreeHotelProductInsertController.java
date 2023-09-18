package kr.or.ddit.common.product.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.product.service.IFreeProductService;
import kr.or.ddit.vo.TestHotelVO;

@Controller
public class FreeHotelProductInsertController {
	
	@Inject
	IFreeProductService service;
	
	@RequestMapping(value="/common/package/insertFreeProduct.do")
	public String process(
			@ModelAttribute("hotelVO")TestHotelVO hotelVO,
			Model model
			){
		String goPage = null;
		String message = null;
		
		ServiceResult result = service.registBook(hotelVO);
		switch (result) {
		case FAILED:
			goPage = "common/product/checkFreeProductForm";
			message = "서버 오류로 인한 실패, 잠시 뒤 다시 하셈.";
			break;
		case OK:
			goPage = "redirect:/";
			break;
		}
		model.addAttribute("message", message);
		
		return goPage;
	}
}
