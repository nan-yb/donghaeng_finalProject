package kr.or.ddit.signup.contoller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.signup.service.ISignUpService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.CorporationVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PersonVO;

/**
 * 회원가입을 위한 회원용 회원가입 로직
 * @author 박지원
 *
 */

@Controller
public class SignUpController {
	
	@Inject
	ISignUpService service;
	
	@RequestMapping(method=RequestMethod.GET ,value="/signup/member.do")
	public String getMemProcess(){
		return "login/memSignUpForm";
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST ,value="/signup/memIdCheck.do" ,produces = "application/json;charset=utf8")
	public boolean memIdCheck(@RequestParam(required = true) String mem_id){
		boolean result = service.idCheck(mem_id);
		return result;
	}
	
	
	@RequestMapping(method=RequestMethod.POST ,value = "/signup/member.do")
	public String postCorProcess(@Validated(InsertGroup.class) PersonVO person,
			Errors errors,
			Model model) throws IOException{
		String goPage = null;
		String message = null;
//		Map<String, List<CharSequence>> errors = new LinkedHashMap<>();
//		model.addAttribute("errors", errors);
//		GeneralValidator validator = new GeneralValidator();
//		boolean valid = validator.validate(member, errors, InsertGroup.class);
		MemberVO member = new MemberVO();
		member.setMem_id(person.getPerson_id());
		member.setPerson(person);
		
		boolean valid = !errors.hasErrors();
		if (valid) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				goPage = "login/memSignUpForm";
				message = "아이디 중복, 바꾸셈.";
				break;
			case FAILED:
				goPage = "login/memSignUpForm";
				message = "서버 오류로 인한 실패, 잠시 뒤 다시 하셈.";
				break;
			case OK:
				goPage = "redirect:/";
				break;
			
			default :
				System.out.println("??");
			}
			
			model.addAttribute("message", message);
		} else {
			goPage = "login/memSignUpForm";
		}
		
		return goPage;
	}
	
	
	@RequestMapping(method=RequestMethod.GET ,value = "/signup/corp.do")
	public String getCorProcess(){
		return "member/memSignUpForm";
	}
	
	
	@RequestMapping(method=RequestMethod.POST ,value="/signup/corp.do")
	public String postMemberProcess(@Validated(InsertGroup.class) PersonVO person,
			Errors errors,
			Model model) throws IOException{
		String goPage = null;
		String message = null;
		CorporationVO corp = new CorporationVO();
		corp.setCompany_id(corp.getPerson().getPerson_id());
		corp.setCompany_business_no(corp.getPerson().getCompany_business_no());
		corp.setPerson(person);
		
		boolean valid = !errors.hasErrors();
		if (valid) {
			ServiceResult result = service.createCorp(corp);
			switch (result) {
			case PKDUPLICATED:
				goPage = "member/memSignUpForm";
				message = "아이디 중복, 바꾸셈.";
				break;
			case FAILED:
				goPage = "member/memSignUpForm";
				message = "서버 오류로 인한 실패, 잠시 뒤 다시 하셈.";
				break;
			case OK:
				goPage = "redirect:/";
				break;
			}
			model.addAttribute("message", message);
		} else {
			goPage = "member/memSignUpForm";
		}
		
		return goPage;
	}
}
