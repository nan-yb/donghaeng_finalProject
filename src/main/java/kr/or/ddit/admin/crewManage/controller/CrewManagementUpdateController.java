package kr.or.ddit.admin.crewManage.controller;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.admin.crewManage.service.ICrewManagementService;
import kr.or.ddit.vo.CrewVO;

@Controller
public class CrewManagementUpdateController {

	@Inject
	ICrewManagementService service;
	
	@RequestMapping("/admin/crewManagementUpdate.do")
	public String process(
			long crew_no,
			String crew_type, 
			Model model
			){
		CrewVO crew = new CrewVO();
		crew.setCrew_no(crew_no);
		crew.setCrew_type(crew_type);
		ServiceResult result = service.modifyCrewManagement(crew);
		String view = null;
		Map<String, String> errors = new HashMap<>();
		view = "jsonView";
		if (ServiceResult.OK.equals(result)) {
			view = "redirect:/admin/crewManagementView.do?what="+crew_no;
		} else {
			errors.put("error", "true");
			errors.put("message", "서버오류");
		}
		model.addAttribute("errors", errors);
		return view;
	}
}

