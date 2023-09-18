package kr.or.ddit.common.board.noticeboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.noticeboard.servaice.INoticeboardService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.NoticeboardVO;

//공지게시판 수정 기능
//공지사항을 수정하기 위한 프로그램

@Controller
@RequestMapping("/noticeboard/noticeboardUpdate.do")
public class NoticeUpdateController {
	
	@Inject
	INoticeboardService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getProcess(
			@RequestParam(name = "what", required=true) long notice_no,
			Model model
	){
		NoticeboardVO notice = service.retriveBoard(notice_no);
		
		model.addAttribute("notice", notice);
		
		return "common/board/noticeboard/noticeboardForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String postProcess(
			@Validated(UpdateGroup.class) @ModelAttribute("notice") NoticeboardVO notice,
			BindingResult errors,
			Model model
	){
		boolean valid = !errors.hasErrors();
		String view = null;
		if(valid) {
			ServiceResult result = service.modifyBoard(notice);
			if(ServiceResult.OK.equals(result)) {
				// POST-redirect-GET : PRG패턴
				view = "redirect:/noticeboard/noticeboardView.do?what="+notice.getNotice_no();
			}else if(ServiceResult.FAILED.equals(result)){
				model.addAttribute("message", "서버 오류");
				view = "common/board/noticeboard/noticeboardForm";
			}else {
				model.addAttribute("message", "비밀번호 오류");
				view = "common/board/noticeboard/noticeboardForm";
			}
		}else {
			model.addAttribute("message", "필수 데이터 누락");
			view = "common/board/noticeboard/noticeboardForm";
		}
		return view;
	}
}
