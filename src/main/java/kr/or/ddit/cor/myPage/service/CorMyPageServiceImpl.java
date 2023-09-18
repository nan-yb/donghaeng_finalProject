package kr.or.ddit.cor.myPage.service;

import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.api.encrypt.EncryptionUtil;
import kr.or.ddit.cor.myPage.dao.ICorMyPageDAO;
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
@Service
public class CorMyPageServiceImpl implements ICorMyPageService {
	
	@Inject
	ICorMyPageDAO dao;
	
	
	private void encryption(PersonVO person){
		try {
			byte[] encryptionPass = EncryptionUtil.sha512Encrypt(person.getPerson_pass());
			String passWord = EncryptionUtil.base64Encode(encryptionPass);
			person.setPerson_pass(passWord);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	

	@Override
	public PersonVO retrieveCorMyPage(String person_id) {
		return dao.selectCorMyPage(person_id);
	}

	@Override
	public ServiceResult modifyCorMyPage(PersonVO person) {
		encryption(person);
		ServiceResult result = null;
		int rowCnt = dao.updateCorMyPage(person);
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult removeCorMyPage(PersonVO person) {
		ServiceResult result = null;
		PersonVO check = retrieveCorMyPage(person.getPerson_id());
		if (check.getPerson_pass().equals(person.getPerson_pass())) {
			int rowCnt = dao.deleteCorMyPage(check);
			if (rowCnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

}
