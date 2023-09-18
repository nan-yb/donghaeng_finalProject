package kr.or.ddit.admin.adminMain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminMain {
	@RequestMapping("/admin/mainPage.do")
	public String getProcess(){
		return "admin/adminMain";
	}
}
