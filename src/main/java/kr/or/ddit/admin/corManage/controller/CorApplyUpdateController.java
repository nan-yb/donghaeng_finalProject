package kr.or.ddit.admin.corManage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.admin.corManage.service.ICorApplyService;
import kr.or.ddit.vo.CorporationVO;

//승인/미승인 여부 결정 기능
//관리자가 기업의 업체등록을 결정하는 프로그램

/**
 * @author 서신원
 * @since 2019. 1. 23.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 23.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class CorApplyUpdateController {
	
	@Inject
	ICorApplyService service;
	
	@RequestMapping(value="/admin/corApplyUpdate.do")
	public String getProcess(
			@RequestParam(name="apply" ,required=false) List<String> company_id,
			Model model
	){
		
		String view = null;
		ServiceResult result = service.modifyCorApply(company_id);
		view = "jsonView";
		System.out.println(result);
		if (result.OK.equals(result)) {
			view = "redirect:/admin/corApplyList.do";
		} else {
			model.addAttribute("message", "서버오류");
		}
		return view;
	}
}
