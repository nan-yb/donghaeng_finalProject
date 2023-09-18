package kr.or.ddit.common.board.noticeboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.noticeboard.servaice.INoticeboardService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.NoticeboardVO;

//공지게시판 작성 기능
//공지사항을 작성하기 위한 프로그램

@Controller
@RequestMapping("/noticeboard/noticeboardInsert.do")
public class NoticeInsertController {
	
	@Inject
	INoticeboardService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getProcess(){
		return "common/board/noticeboard/noticeboardForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postProcess(
			@Validated(InsertGroup.class) @ModelAttribute("notice") NoticeboardVO nbVO,
			Errors errors,
			Model model
	){
		boolean valid = !errors.hasErrors();
		String view = null;
		if(valid){
			ServiceResult result = service.createBoard(nbVO);
			if(ServiceResult.OK.equals(result)){
				view = "redirect:/noticeboard/noticeboardRetrieve.do";
			} else {
				model.addAttribute("message", "서버 오류");
				view = "common/board/noticeboard/noticeboardForm";
			}
		}else {
			model.addAttribute("message", "필수 데이터 누락");
			view = "common/board/noticeboard/noticeboardForm";
		}
		return view;
	}
}
