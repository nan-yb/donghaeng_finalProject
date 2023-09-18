package kr.or.ddit.cor.myPage.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PersonVO;

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
@Repository
public interface ICorMyPageDAO {
	/**
	 * 기업회원 정보조회
	 * @param person_id
	 * @return
	 */
	public PersonVO selectCorMyPage(String person_id);
	
	
	/**
	 * 기업회원 정보수정
	 * @param person
	 * @return
	 */
	public int updateCorMyPage(PersonVO person);
	
	
	/**
	 * 기업회원 탈퇴
	 * @param person
	 * @return
	 */
	public int deleteCorMyPage(PersonVO person);
}
