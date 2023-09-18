package kr.or.ddit.login.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.login.service.IFindIdPwService;
import kr.or.ddit.vo.PersonVO;


//id찾기
//아이디를 찾기 위한 로직

/**
 * @author 서신원
 * @since 2019. 1. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 24.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class FindIdPwController {
	
	@Inject
	IFindIdPwService service;
	
	@RequestMapping("/login/findId.do")
	public String idProcess(){
		return "login/findId";
	}
	
	@RequestMapping("/login/findIding.do")
	public String findIdProcess(
			@RequestParam(required=true) String person_name,
			@RequestParam(required=true) String person_mail,
			Model model
			){
		PersonVO person = new PersonVO();
		person.setPerson_name(person_name);
		person.setPerson_mail(person_mail);
		String person_id = service.retrieveFindId(person);
		model.addAttribute("person_id", person_id);
		return "login/findId";
	}
	
	@RequestMapping("/login/findPw.do")
	public String pwProcess(){
		return "login/findPw";
	}
	
	@RequestMapping("/login/findPwding.do")
	public String findPwProcess(
			@RequestParam(required=true) String person_id,
			@RequestParam(required=true) String person_pass,
			@RequestParam(required=true) String person_mail,
			Model model
			){
		PersonVO person = new PersonVO();
		person.setPerson_id(person_id);
		person.setPerson_pass(person_pass);
		person.setPerson_mail(person_mail);
		String view = null;
		ServiceResult result = service.modifyFindPw(person);
		if (ServiceResult.OK.equals(result)) {
//			String id = service.retrieveFindPw(person);
//			model.addAttribute("id", id);
			view ="redirect:/login/findPw.do";
		} else {
			view = "login/findPw";
		}
		return view;
	}
	
}
