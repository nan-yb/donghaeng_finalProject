package kr.or.ddit.admin.memberManage.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.admin.memberManage.dao.IMemberManagementDAO;
import kr.or.ddit.api.encrypt.EncryptionUtil;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;
/**
 * @author 서신원
 * @since 2019. 1. 18.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 18.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class MemberManagementServiceImpl implements IMemberManagementService {

	@Inject
	IMemberManagementDAO memberManagementDAO;
	
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
	public long retrieveTotalRecord(PagingInfoVO<MemberVO> pagingVO) {
		return memberManagementDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<MemberVO> retrieveMemberManagementList(PagingInfoVO<MemberVO> pagingVO) {
		return memberManagementDAO.selectMemberManagementList(pagingVO);
	}

	@Override
	public MemberVO retrieveMemberManagement(String mem_id) {
		MemberVO mv = memberManagementDAO.selectMemberManagement(mem_id);
		if(mv.getMem_state().equals("F")){
			return null;
		}
		return mv;
	}

	@Override
	public List<MemberVO> retrieveBlackList(PagingInfoVO<MemberVO> pagingVO) {
		return memberManagementDAO.selectBlackList(pagingVO);
	}

	@Override
	public ServiceResult modifyBlackMember(MemberVO memVO) {
		ServiceResult result = ServiceResult.FAILED;
		int cnt = memberManagementDAO.updateBlackMember(memVO);
		if(cnt > 0){
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public long retrieveTotalBlackListRecord(PagingInfoVO<MemberVO> pagingVO) {
		return memberManagementDAO.selectTotalBlackListRecord(pagingVO);
	}

	@Override
	public PersonVO retrievePerson(String person_id) {
		return memberManagementDAO.selectPerson(person_id);
	}

	@Override
	public ServiceResult modyfyMyInfo(PersonVO memVO) {
		encryption(memVO);
		ServiceResult result = ServiceResult.FAILED;
		int cnt = memberManagementDAO.updateMyInfo(memVO);
		if(cnt > 0){
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
