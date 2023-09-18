package kr.or.ddit.cor.myPage.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.cor.myPage.service.ICorMyPageService;
import kr.or.ddit.vo.PersonVO;

//개인정보조회
//기업회원의 개인정보조회를 위한 로직

/**
 * @author 서신원
 * @since 2019. 1. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 22.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class CorporationRetrieveController {
	
	@Inject
	ICorMyPageService service;
	
	@RequestMapping("/common/mypage/corporationViewInfo.do")
	public String Process(
			HttpServletRequest req, HttpServletResponse resp,
			Model model
			) throws IOException{
		HttpSession session = req.getSession(false);
		if (session==null||session.isNew()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "로그인 한 이후에 마이페이지 요청가능합니다.");
			return null;
		}
		PersonVO authMember = (PersonVO) session.getAttribute("authMember");
		PersonVO person = service.retrieveCorMyPage(authMember.getPerson_id());
		req.setAttribute("person", person);
		return "member/mypage/corporationViewInfo";
	}
}
