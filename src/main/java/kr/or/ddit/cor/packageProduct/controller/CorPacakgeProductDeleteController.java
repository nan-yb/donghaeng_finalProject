package kr.or.ddit.cor.packageProduct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CorPacakgeProductDeleteController {
	// 여행사가 자신의 패키지상품을 삭제하는 메서드
	
	@RequestMapping("/packageProduct/packageDelete.do")
	public String getProcess(){
		return "corporation/product/corProductView";
	}
	
}
