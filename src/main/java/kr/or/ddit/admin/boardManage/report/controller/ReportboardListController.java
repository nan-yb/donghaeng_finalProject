package kr.or.ddit.admin.boardManage.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//신고 들어온 글 목록 조회 기능
//관리자에게 신고가 들어온 게시글 리스트를 보여주는 프로그램

@Controller
public class ReportboardListController {
	@RequestMapping("/admin/reportboardList.do")
	public String getProcess(){
		return "admin/boardManage/reportboardList";
	}
}
