package kr.or.ddit.cor.survey.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.cor.survey.service.ISurveyService;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.SurveyVO;
import kr.or.ddit.vo.Survey_EvaluationVO;

//설문조사 참여 기능
//설문조사 참여를 위한 로직

@Controller
@RequestMapping("/survey")
public class SurveyListController {
	
	@Inject
	ISurveyService service;
	@ResponseBody
	@RequestMapping(value="surveyList.do", produces="application/json;charset=UTF-8")
	public PagingInfoVO<SurveyVO> processAsync(
			@RequestParam(required=false) String searchType,	
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(name="company_id", required=true) String authorMember
	){
		SurveyVO searchVO = new SurveyVO();
		if(StringUtils.isNotBlank(searchWord)) {
			if(StringUtils.isBlank(searchType)) {
				searchVO.setSurvey_title(searchWord);
			}else {
				switch (searchType) {
				case "title":
					searchVO.setSurvey_title(searchWord);
					break;
				}
			}			
		}
		searchVO.setCompany_id(authorMember);
		PagingInfoVO<SurveyVO> pagingVO = new PagingInfoVO<>(5,3);
		pagingVO.setCurrentPage(currentPage);
		/* 검색 정보 */
		pagingVO.setSearchVO(searchVO);
		long totalRecord = service.retriveSurveyCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<SurveyVO> surveyList = service.retriveSurveyList(pagingVO);
		pagingVO.setDataList(surveyList);
		return pagingVO;
	}
	
	@RequestMapping("surveyList.do")
	public String getProcess(
			@RequestParam(required=false) String searchType,	
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(name="company_id", required=true) String authorMember,
				Model model
			){
		PagingInfoVO<SurveyVO> pagingVO =
				processAsync(searchType, searchWord, currentPage, authorMember);
		model.addAttribute("pagingVO", pagingVO);
		return "corporation/survey/surveyList";
	}
}
