package kr.or.ddit.admin.memberManage.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
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
public interface IMemberManagementService {
	/**
	 * 검색과 페이징을 위한 전체 게시글 수 조회
	 * @param pagingVO 조건을 가진 VO
	 * @return 조건에 맞는 글이 없다면 0 반환
	 */
	public long retrieveTotalRecord(PagingInfoVO<MemberVO> pagingVO);
	
	/**
	 * 검색조건에 맞는 게시글 목록 조회
	 * @param pagingVO 조건과 페이징 속성을 가진 VO
	 * @return 조건에 맞는 글이 없다면 size()==0
	 */
	public List<MemberVO> retrieveMemberManagementList(PagingInfoVO<MemberVO> pagingVO);
	
	/**
	 * 회원 상세조회 
	 * @param mem_id 회원 아이디
	 * @return 없다면 null 반환
	 */
	public MemberVO retrieveMemberManagement(String mem_id);
	
	
	//블랙리스트ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public List<MemberVO> retrieveBlackList(PagingInfoVO<MemberVO> pagingVO);
	
	public ServiceResult modifyBlackMember(MemberVO memVO);
	
	public long retrieveTotalBlackListRecord(PagingInfoVO<MemberVO> pagingVO);
	
	//마이페이지ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public PersonVO retrievePerson(String person_id);
	
	public ServiceResult modyfyMyInfo(PersonVO memVO);
}
