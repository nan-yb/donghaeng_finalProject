package kr.or.ddit.cor.survey.controller;


import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.runtime.directive.Parse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.cor.survey.service.ISurveyService;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.Survey_EvaluationVO;


//후기게시판 조회
//후기 게시판의 모든 후기조회를 위한 로직

@Controller
@RequestMapping("/survey")
public class SurveyViewController {
	@Inject
	ISurveyService service;
	@ResponseBody
	@RequestMapping(value="surveyView.do", produces="application/json;charset=UTF-8")
	public PagingInfoVO<Survey_EvaluationVO> processAsync(
			@RequestParam(required=false) String searchType,	
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(name="what", required=true) long survey_no
	){
		Survey_EvaluationVO searchVO = new Survey_EvaluationVO();
		if(StringUtils.isNotBlank(searchWord)) {
			if(StringUtils.isBlank(searchType)) {
//				searchVO.setEvaluation_content(searchWord);
			}else {
				switch (searchType) {
				case "content":
//					searchVO.setEvaluation_content(searchWord);	
					break;
				}
			}			
		}
		PagingInfoVO<Survey_EvaluationVO> pagingVO = new PagingInfoVO<>(5,3);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setNumber(survey_no);
		/* 검색 정보 */
		pagingVO.setSearchVO(searchVO);
		long totalRecord = service.retriveSurveyECount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<Survey_EvaluationVO> surveyEList = service.retriveSurveyEList(pagingVO);
		pagingVO.setDataList(surveyEList);
		return pagingVO;
	}
	
	@RequestMapping(value="surveyView.do")
	public String process(
			@RequestParam(required=false) String searchType,	
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(name="what", required=true) long survey_no,
			Model model
		) {
		PagingInfoVO<Survey_EvaluationVO> pagingVO =
				processAsync(searchType, searchWord, currentPage, survey_no);
		model.addAttribute("pagingVO", pagingVO);
		return "corporation/survey/surveyView";
	}
	
}
