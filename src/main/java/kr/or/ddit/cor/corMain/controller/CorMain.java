package kr.or.ddit.cor.corMain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CorMain {
	
	@RequestMapping("/cor/mainPage.do")
	public String getProcess(){
		return "corporation/corMain";
	} 
}
