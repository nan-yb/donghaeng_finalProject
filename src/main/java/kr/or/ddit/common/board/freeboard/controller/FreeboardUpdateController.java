package kr.or.ddit.common.board.freeboard.controller;

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
import kr.or.ddit.common.board.freeboard.service.IFreeboardService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.FreeboardVO;

//자유게시판 수정
//게시글을 수정하기 위한 로직

@Controller
@RequestMapping("/freeboard/freeboardUpdate.do")
public class FreeboardUpdateController {

	@Inject
	IFreeboardService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getProcess(
			@RequestParam(name = "what", required = true) long board_no,
			Model model
	){
		FreeboardVO board = service.retrieveBoard(board_no);
		model.addAttribute("board", board);
		return "common/board/freeboard/freeboardForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String postProcess(
		@Validated(UpdateGroup.class) @ModelAttribute("board") FreeboardVO board,
		BindingResult errors,
		Model model
	){
		boolean valid = !errors.hasErrors();
		String view = null;
		if(valid){
			ServiceResult result = service.modifyBoard(board);
			if(ServiceResult.OK.equals(result)) {
				// POST-redirect-GET : PRG패턴
				view = "redirect:/freeboard/freeboardView.do?what="+board.getBoard_no();
			}else if(ServiceResult.FAILED.equals(result)){
				model.addAttribute("message", "서버 오류");
				view = "common/board/freeboard/freeboardForm";
			}else {
				model.addAttribute("message", "비밀번호 오류");
				view = "common/board/freeboard/freeboardForm";
			}
		}else {
			model.addAttribute("message", "필수 데이터 누락");
			view = "common/board/freeboard/freeboardForm";
		}
		return view;
	}
}
