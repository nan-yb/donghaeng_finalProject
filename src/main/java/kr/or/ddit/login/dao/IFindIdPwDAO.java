package kr.or.ddit.login.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PersonVO;

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
@Repository
public interface IFindIdPwDAO {
	/**
	 * 이름과 메일을 입력시 ID를 조회
	 * @param person
	 * @return
	 */
	public String selectFindId(PersonVO person);
	
	/**
	 * ID와 메일을 입력시 임시비밀번호로 수정
	 * @param person
	 * @return
	 */
	public int updateFindPw(PersonVO person);
	
	/**
	 * 임시비밀번호 수정후 임시비밀번호 조회
	 * @param person
	 * @return
	 */
	public String selectFindPw(PersonVO person);
}
