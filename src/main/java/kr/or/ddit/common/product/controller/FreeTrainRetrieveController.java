package kr.or.ddit.common.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FreeTrainRetrieveController {
	@RequestMapping("/common/free/freeRetrieve.do")
	public String process(){
		return "common/product/freeProductTrain";
	}
}
