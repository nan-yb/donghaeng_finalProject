package kr.or.ddit.signup.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.CorporationVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

public interface ISignUpService {
	
	/**
	 * 회원 신규 등록
	 * @param member
	 * @return PKDUPLICATED, OK, FAILED
	 * @throws NoSuchAlgorithmException 
	 */
	public ServiceResult createMember(MemberVO member);
	
	public ServiceResult createCorp(CorporationVO corp);
	
	
	public boolean idCheck(String mem_id);
	
	public long retrieveMemberCount(PagingInfoVO pagingVO);
	
	/**
	 * 회원 목록 조회
	 * @param pagingVO TODO
	 * @return 존재하지 않을때, size()==0
	 */
	public List<MemberVO> retrieveMemberList(PagingInfoVO pagingVO);
	/**
	 * 회원 정보 상세 조회
	 * @param mem_id 조회할 회원의 아이디
	 * @return 존재하지 않는다면, CommonException 발생
	 */
	public MemberVO retrieveMember(String mem_id);
	/**
	 * 회원 정보 수정
	 * @param member 
	 * @return CommonException, INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult modifyMember(MemberVO member);
	/**
	 * 회원 탈퇴
	 * @param member
	 * @return CommonException, INVALIDPASSWORD, OK, FAILED
	 */
}
