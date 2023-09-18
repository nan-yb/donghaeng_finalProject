package kr.or.ddit.common.board.suggestboard.controller;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.board.suggestboard.service.ISuggestBoardService;
import kr.or.ddit.vo.SuggestBoardVO;

//건의 게시판 수정 기능
//건의게시판에 게시글을 수정하는 로직

@Controller
public class SuggestboardUpdateController {
	@Inject
	ISuggestBoardService service;
	
	
	@RequestMapping(value="/suggestboard/suggestboardUpdate.do", method=RequestMethod.GET)
	public String getProcess(
			@RequestParam(name="what", required=true) long bo_no,
			Model model
			){
		SuggestBoardVO suggest = service.suggestSelect(bo_no);
		model.addAttribute("suggest", suggest);
		return "common/board/suggestboard/suggestboardForm";
	}
	
	@RequestMapping(value="/suggestboard/suggestboardUpdate.do", method=RequestMethod.POST)
	public String postProcess(
			@Validated(Update.class) @ModelAttribute("suggest") SuggestBoardVO sbVO,
			BindingResult errors, Model model
			){
		boolean vaild = !errors.hasErrors();
		String view=null;
		if(vaild){
			ServiceResult result = service.suggestModify(sbVO);
			if(ServiceResult.OK.equals(result)){
				view ="redirect:/suggest/suggestView.do?what="+sbVO.getSuggest_board_no();
			}else if(ServiceResult.FAILED.equals(result)){
				model.addAttribute("message", "서버오류");
				view = "common/board/suggestboard/suggestboardForm";
			}
		}else{
			model.addAttribute("mssage","비밀번호 오류");
			view = "common/board/suggestboard/suggestboardForm";
		}
		return view;
	}
}
