package kr.or.ddit.signup.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.api.encrypt.EncryptionUtil;
import kr.or.ddit.signup.dao.ISignupDAO;
import kr.or.ddit.vo.CorporationVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;


@Service
public class SignUpServiceImpl implements ISignUpService {

	@Inject
	ISignupDAO dao;
	
	private void encryption(PersonVO person){
		try {
			byte[] encrypt_pass = EncryptionUtil.sha512Encrypt(person.getPerson_pass());
			String passWord = EncryptionUtil.base64Encode(encrypt_pass);
			person.setPerson_pass(passWord);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	@Override
	public boolean idCheck(String mem_id) {
		boolean result = false;
		if(dao.selectMember(mem_id) ==null){
			result = true;
		}
		return result;
	}
	
	@Override
	@Transactional
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		
		if(dao.selectMember(member.getMem_id())==null) {
			encryption(member.getPerson());
			int personCnt = dao.insertPerson(member.getPerson());
			int memberCnt = dao.insertMember(member);
			
			if((memberCnt+personCnt) == 2){
				result = ServiceResult.OK;
			}else{
				result = ServiceResult.FAILED;
			}
		}else{
			result = ServiceResult.PKDUPLICATED;
		}
		
		return result;
	}
	
	

	@Override
	@Transactional
	public ServiceResult createCorp(CorporationVO corp) {
		ServiceResult result = null;
		
		if(dao.selectCorporation(corp.getCompany_id())==null) {
			encryption(corp.getPerson());
			int personCnt = dao.insertPerson(corp.getPerson());
			int corpCnt = dao.insertCorporation(corp);
			
			if((corpCnt+personCnt) == 2){
				result = ServiceResult.OK;
			}else{
				result = ServiceResult.FAILED;
			}
		}else{
			result = ServiceResult.PKDUPLICATED;
		}
		
		return result;
	}



	@Override
	public long retrieveMemberCount(PagingInfoVO pagingVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> retrieveMemberList(PagingInfoVO pagingVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO retrieveMember(String mem_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
