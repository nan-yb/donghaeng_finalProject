package kr.or.ddit.admin.boardManage.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//글 강제 삭제
//관리자가 신고된 글을 강제로 삭제하는 프로그램

@Controller
public class ReportboardDeleteController {
	
	@RequestMapping("/admin/reportboardDelete.do")
	public String getProcess(){
		return "";
	}
}
