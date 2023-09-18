package kr.or.ddit.common.customerService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerServiceController {
	@RequestMapping("/customerService/customerService.do")
	public String getProcess(){
		return "common/customerService/customerServiceForm";
	}

}
