package kr.or.ddit.admin.memberManage.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.admin.memberManage.service.IMemberManagementService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

//블랙리스트 수정
//관리자가 블랙리스트 회원을 수정하는 프로그램

@Controller
@RequestMapping("/admin/blackListUpdate.do")
public class BlackListUpdateController {
	
	@Inject
	IMemberManagementService service;
	
//	@RequestMapping(method = RequestMethod.GET)
//	public String getProcess(){
//		return "admin/memManage/blackListView";
//	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String postProcess(
		@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member,
//		@RequestParam(name="mem_id", required = true) String mem_id,
//		@RequestParam(name="mem_state", required = true) String mem_state,
		BindingResult errors,
		Model model
	){
		boolean valid = !errors.hasErrors();
		String view = null;
//		MemberVO memVO = new MemberVO();
//		memVO.setMem_id(mem_id);
//		memVO.setMem_state(mem_state);
		if(valid){
			ServiceResult result = service.modifyBlackMember(member);
			if(ServiceResult.OK.equals(result)) {
				// POST-redirect-GET : PRG패턴
				view = "redirect:/admin/blackListView.do?what="+member.getMem_id();
			}else if(ServiceResult.FAILED.equals(result)){
				model.addAttribute("message", "서버 오류");
				view = "admin/memManage/blackListView";
			}else {
				model.addAttribute("message", "비밀번호 오류");
				view = "admin/memManage/blackListView";
			}
		}else {
			model.addAttribute("message", "필수 데이터 누락");
			view = "admin/memManage/blackListView";
		}
		return view;
	}
}
