package kr.or.ddit.login.service;

import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.api.encrypt.EncryptionUtil;
import kr.or.ddit.login.dao.IFindIdPwDAO;
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
@Service
public class FindIdPwServiceImpl implements IFindIdPwService {

	@Inject
	IFindIdPwDAO dao;
	
	private void encryption(PersonVO person){
		try {
			byte[] encryptPass = EncryptionUtil.sha512Encrypt(person.getPerson_pass());
			String passWord = EncryptionUtil.base64Encode(encryptPass);
			person.setPerson_pass(passWord);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String retrieveFindId(PersonVO person) {
		return dao.selectFindId(person);
	}

	@Override
	public ServiceResult modifyFindPw(PersonVO person) {
		encryption(person);
		ServiceResult result = null;
		int rowCnt = dao.updateFindPw(person);
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public String retrieveFindPw(PersonVO person) {
		return dao.selectFindPw(person);
	}
	
	

}
