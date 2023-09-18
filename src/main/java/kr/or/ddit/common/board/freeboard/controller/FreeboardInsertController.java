package kr.or.ddit.common.board.freeboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.freeboard.service.IFreeboardService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.FreeboardVO;

//자유게시판 작성 기능
//자유게시판에 게시글을 작성하기 위한 로직

@Controller
@RequestMapping("/freeboard/freeboardInsert.do")
public class FreeboardInsertController {
	
	@Inject
	IFreeboardService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getProcess(){
		return "common/board/freeboard/freeboardForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String postProcess(
		@Validated(InsertGroup.class) @ModelAttribute("free") FreeboardVO free,
		Errors errors,
		Model model
	){
		boolean valid = !errors.hasErrors();
		String view = null;
		if(valid){
			ServiceResult result = service.createBoard(free);
			if(ServiceResult.OK.equals(result)){
				view = "redirect:/freeboard/freeboardRetrieve.do";
			} else {
				model.addAttribute("message", "서버 오류");
				view = "common/board/freeboard/freeboardForm";
			}
		} else {
			model.addAttribute("message", "필수 데이터 누락");
			view = "common/board/freeboard/freeboardForm";
		}
		return view;
	}
}
