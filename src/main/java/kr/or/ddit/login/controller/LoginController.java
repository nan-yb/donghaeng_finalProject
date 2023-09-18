package kr.or.ddit.login.controller;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.admin.memberManage.service.IMemberManagementService;
import kr.or.ddit.signup.service.IAuthenticateService;
import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.utils.CookieUtil.TextType;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PersonVO;

//로그인
//일반회원 또는 기업회원 선택 후 로그인할 수 있는 로직

@Controller
public class LoginController {
	
	@RequestMapping("/login/loginCheck.do")
	public String getProcess(){
		return "login/loginForm";
	}
	
	@Inject
	IAuthenticateService service;
	
	@Inject
	IMemberManagementService service2;
	
	@RequestMapping(value="/login/loginCheck.do", method=RequestMethod.POST)
	public String login(
			@RequestParam(required=true) String person_id,
			@RequestParam(required=true) String person_pass,
			@RequestParam(required=false) String idChecked,
			HttpServletRequest request, 
			RedirectAttributes redirectAttributes,
			HttpServletResponse response ) {
		String goPage = null;
		HttpSession session = request.getSession();
			
		Object result = service.authenticate(new PersonVO(person_id, person_pass));
		
		if(result instanceof PersonVO){
			if(((PersonVO) result).getPerson_type().equals("1")){
				MemberVO member = service2.retrieveMemberManagement(person_id);
				if(member == null){
					goPage = "redirect:/login/loginCheck.do";
					session.setAttribute("message", "블랙리스트 입니다.");
					return goPage;
				}
				session.setAttribute("member", member);
			}
			goPage = "redirect:/";
			session.setAttribute("authMember", result);
			int maxAge = 0;
			if("idSaved".equals(idChecked)){
				maxAge = 60*60*24*7;
			}
			Cookie idCookie = CookieUtil.createCookie("idCookie", person_id, request.getContextPath(), TextType.PATH, maxAge);
			response.addCookie(idCookie);
			
		}else if(result == ServiceResult.PKNOTFOUND){
			System.out.println("아이디 없음");
			goPage = "redirect:/login/loginCheck.do";
			session.setAttribute("message", "존재하지 않는 회원");
		}else{
			System.out.println("실패");
			goPage = "redirect:/login/loginCheck.do";
			redirectAttributes.addFlashAttribute("message", "비번 오류로 인증 실패");
		}
		return goPage;
	}
	
	@RequestMapping("/login/logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();	
		// 이동(index.jsp, redirect)
		return "redirect:/";
	}
}
