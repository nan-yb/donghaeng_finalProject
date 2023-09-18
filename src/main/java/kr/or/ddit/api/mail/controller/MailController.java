package kr.or.ddit.api.mail.controller;

import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.api.mail.service.IMailService;
import kr.or.ddit.signup.service.ISignUpService;

/**
 * @author 서신원
 * @since 2019. 1. 21.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 21.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class MailController {

	@Inject
	ISignUpService personSerivce;
	
	@Inject
	IMailService mailService;
	
	//인증번호발송
	@ResponseBody
	@RequestMapping(value="/mail/mailAuth.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public boolean joinProcess(
			@RequestParam String email, String CodeNum, Model model, HttpSession session
			){
//		int random = new Random().nextInt(1000000)+100000;
//		String joinCode = String.valueOf(random);
//		session.setAttribute("joinCode", joinCode);
//		model.addAttribute("joinCode", joinCode);
		
		String subject = "동행 홈페이지 회원가입 인증코드를 발급했습니다.";
		StringBuilder sb = new StringBuilder();
		sb.append("귀하의 인증코드는 "+CodeNum+" 입니다.");
		boolean result = mailService.send(subject, sb.toString(), "sin123test@gmail.com", email, null);
			
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/mail/findMail.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public boolean findIdProcess(
			@RequestParam String email, String CodeNum, Model model, HttpSession session
			){
		
		String subject = "동행 홈페이지 인증코드를 발급했습니다.";
		StringBuilder sb = new StringBuilder();
		sb.append("귀하의 인증코드는 "+CodeNum+" 입니다.");
		boolean result = mailService.send(subject, sb.toString(), "sin123test@gmail.com", email, null);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/mail/findPwMail.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public boolean findPwProcess(
			@RequestParam String email, String CodeNum, Model model, HttpSession session
			){
		
		String subject = "동행 홈페이지 임시비밀번호를 발급했습니다.";
		StringBuilder sb = new StringBuilder();
		sb.append("귀하의 임시비밀번호는 "+CodeNum+" 입니다.");
		boolean result = mailService.send(subject, sb.toString(), "sin123test@gmail.com", email, null);
		
		return result;
	}
	
}
