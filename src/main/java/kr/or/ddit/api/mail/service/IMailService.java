package kr.or.ddit.api.mail.service;

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
public interface IMailService {
	
	/**
	 * 메일 전송
	 * @param subject 제목
	 * @param text 내용
	 * @param from 보내는 메일주소
	 * @param to 받는 메일 주소
	 * @param filePath 첨부 파일 경로
	 * @return
	 */
	public boolean send(String subject, String text, String from, String to, String filePath);
}
